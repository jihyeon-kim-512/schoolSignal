package jeju.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crawl.News;
import jeju.models.JejuDAO;
import jeju.models.JejuDTO;


@WebServlet("/Jeju")
public class JejuController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	      News news = News.getInstance();
	      News.news_crawl();
	      
	    JejuDAO deptDAO = JejuDAO.getInstance();
		List<JejuDTO> list = deptDAO.getDeptList();

		request.setAttribute("list", list);
		request.setAttribute("title", "학교위치");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/schoolSignal/kakaoAPI.jsp");
		dispatcher.forward(request, response);
	}

}
