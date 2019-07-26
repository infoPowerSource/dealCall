package com.deal.entity.conference;

public class ConfQueryDTO{
	//客服查询任务量实体
	private String begin_time;//开会日期
	private int all_num;//会议总数
	private int op_num;//需要客服的会议数
	
	public String getBegin_time(){
		return begin_time;
	}
	public void setBegin_time(String begin_time){
		this.begin_time = begin_time;
	}
	public int getAll_num(){
		return all_num;
	}
	public void setAll_num(int all_num){
		this.all_num = all_num;
	}
	public int getOp_num(){
		return op_num;
	}
	public void setOp_num(int op_num){
		this.op_num = op_num;
	}
	
	
}
