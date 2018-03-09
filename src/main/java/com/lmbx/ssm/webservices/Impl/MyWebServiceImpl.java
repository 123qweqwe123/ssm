package com.lmbx.ssm.webservices.Impl;

import javax.jws.WebService;

import com.lmbx.ssm.webservices.MyWebService;
@WebService(endpointInterface = "com.lmbx.ssm.webservices.MyWebService")
public class MyWebServiceImpl implements MyWebService{

	@Override
	public String add() {
		
		return "ssss";
	}

}
