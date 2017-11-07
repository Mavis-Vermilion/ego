package cn.ego.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.ego.portal.service.UserService;
import cn.ego.portal.utils.CookieUtils;
import cn.ego.vo.UserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Value("${COOKIE_TOKEN}")
	private String COOKIE_TOKEN;
	@Value("${SSO_URL}")
	private String SSO_URL;
	@Value("${SSO_LOGIN_ADDRESS}")
	private String SSO_LOGIN_ADDRESS;
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURL().toString();
		System.err.println(url);
		String token = CookieUtils.getCookieValue(request, COOKIE_TOKEN);
		
		UserVo user = null;
		
		if(StringUtils.isNotBlank(token)) 
			user = userService.findUserByToken(token);
		
		if(null != user)
			return true;
		
		response.sendRedirect(SSO_URL+SSO_LOGIN_ADDRESS+"?redirect="+url);
		
		return false;
	}

	
}
