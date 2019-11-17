package controllers;

import dao.SpecialityDao;
import dao.StudentDao;
import entities.Exam;
import entities.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = {"/speciality"})
public class SepcualizationServlet extends HttpServlet {

   SpecialityDao specialityDao = new SpecialityDao();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nextJSPadress = "/specialisation.jsp";
        Set<Exam> exams = specialityDao.getEntityById(1L).get().getExams();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSPadress);
        req.setAttribute("examList", exams);
        dispatcher.forward(req, resp);
    }

}
