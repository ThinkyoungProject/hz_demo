package com.goopal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.goopal.entity.TblUserInfo;


public class WebInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		HttpSession session = request.getSession(true);
		String uriPrefix = request.getContextPath();
		Object obj = session.getAttribute("user");
		String phone = request.getParameter("phone");
		if (obj == null && !StringUtils.isNotBlank(phone)) {
			response.sendRedirect(uriPrefix + "/index.do");
			return false;
		} else {
			TblUserInfo user = (TblUserInfo) obj;
			if (StringUtils.startsWith(uri, uriPrefix)) {
				if (null != obj && StringUtils.isNotBlank(phone)) {
					if (!user.getPhoneNum().equals(phone)) {
						session.removeAttribute("user");
					} 
				} 
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
