package com.lmbx.ssm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lmbx.ssm.domain.PipCommLcc;
import com.lmbx.ssm.service.LccService;
import com.lmbx.ssm.vo.JqgridResponse;
import com.lmbx.ssm.vo.JqgridResponseContext;

@Controller
@RequestMapping("sys")
public class UserController {
	@Autowired
	private LccService lccService;
	 @RequestMapping
	    public ModelAndView init() throws Exception{
	        ModelAndView mav = new ModelAndView("/sys/user");
	        mav.addObject("orgTypeList", lccService.getDictbyfilter());
	        mav.addObject("aaaa", "aaaa");
	        return mav;
	    }
	 /*
	  * 返回带页码，总页数的集合
	 */
	 @RequestMapping("getList")
	    public JqgridResponse<PipCommLcc> getJqgridResponse() {
	        
	        ModelAndView mav = new ModelAndView("/sys/user");
	        JqgridResponse<PipCommLcc> response = JqgridResponseContext.getJqgridResponse();
		    return response.setRows(lccService.getDictbyfilter());
	    }
		 
	 @RequestMapping("insert")
	    public void insert() {
		 lccService.insert(new PipCommLcc());
	    }
	
	/*@RequestMapping()
    public String init(){
		
		 * 初始化项目url地址对应的jsp页面，后台方便控制访问
		
		return "../views/sys/user";
    }*/
	 
	@SuppressWarnings("unchecked")
	@RequestMapping("user")
    public @ResponseBody ResponseEntity<?> responseEntity(){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("ccount","ssssssssss" );
		return new ResponseEntity<Object>(result, HttpStatus.OK);
    }
	@GetMapping("usersss")
	public @ResponseBody List<PipCommLcc>  test5(String s) {
		lccService.getDict();
		return lccService.getDictbyfilter();
	}
}
