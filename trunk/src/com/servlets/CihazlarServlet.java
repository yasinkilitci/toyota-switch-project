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


@WebServlet("/cihazlar")
public class CihazlarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int turid = Integer.valueOf(request.getParameter("turid"));
		
		ArrayList<Cihaz> cihazlar = new CihazDAO().TureAitcihazlariGetir(turid);
		request.setAttribute("cihazlar", cihazlar);
		request.getRequestDispatcher("cihazlar.jsp").forward(request,response);
	}

}
