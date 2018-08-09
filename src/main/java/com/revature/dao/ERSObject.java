package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.jdbcutil.JDBCConnection;
import com.revature.model.*;

public class ERSObject implements ERSObjectInt {
	public static ERSObject ersObject;
	
	private ERSObject() {
		
	}
	
	public static ERSObject getERSObject() {
		if (ersObject == null) {
			ersObject = new ERSObject();
		}
		
		return ersObject;
	}
	
	public boolean login(String us, String pw) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = null;
			
			String endpoint = "jdbc:oracle:thin:@newdbinstance.ctly377z2znq.us-east-1.rds.amazonaws.com:1521:ORCL";
			String username = "manus8";
			String password = "835?4fXw";
			
			conn = DriverManager.getConnection(endpoint, username, password);
			String sql = "SELECT username, password FROM employee WHERE username = ? AND password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, us);
			ps.setString(2, pw);
			ResultSet rs = ps.executeQuery();
			
			if (rs.getFetchSize() == 0) {
				return false;
			}
			
			else {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void logout() {
		
	}
	
	public void submitRequest(Request r, int id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = null;
			
			String endpoint = "jdbc:oracle:thin:@newdbinstance.ctly377z2znq.us-east-1.rds.amazonaws.com:1521:ORCL";
			String username = "manus8";
			String password = "835?4fXw";
			
			conn = DriverManager.getConnection(endpoint, username, password);
			String sql = "call add_request (?, ?, ?, ?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, id);
			cs.setString(2, r.getTitle());
			cs.setString(3, r.getBody());
			cs.setString(4, "" + r.isResolved());
				
			cs.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Request> viewRequests(int id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = null;
			
			String endpoint = "jdbc:oracle:thin:@newdbinstance.ctly377z2znq.us-east-1.rds.amazonaws.com:1521:ORCL";
			String username = "manus8";
			String password = "835?4fXw";
			
			conn = DriverManager.getConnection(endpoint, username, password);
			String sql = "select * from request where empid = ? order by resolved";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			List<Request> requests = new ArrayList<Request>();
			
			while (rs.next()) {
				boolean resolved = false;
				
				if (rs.getString("RESOLVED").equals("true")) {
					resolved = true;
				}
				
				
				requests.add(new Request(rs.getInt("REQID"),
						rs.getString("TITLE"),
						rs.getString("BODY"),
						resolved, rs.getInt("EMPID")));
				
			}
			return requests;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Request> viewAllRequests() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = null;
			
			String endpoint = "jdbc:oracle:thin:@newdbinstance.ctly377z2znq.us-east-1.rds.amazonaws.com:1521:ORCL";
			String username = "manus8";
			String password = "835?4fXw";
			
			conn = DriverManager.getConnection(endpoint, username, password);
			String sql = "select * from request";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<Request> requests = new ArrayList<Request>();
			
			while (rs.next()) {
				boolean resolved = false;
				if (rs.getString("RESOLVED").equals("true")) {
					resolved = true;
				}
				
				requests.add(new Request(rs.getInt("REQID"),
						rs.getString("TITLE"),
						rs.getString("BODY"),
						resolved, rs.getInt("EMPID")));
				
			}
			return requests;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Employee viewInfo(int id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = null;
			String endpoint = "jdbc:oracle:thin:@newdbinstance.ctly377z2znq.us-east-1.rds.amazonaws.com:1521:ORCL";
			String username = "manus8";
			String password = "835?4fXw";
			
			conn = DriverManager.getConnection(endpoint, username, password);
			String sql = "select * from employee where empid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Employee emp = null;
			
			while (rs.next()) {
				boolean manager = false;
				
				if (rs.getString("MANAGER").equals("true")) {
					manager = true;
				}
				
				emp = new Employee(rs.getInt("EMPID"),
						rs.getString("NAME"),
						rs.getString("EMAIL"),
						rs.getString("USERNAME"),
						rs.getString("PASSWORD"),
						rs.getBoolean("MANAGER"));
			}
			
			return emp;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Employee viewInfo(String us, String pw) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = null;
			String endpoint = "jdbc:oracle:thin:@newdbinstance.ctly377z2znq.us-east-1.rds.amazonaws.com:1521:ORCL";
			String username = "manus8";
			String password = "835?4fXw";
			
			conn = DriverManager.getConnection(endpoint, username, password);
			String sql = "select * from employee where username = ? and password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, us);
			ps.setString(2, pw);
			ResultSet rs = ps.executeQuery();
			List<Employee> emps = new ArrayList<Employee>();
			Employee emp = null;
			
			while (rs.next()) {
				boolean manager = false;
				
				if (rs.getString("MANAGER").equals("true")) {
					manager = true;
				}
				emp = new Employee(rs.getInt("EMPID"),
						rs.getString("NAME"),
						rs.getString("EMAIL"),
						rs.getString("USERNAME"),
						rs.getString("PASSWORD"),
						rs.getBoolean("MANAGER"));
				emps.add(emp);
			}
			
			return emp;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void changeReqStatus(String res, int id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = null;
			
			String endpoint = "jdbc:oracle:thin:@newdbinstance.ctly377z2znq.us-east-1.rds.amazonaws.com:1521:ORCL";
			String username = "manus8";
			String password = "835?4fXw";
			
			conn = DriverManager.getConnection(endpoint, username, password);
			String sql = "update request set resolved = ? where reqid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, res);
			ps.setInt(2, id);
				
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	
	public List<Employee> viewAllEmployees() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = null;
			String endpoint = "jdbc:oracle:thin:@newdbinstance.ctly377z2znq.us-east-1.rds.amazonaws.com:1521:ORCL";
			String username = "manus8";
			String password = "835?4fXw";
			
			conn = DriverManager.getConnection(endpoint, username, password);
			String sql = "select * from employee";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Employee> emps = new ArrayList<Employee>();
			Employee emp = null;
			
			while (rs.next()) {
				boolean manager = false;
				
				if (rs.getString("MANAGER").equals("true")) {
					manager = true;
				}
				
				emp = new Employee(rs.getInt("EMPID"),
						rs.getString("NAME"),
						rs.getString("EMAIL"),
						rs.getString("USERNAME"),
						rs.getString("PASSWORD"),
						rs.getBoolean("MANAGER"));
				emps.add(emp);
			}
			
			return emps;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void updateInfo(String name, String email, String us, String pw, int id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = null;
			String endpoint = "jdbc:oracle:thin:@newdbinstance.ctly377z2znq.us-east-1.rds.amazonaws.com:1521:ORCL";
			String username = "manus8";
			String password = "835?4fXw";
			
			conn = DriverManager.getConnection(endpoint, username, password);
			String sql = "update employee set name = ?, email = ?, username = ?, password = ? where empid = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, us);
			ps.setString(4, pw);
			ps.setInt(5, id);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public void email() {
		
	}
	
}
