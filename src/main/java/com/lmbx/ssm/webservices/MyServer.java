package com.lmbx.ssm.webservices;


import javax.xml.ws.Endpoint;

import com.lmbx.ssm.web.service.Impl.ModuleServiceImpl;

public class MyServer {
	public static void main(String args[]) {
		String address = "http://localhost:8888/ms";
		Endpoint.publish(address, new ModuleServiceImpl());
	}
}
