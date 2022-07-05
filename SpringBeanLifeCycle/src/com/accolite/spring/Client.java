package com.accolite.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
	
	public static void main(String[] args) throws Exception {
		/*StudentDAO dao=new StudentDAO();
		dao.selectAllRows();
		dao.deleteStudentRow(3);*/
		
		ApplicationContext context=new ClassPathXmlApplicationContext("bean_autowire.xml");
		StudentDAO studentDAO= context.getBean("studentDAO",StudentDAO.class);
		studentDAO.selectAllRows(); 
		//container creation -> bean instantiation -> dependency injection -> postconstruct
		//after the object is constructed, @postconstruct will be called
		
		
		Hello hello=context.getBean("hello",Hello.class);
		
		
		
		//after invoking close(), u cannot get bean. since the ioc container is closed, bean invocation cannot happen
		
		
		((ClassPathXmlApplicationContext)context).close(); //in standalone app, creating container 
		//deleting it should be done manually.
		//container should be closed, so that before container is destroyed, @predestroy is called
		
		
		//((ClassPathXmlApplicationContext)context).registerShutdownHook();// this line will be executed only finally(once the main method ends) irrespective of the line where we are inserting it. And we will be able to get beans after this,
		//unlike close
		
		/*
		StudentDAO studentDAO1= context.getBean("studentDAO",StudentDAO.class);
		studentDAO1.selectAllRows();
		*/
	}

}
