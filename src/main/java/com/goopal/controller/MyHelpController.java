package com.goopal.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goopal.entity.TblUserInfo;
import com.goopal.entity.TblUserTransaction;
import com.goopal.service.IndexService;
import com.goopal.tools.controller.BaseController;

@Controller
public class MyHelpController extends BaseController {
	
	@Autowired
	private IndexService indexService;
	
	/**
	 * 
	* @Title: publicity 
	* @Description:我的互助
	* @author David
	* @param 
	* @return String 
	* @throws
	 */
	@RequestMapping("publicity")
	public String publicity(HttpServletRequest request) {
		Map<String,Object> params = getParams(request);
		TblUserInfo user = (TblUserInfo) request.getSession().getAttribute("user");
		params.put("phone", user.getPhoneNum());
		List<TblUserTransaction> result = this.indexService.selectByTransacPhone(params);
		List<Map<String,Object>> publicList = this.indexService.selectByTrxStateByPhone(params);
		Map<String,Object> balance = this.indexService.getBalance(user);
		List<Map<String,Object>> transaction = this.indexService.selectBytranToPhone(user.getPhoneNum());
		request.setAttribute("publicList", publicList);
		request.setAttribute("transaction", transaction);
		request.setAttribute("account", balance);
		request.setAttribute("result", result);
		request.setAttribute("tranCount", transaction.size());
		return "publicity";
	}
	
	/**
	 * 
	* @Title: applayHelp 
	* @Description:申请帮助,更改状态
	* @author David
	* @param 
	* @return String 
	* @throws
	 */
	@ResponseBody
	@RequestMapping("applayHelp")
	public int applayHelp(HttpServletRequest request) {
		Map<String,Object> params = getParams(request);
		return this.indexService.updateByTrxState(params);
	}
	
	/**
	 * 
	* @Title: publicInfo 
	* @Description:公示信息
	* @author David
	* @param 
	* @return String 
	* @throws
	 */
	@RequestMapping("publicInfo")
	public String publicInfo(HttpServletRequest request) {
		Map<String,Object> mutua = this.indexService.mutualInfo();
		Map<String,Object> tranNum = this.indexService.selectNumTrans();
		List<Map<String,Object>> tranAll = this.indexService.selectAllTrance();
		List<Map<String,Object>> trxstate = this.indexService.selectByTrxState();
		request.setAttribute("trxstates", trxstate);
		request.setAttribute("tranAll", tranAll);
		request.setAttribute("mutua", mutua);
		request.setAttribute("tranNum", tranNum);
		return "publicInfo";
	}
	
	/**
	 * 
	* @Title: auditing 
	* @Description:李远审核页面
	* @author David 
	* @param 
	* @return String 
	* @throws
	 */
	@RequestMapping("auditing")
	public String auditing(HttpServletRequest request) {
		List<Map<String,Object>> result = this.indexService.selectByExamine();
		List<Map<String,Object>> tranAll = this.indexService.selectAllTrance();
		request.setAttribute("tranAll", tranAll);
		request.setAttribute("result", result);
		return "auditing";
	}
	
	/**
	 * 
	* @Title: confrimAudit 
	* @Description:确认审核页面
	* @author David
	* @param 
	* @return String 
	* @throws
	 */ 
	@ResponseBody
	@RequestMapping("confrimAudit")
	public String confrimAudit(HttpServletRequest request) {
		Map<String,Object> params = getParams(request);
		return indexService.selectPayment(params);
	}
	
	
}
