package spring.mvc.session17.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("referer", request.getHeader("Refere"));
		mv.addObject("ex", "HandlerExceptionResolver 全局異常捕捉:" + ex);
		mv.setViewName("session17/error");

		// TODO Auto-generated method stub
		return mv;
	}

}
