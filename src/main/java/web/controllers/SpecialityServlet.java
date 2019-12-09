package web.controllers;

import dao.ExamDao;
import entities.Exam;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (urlPatterns = {"/speciality", "/speciality/*"})
public class SpecialityServlet  extends HttpServlet {

    private ExamDao examDao = new ExamDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nextJSPadress = "/speciality.jsp";
        List<Exam> all = examDao.getAll();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSPadress);
        req.setAttribute("exams", all);
        dispatcher.forward(req, resp);
    }
}
