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
 * Servlet implementation class PostServlet
 */
@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String resource = "config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession session = sqlSessionFactory.openSession();

		CommentDao comDao = session.getMapper(CommentDao.class);
		Comment com = new Comment();

		com.setDate(new Date());
		com.setName(request.getParameter("name"));
		com.setContent(request.getParameter("content"));

		comDao.insert(com);

		// オートインクリメントのIDを取得するためにINSERTした後で全件取得する
		List<Comment> list = comDao.findAllComment();
		request.setAttribute("list", list);

		// 返信全件表示（リダイレクト後の表示に必要）
		ReplyDao repDao = session.getMapper(ReplyDao.class);
		List<Reply> reply = repDao.findAllReply();
		request.setAttribute("reply", reply);

		session.commit();
		session.close();

		RequestDispatcher rd = request.getRequestDispatcher("/comment.jsp");
		rd.forward(request, response);
		return;

	}
}
