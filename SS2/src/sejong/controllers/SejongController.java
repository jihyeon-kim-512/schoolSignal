package sejong.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crawl.News;
import sejong.models.SejongDAO;
import sejong.models.SejongDTO;


@WebServlet("/Sejong")
public class SejongController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	      News news = News.getInstance();
	      News.news_crawl();
	      
	    SejongDAO deptDAO = SejongDAO.getInstance();
		List<SejongDTO> list = deptDAO.getDeptList();

		request.setAttribute("list", list);
		request.setAttribute("title", "학교위치");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/schoolSignal/kakaoAPI.jsp");
		dispatcher.forward(request, response);
	}

}
