package com.goopal.service;

import java.util.List;

public interface SocketService {
	
	/**
	 * 
	* @Title: send 
	* @Description: 获取接口数据
	* @author David
	* @param 
	* @return String 
	* @throws
	 */
	String send(String method,List<Object> params);
	
}
