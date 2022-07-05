package com.accolite.spring;

import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentDAO {
	//implementing bean life cycle through 1. annotation-- postconstruct, preconstruct
	//2. xml approach-- init method,destroy method
	//3. by implementing interface
	
	private String driver;
	private String url;
	private String userName;
	private String password;
	
	Connection connection;

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	//@PostConstruct
	public void init () throws ClassNotFoundException, SQLException {
		openConnection();
	}
	
	public void openConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		connection = DriverManager.getConnection(url, userName, password);
	}

	public void selectAllRows() throws Exception {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from studentdao.student");

		while (rs.next()) {
			int studentId = rs.getInt(1);
			String studentName = rs.getString(2);
			Double studentFee = rs.getDouble(3);
			String foodType = rs.getString(4);

			System.out.println(studentId + " " + studentName + " " + studentFee + " " + foodType);
		}
	}

	public void deleteStudentRow(int studentId) throws Exception {
		openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate("delete from studentdao.student where studentId= " + studentId);
		System.out.println("Student deleted with id " + studentId);
		closeConnection();
	}
	
	public void closeConnection() throws SQLException {
		connection.close();
	}
	
	//@PreDestroy
	public void destroy() throws SQLException {
		System.out.println("closing DB connection");
		closeConnection();
	}

}
