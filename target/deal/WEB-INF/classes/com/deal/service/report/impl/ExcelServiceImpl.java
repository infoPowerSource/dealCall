package com.deal.service.report.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.deal.dao.report.ConferenceReportDao;
import com.deal.entity.create.ConferenceInfo;
import com.deal.entity.create.ConferenceReportInfo;
import com.deal.entity.report.ConfDetailCDR;
import com.deal.service.create.IConferenceService;
import com.deal.service.report.IExcelService;
import com.deal.util.DateFormatUtil;
import com.deal.util.FileUtils;

@Service
public class ExcelServiceImpl implements IExcelService{
	@Autowired
	private IConferenceService conferenceService;
	@Autowired
	private ConferenceReportDao reportDao;
	private static Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);
	/**
	 * 会后报告输出路径
	 */
	@Value("${report.reportPath}")
	private String reportPath;
	/**
	 * 会后报告模板
	 */
	@Value("${report.template.zh}")
	private String templateFile_zh;
	/**
	 * 中文报表文件
	 */
	File cnReport;

	/**
	 * 写入Excel数据
	 * 
	 * @param confDetailCDRList
	 * 
	 * @param recordList
	 */
	public void writeExcelReport(List<ConfDetailCDR> confDetailCDRList) throws IOException{
		String billingCode = confDetailCDRList.get(0).getBillingcode();
		Long confId = confDetailCDRList.get(0).getConferenceid();
		if(confDetailCDRList == null || confDetailCDRList.size() == 0 || billingCode == null) {
			logger.error("report is null , billingcode : {}", billingCode);
			return;
		}
		logger.info("write excel report files, billingcode : {}", billingCode);
		// 获取会议实体
		ConferenceInfo conference = conferenceService.getConfInfoById(confId.toString());
		if(conference == null) {
			logger.error("conference is null , confId : {}", confId);
			return;
		}
		try{
			// 参会者行为 sheet所在索引
			/**
			 * 添加判断模板文件类型的逻辑
			 */
			String fileType = "." + FileUtils.getExtend(templateFile_zh);
			/**
			 * 将报表数据写入excel文件
			 */
			Workbook chinaReport = null;
			// 判断文件类型，创建workbook
			chinaReport = new XSSFWorkbook(new FileInputStream(templateFile_zh));
			Sheet cn_sheet = chinaReport.getSheet("Sheet1");// 参会者名单
			// 会议主题
			Row cn_row = cn_sheet.getRow(0);
			Cell cn_cell = cn_row.getCell(1);
			if(cn_cell == null) {
				cn_cell = cn_row.createCell(1);
			}
			cn_cell.setCellValue(conference.getConfName());
			
			// 时间格式，字体等设置
			Font zhFont = chinaReport.createFont();
			zhFont.setFontHeightInPoints((short) 11);
			zhFont.setFontName("宋体");
			
			CellStyle cn_style = chinaReport.createCellStyle();
			cn_style.setAlignment(CellStyle.ALIGN_LEFT);
			cn_style.setVerticalAlignment(CellStyle.ALIGN_LEFT);
			cn_style.setFont(zhFont);
			cn_style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
			cn_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
			cn_style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
			cn_style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
			
			// 获得CreationHelper对象,这个应该是一个帮助类
			CreationHelper helper_cn = chinaReport.getCreationHelper();
			// 日期单元格格式处理
			CellStyle dateCellStyle_cn = chinaReport.createCellStyle();
			// m/d/yyh:mm 设置日期格式
			dateCellStyle_cn.setDataFormat(helper_cn.createDataFormat().getFormat("yyyy/MM/dd hh:mm:ss"));
			dateCellStyle_cn.setFont(zhFont);
			dateCellStyle_cn.setAlignment(CellStyle.ALIGN_LEFT);
			dateCellStyle_cn.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
			dateCellStyle_cn.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
			dateCellStyle_cn.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
			dateCellStyle_cn.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
			
			
			// 会议总时长
//			int allDuration=0;
//			Date bgtime=conference.getEndTime();
//			Date edtime=DateFormatUtil.addMinitue(conference.getBeginTime(), -10);
			String confYear=DateFormatUtil.timeStamaps2StrFormatForReport(conference.getBeginTime());
			String confHour=DateFormatUtil.getDurationTimeForReport(conference.getBeginTime(), conference.getConfDuration());
			String confTime=confYear+"    "+confHour;
			String totalDuration=DateFormatUtil.minutes2DataForReport(conference.getConfDuration());
					
			// 会议总人次
			/*cn_row = cn_sheet.getRow(3);
			cn_cell = cn_row.getCell(1);
			if(cn_cell == null) {
				cn_cell = cn_row.createCell(1);
			}
			cn_cell.setCellValue(confDetailCDRList.size());// 会议方数*/		
			/**
			 * 参会者名单
			 */
			if(confDetailCDRList != null && confDetailCDRList.size() > 0) {
				int _rIndex = 6;
				for(ConfDetailCDR confDetialCdr : confDetailCDRList){
					cn_row = cn_sheet.getRow(_rIndex);
					if(cn_row == null) {
						cn_row = cn_sheet.createRow(_rIndex);
					}
					/**
					 * 角色
					 */
					if((cn_cell = cn_row.getCell(0)) == null) {
						cn_cell = cn_row.createCell(0);
					}
					cn_cell.setCellValue(confDetialCdr.getRole());
					cn_cell.setCellStyle(cn_style);
					/**
					 * 姓名
					 */
					if((cn_cell = cn_row.getCell(1)) == null) {
						cn_cell = cn_row.createCell(1);
					}
					cn_cell.setCellValue(confDetialCdr.getBindtel());
					cn_cell.setCellStyle(cn_style);
					/**
					 * 电话
					 */
					if((cn_cell = cn_row.getCell(2)) == null) {
						cn_cell = cn_row.createCell(2);
					}
					cn_cell.setCellValue(confDetialCdr.getClientcode());// 电话
					cn_cell.setCellStyle(cn_style);
					/**
					 * 入会时间
					 */
					if((cn_cell = cn_row.getCell(3)) == null) {
						cn_cell = cn_row.createCell(3);
					}
//					cn_cell.setCellValue(confDetialCdr.getOnlinetime());
					cn_cell.setCellValue(DateFormatUtil.data2StringForReport(confDetialCdr.getOnlinetime()));
					cn_cell.setCellStyle(dateCellStyle_cn);
					/**
					 * 退会时间
					 */
					if((cn_cell = cn_row.getCell(4)) == null) {
						cn_cell = cn_row.createCell(4);
					}
//					cn_cell.setCellValue(confDetialCdr.getOfflinetime());
					cn_cell.setCellValue(DateFormatUtil.data2StringForReport(confDetialCdr.getOfflinetime()));
					cn_cell.setCellStyle(dateCellStyle_cn);
					/**
					 * 与会时长
					 */
					if((cn_cell = cn_row.getCell(5)) == null) {
						cn_cell = cn_row.createCell(5);
					}
					//会议时长=会议开始时间到会议结束时间的时长
//					if(bgtime.after(confDetialCdr.getMsgstarttime())){
//						bgtime = confDetialCdr.getMsgstarttime();
//					}
//					if(edtime.before(confDetialCdr.getMsgendtime())){
//						edtime = confDetialCdr.getMsgendtime();
//					}
//					allDuration =DateFormatUtil.compareToMinute(bgtime, edtime);
					
					cn_cell.setCellValue(DateFormatUtil.compareToMinute(confDetialCdr.getOnlinetime(), confDetialCdr.getOfflinetime()));
					cn_cell.setCellStyle(cn_style);
					_rIndex++;
				}
			}
			
			// 会议时间
			cn_row = cn_sheet.getRow(1);
			cn_cell = cn_row.getCell(1);
			if(cn_cell == null) {
				cn_cell = cn_row.createCell(1);
			}
//			cn_cell.setCellValue(DateFormatUtil.DateToStr(bgtime, "yyyy-MM-dd HH:mm:ss")+ "——"+ DateFormatUtil.DateToStr(edtime, "yyyy-MM-dd HH:mm:ss"));
			cn_cell.setCellValue(confTime);
			cn_cell.setCellStyle(cn_style);
			
			//每个参与者时长的总和，设置到会议总时长
			cn_row = cn_sheet.getRow(2);
			cn_cell = cn_row.getCell(1);
			if(cn_cell == null) {
				cn_cell = cn_row.createCell(1);
			}
//			cn_cell.setCellValue(allDuration);
			cn_cell.setCellValue(totalDuration);
			
			cnReport = FileUtils.createFile(reportPath + "cn-" + billingCode + fileType);
			chinaReport.write(new FileOutputStream(cnReport));
			// 写入本地报告表中
			insertLocalReport(confDetailCDRList);
			logger.info("end write excel report files, billingcode : {}", billingCode);
		}catch (Exception e){
			logger.error("会议结束，会后报告生成失败 billingCode " + billingCode);
			e.printStackTrace();
		}
	}

	/**
	 * 写入本地报告表中
	 * 
	 * @param confDetailCDRList
	 */
	private void insertLocalReport(List<ConfDetailCDR> confDetailCDRList){
		ConferenceReportInfo conferenceReport = new ConferenceReportInfo();
		ConferenceInfo conf = new ConferenceInfo();

		Timestamp now = new Timestamp(System.currentTimeMillis());
		Long recordId = Long.valueOf(confDetailCDRList.get(0).getRecordid());
		Long confId = confDetailCDRList.get(0).getConferenceid();
		conf.setConfId(confId);
		conferenceReport.setReportId(recordId);
		conferenceReport.setIsValid(0);
		conferenceReport.setCreateTime(now);
		conferenceReport.setReportLanguage(0);
		conferenceReport.setReportName(cnReport.getName());
		conferenceReport.setReportUrl(cnReport.getAbsolutePath().toString());
		conferenceReport.setConfInfo(conf);
		/**
		 * 保存入库
		 */
		reportDao.save(conferenceReport);
	}
}