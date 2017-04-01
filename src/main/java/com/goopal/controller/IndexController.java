package com.goopal.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goopal.entity.TblConfigure;
import com.goopal.entity.TblUserInfo;
import com.goopal.service.IndexService;
import com.goopal.tools.controller.BaseController;

@Controller
public class IndexController extends BaseController {
	
	@Autowired
	private IndexService indexService;
	
	/**
	 * 
	* @Title: index 
	* @Description:二维码扫描进入页面
	* @author David
	* @param 
	* @return String 
	* @throws
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request) {
		
		return "index";
	}
	
	/**
	 * 
	* @Title: main
	* @Description: 输入手机号码加入互助计划
	* @author David
	* @param 
	* @return String 
	* @throws
	 */
	@RequestMapping("main")
	public String main(HttpServletRequest request) {
		Map<String,Object> params = getParams(request);
		String phone = String.valueOf(params.get("phone"));
		TblConfigure config = indexService.audphone();
		if (phone.equals(config.getConfValue())) {
			TblUserInfo user = this.indexService.selectNoState(phone);
			request.getSession().setAttribute("user", user);
			return "redirect:auditing.do"; 
		} else {
			Map<String,Object> mutua = this.indexService.mutualInfo();
			indexService.main(params,request);
			request.setAttribute("mutua", mutua);
			return "main";
		}
	}
	
	/**
	 * 
	* @Title: join 
	* @Description:立即加入
	* @author David
	* @param 
	* @return String 
	* @throws
	 */
	@RequestMapping("join")
	public String join(HttpServletRequest request) {
		Map<String,Object> params = getParams(request);
		TblUserInfo user = (TblUserInfo) request.getSession().getAttribute("user");
		TblUserInfo info = indexService.join(params, user);
		request.setAttribute("userInfo", info);
		return "confirm";
	}
	
	/**
	 * 
	* @Title: buy 
	* @Description:确认购买
	* @author David
	* @param 
	* @return String 
	* @throws
	 */
	@ResponseBody
	@RequestMapping("buy")
	public String buy(HttpServletRequest request) {
		Map<String,Object> params = getParams(request);
		TblUserInfo user = (TblUserInfo) request.getSession().getAttribute("user");
		return indexService.confirm(params, user);
	}
	
	/**
	 * 
	* @Title: apply 
	* @Description:确认购买提示页面
	* @author David
	* @param 
	* @return String 
	* @throws
	 */
	@RequestMapping("apply")
	public String apply(HttpServletRequest request) {
		return "apply";
	}
	
	/**
	 * 
	* @Title: checkPhone 
	* @Description:校验手机号是否被注册
	* @author David
	* @param 
	* @return String 
	* @throws
	 */
	@ResponseBody
	@RequestMapping("checkPhone")
	public String checkPhone(HttpServletRequest request) {
		Map<String,Object> params = getParams(request);
		return indexService.checkPhone(params);
	}
	
	
	
	
		
	/**
	 * 
	* @Title: checkByJoin 
	* @Description:验证用户是否已经加入互助计划
	* @author David
	* @param 
	* @return String 
	* @throws
	 */
	@ResponseBody
	@RequestMapping("checkByJoin")
	public String checkByJoin(HttpServletRequest request) {
		TblUserInfo user = (TblUserInfo) request.getSession().getAttribute("user");
		return this.indexService.checkByJoin(user);
	}
	
	
	
}	
