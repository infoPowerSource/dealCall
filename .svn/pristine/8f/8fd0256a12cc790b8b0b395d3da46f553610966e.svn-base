package com.deal.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ExcelUtils{
	public static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

	public static Map<String, Object> readExcelData(String fileName, String filePath){
		Workbook wb = null;
		Map<String,Object> resultMap=Maps.newHashMap();
		try{
			FileInputStream fileStream = new FileInputStream(filePath);
			if (fileName.toLowerCase().endsWith("xlsx")) {
				  wb =new XSSFWorkbook(fileStream);
			}else if (fileName.toLowerCase().endsWith("xls")) {
				  wb = new HSSFWorkbook(fileStream);
			}
			Sheet sheet = wb.getSheetAt(0);
			resultMap.put("confName",sheet.getRow(0).getCell(1).toString());
			resultMap.put("confTime",sheet.getRow(1).getCell(1).toString());
			resultMap.put("confDuration",sheet.getRow(2).getCell(1).toString());
			List<Map<String,String>> custList=Lists.newArrayList();
			for(int i=6;i<=sheet.getLastRowNum();i++){
				Row row = sheet.getRow(i);
				if(row!=null){
					if(StringUtils.isEmptyString(row.getCell(0).toString())){
						continue;
					}
					Map<String,String> custMap=Maps.newHashMap();
					custMap.put("role",row.getCell(0).toString());
					custMap.put("name",row.getCell(1).toString());
					custMap.put("tel", row.getCell(2).toString());
					custMap.put("inTime",row.getCell(3).toString());
					custMap.put("outTime",row.getCell(4).toString());
					custMap.put("durTime",row.getCell(5).toString());
					custList.add(custMap);
				}
			}
			resultMap.put("custList",custList);
		}catch (IOException e){
			e.printStackTrace();
			logger.error("读取Excel出错,fileName:"+fileName+"文件路径filePath:"+filePath+"错误信息"+e.getMessage());
		}
		return resultMap;
	}

}
