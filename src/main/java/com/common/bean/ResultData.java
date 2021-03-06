package com.common.bean;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="统一响应对象", description="")
public class ResultData implements Serializable{
	
	public static ResultData success() {
		return success("OK");
	}
	
	public static ResultData success(String msg) {
		ResultData rs = new ResultData();
		rs.setSuccess(msg);
		return rs;
	}
	
	
	public static ResultData fail() {
		return fail("ERROR");
	}
	
	public static ResultData fail(String msg) {
		ResultData rs = new ResultData();
		rs.setError(msg);
		return rs;
	}
	
	@ApiModelProperty("真实数据")
	private Object data;
	
	@ApiModelProperty("令牌")
	private String token;
	
	@ApiModelProperty("数据总数，用于分页")
	private int total;
	 
	@ApiModelProperty("成功时返回的消息")
	private String success = "OK";
	
	@ApiModelProperty("失败时返回的消息")
	private String error;
	
	
	@ApiModelProperty("请求码：0  成功  -1失败")
	private int code = 0;
	
	@ApiModelProperty("业务状态码： 挺多。")
	private String serviceCode;

	
	private long mills;
	
	
	public long getMills() {
		return mills;
	}

	public void setMills(long mills) {
		this.mills = mills;
	}

	public Object getData() {
		return data;
	}

	public ResultData setData(Object data) {
		this.data = data;
		return this;
	}
	//重载 setData 方法统一返回分页 List 对象
	public ResultData setData(List list) {
		 PageInfo page = new PageInfo(list);
		return this.setData(page);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
		this.setCode( 0 );
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
		this.setCode( -1 );
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	@Override
	public String toString() {
		return "ResultData [data=" + data + ", token=" + token + ", total=" + total + ", success=" + success
				+ ", error=" + error + ", code=" + code + ", serviceCode=" + serviceCode + ", mills=" + mills + "]";
	}

	 
	
	
}
