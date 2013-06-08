package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DeviceDAO;
import com.entity.Device;


@WebServlet("/cihazdetay")
public class DeviceDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cihazid = Integer.valueOf(request.getParameter("cihazid"));
		Device cihaz = new DeviceDAO().CihazDetayiniGetir(cihazid);
		request.setAttribute("cihaz", cihaz);
		request.getRequestDispatcher("cihazdetay.jsp").forward(request,response);
	}

}
