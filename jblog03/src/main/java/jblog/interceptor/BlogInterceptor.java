package jblog.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jblog.service.BlogService;
import jblog.vo.BlogVo;

public class BlogInterceptor implements HandlerInterceptor {
	
	@Autowired
	private BlogService blogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		BlogVo blogVo = (BlogVo) request.getServletContext().getAttribute("blogVo");
		
		String requestURI = request.getRequestURI();
		String requestParamId = requestURI.split("/")[2];
		
		// 만약 blogVo를 가지고 있었다 하더라도 id가 바뀌면 blogVo를 변경해야 한다.
		if (blogVo == null || blogVo.getBlogId() != requestParamId) {
			blogVo = blogService.getBlogById(requestParamId);
			request.setAttribute("blogVo", blogVo);
		}
		
		return true;
	}
	
	
}
