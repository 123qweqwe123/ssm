package com.lmbx.ssm.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.google.common.collect.Maps;

@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	private Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
	    /**
	     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
	     * @param binder
	     */
	    @InitBinder
	    public void initBinder(WebDataBinder binder) {}

	    /**
	     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
	     * @param model
	     */
	    @ModelAttribute
	    public void addAttributes(Model model) {
	        model.addAttribute("author", "Magical Sam");
	    }

	    /**
	     * 全局异常捕捉处理
	     * @param ex
	     * @return
	     */
	    @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<?> handlerError(Exception ex, WebRequest request) {
	    	Map<String, Object> resp = Maps.newHashMap();
	        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	        @SuppressWarnings("unused")
			String url = ((ServletWebRequest) request).getRequest().getRequestURI();    // 当前请求url
	        String msg = "sssssssssssssssss";
	        if (ex instanceof UncategorizedSQLException) {
	            // sql 异常
	            msg = "sql异常";
	       /* } else if (ex instanceof ValidatorException) {
	            // 表单校验异常
	            msg = ex.getMessage();
	            status = HttpStatus.BAD_REQUEST;*/
	        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
	            // 媒体类型不被支持
	        } else {
	            msg = ex.getMessage();
	        }
	        ex.printStackTrace();
	        resp.put("message", msg);
	        logger.error("url:{},error:{}", url, ex.getMessage());
	        return ResponseEntity.status(status).body(resp);
	    }

}
