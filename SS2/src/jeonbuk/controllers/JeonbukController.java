package jeonbuk.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crawl.News;
import jeonbuk.models.JeonbukDAO;
import jeonbuk.models.JeonbukDTO;


@WebServlet("/Jeonbuk")
public class JeonbukController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	      News news = News.getInstance();
	      News.news_crawl();
	      
	    JeonbukDAO deptDAO = JeonbukDAO.getInstance();
		List<JeonbukDTO> list = deptDAO.getDeptList();

		request.setAttribute("list", list);
		request.setAttribute("title", "학교위치");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/schoolSignal/kakaoAPI.jsp");
		dispatcher.forward(request, response);
	}

}
