//package com.revature.servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.revature.model.Employee;
//import com.revature.service.ERSService;
//
///**
// * Servlet implementation class EmployeeServlet
// */
//public class EmployeeServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public EmployeeServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html");
//		ERSService ers = ERSService.getERSService(); 
//		PrintWriter pw1 = response.getWriter();
//		List<Employee> emps = ers.viewAllEmployees();
//		pw1.println("<div>");
//		pw1.println("<table><tr><th>EmpID</th><th>Name</th><th>Email</th><th>Username</th><th>Password</th><th>Manager?</th></tr>");
//		
//		for (Employee emp : emps) {
//			pw1.println("<tr><td>" + emp.getEmpID() + "</td><td>" + emp.getName() + "</td><td>" + emp.getEmail() 
//			+ "</td><td>" + emp.getUsername() + "</td><td>" + emp.getPassword() + "</td><td>" + emp.isManager() + "</tr>");
//		}
//		
//		pw1.println("</table></div><br><a href='/ERSProject/managerOptions.html'>Click here to go to the options page.</a>");
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
