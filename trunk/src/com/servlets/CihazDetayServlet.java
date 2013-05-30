package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.CihazDAO;
import com.entity.Cihaz;


@WebServlet("/cihazdetay")
public class CihazDetayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cihazid = Integer.valueOf(request.getParameter("cihazid"));
		Cihaz cihaz = new CihazDAO().CihazDetayiniGetir(cihazid);
		request.setAttribute("cihaz", cihaz);
		request.getRequestDispatcher("cihazdetay.jsp").forward(request,response);
	}

}
