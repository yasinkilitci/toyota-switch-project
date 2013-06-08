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


@WebServlet("/cihazlar")
public class DeviceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int turid = Integer.valueOf(request.getParameter("turid"));
		
		ArrayList<Device> cihazlar = new DeviceDAO().tureAitcihazlariGetir(turid);
		request.setAttribute("cihazlar", cihazlar);
		request.getRequestDispatcher("cihazlar.jsp").forward(request,response);
	}

}
