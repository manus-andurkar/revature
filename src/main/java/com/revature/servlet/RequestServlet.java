package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Request;
import com.revature.service.ERSService;

public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ERSService ers = null;
       
    public RequestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("viewAll") != null) {
			response.setContentType("text/html");
			ers = ERSService.getERSService(); 
			PrintWriter pw1 = response.getWriter();
			List<Request> list = ers.viewAllRequests();
			pw1.println("<div>");
			pw1.println("<table><tr><th>Request ID</th><th>Title</th><th>Body</th><th>Resolved</th><th>EmpID</th></tr>");
				
			for (Request req : list) {
				pw1.println("<tr><td>" + req.getRequestID() + "</td><td>" + req.getTitle() + "</td><td>" +  
					req.getBody() + "</td><td>" + req.isResolved() + "</td><td>" + req.getEmpID() + "</td></tr>");
			}
			pw1.println("</div><br><a href='/ERSProject/managerOptions.html'>Click here to go to the options page.</a>");
		} 
		
		else if (request.getParameter("changeReqStatus") != null) {
			response.setContentType("text/html");
			ers = ERSService.getERSService();
			PrintWriter pw1 = response.getWriter();
			String resolved = request.getParameter("resolved");
			int reqId = Integer.parseInt(request.getParameter("reqID"));
			ers.changeReqStatus(resolved, reqId);
			pw1.println("</div><br>You have changed the status of the request! <a href='/ERSProject/managerOptions.html'>Click here to go to the options page.</a>");
		}
		
		else {
			response.setContentType("text/html");
			Integer id = Integer.parseInt(request.getParameter("empID"));
			ers = ERSService.getERSService(); 
			PrintWriter pw1 = response.getWriter();
			List<Request> list = ers.viewRequests(id);
			pw1.println("<div>");
			pw1.println("<table><tr><th>Request ID</th><th>Title</th><th>Body</th><th>Resolved</th><th>EmpID</th></tr>");
				
			for (Request req : list) {
				pw1.println("<tr><td>" + req.getRequestID() + "</td><td>" + req.getTitle() + "</td><td>" +  
					req.getBody() + "</td><td>" + req.isResolved() + "</td><td>" +  id + "</td></tr>");
			}
			pw1.println("</div><br><a href='/ERSProject/options.html'>Click here to go to the options page.</a>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int id = Integer.parseInt(request.getParameter("empID"));
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		boolean resolved = false;
		Request req = new Request(0, title, body, resolved, id);
		ers = ERSService.getERSService();
		ers.submitRequest(req, id);
		response.getWriter().println("Request submitted! <a href='/ERSProject/options.html'>"
				+ "Click here to go to the options page.</a>");
	}
	
	protected void doDelete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
