package com.exex;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.exex.dao.Comment;
import com.exex.dao.CommentDao;
import com.exex.dao.Reply;
import com.exex.dao.ReplyDao;

/**
 * Servlet implementation class ReplyServlet
 */
@WebServlet("/reply")
public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String resource = "config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession session = sqlSessionFactory.openSession();

		ReplyDao repDao = session.getMapper(ReplyDao.class);
		Reply rep = new Reply();

		int comId = Integer.parseInt(request.getParameter("comId"));

		List<Reply> reply = repDao.findAllReply();
		int repId = 1;
		for (Reply r : reply) {
			if (r.getComId() == comId) {
				repId++;
			}
		}
		rep.setRepId(repId);
		rep.setRepDate(new Date());
		rep.setRepName(request.getParameter("repName"));
		rep.setRepContent(request.getParameter("repContent"));
		rep.setComId(comId);

		repDao.insert(rep);

		reply.add(rep);
		request.setAttribute("reply", reply);

		// コメント一覧も取得しないと表示できない
		CommentDao comDao = session.getMapper(CommentDao.class);
		List<Comment> list = comDao.findAllComment();
		request.setAttribute("list", list);

		session.commit();
		session.close();

		RequestDispatcher rd = request.getRequestDispatcher("/comment.jsp");
		rd.forward(request, response);
		return;
	}

}
