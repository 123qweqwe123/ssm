package com.lmbx.ssm.web.service;

import java.util.List;
import java.util.Map;

import com.lmbx.ssm.web.daomain.CustomFunction;


public interface CustomFunctionService{


	public CustomFunction findById(String id);
	
	public CustomFunction findByUserAndCode(String userId, String code);
	
	public  List<CustomFunction>  findByUerId(String userId);

	public  Map findKeyMapByUerId(String userId);
	
	public  Map findVoMapByUerId(String userId);	
    
    public void saveOne(CustomFunction vo);
	
	
}
