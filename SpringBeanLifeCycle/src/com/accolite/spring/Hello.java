package com.accolite.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//configuring bean life cycle by implementing interface- not recommended
public class Hello implements InitializingBean,DisposableBean{

	@Override
	public void destroy() throws Exception {
		System.out.println("inside destroy method");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("inside init method");
	}

}
