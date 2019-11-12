package controllers;

import dao.StudentDao;
import entities.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/students", "/deletestudent/*"})
public class StudentServlet extends HttpServlet {

    StudentDao studentDao = new StudentDao();

    private void forwardListStudents(HttpServletRequest req, HttpServletResponse resp, List<Student> studentsList)
            throws ServletException, IOException {
        String nextJSPadress = "/student.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSPadress);
        req.setAttribute("studentsList", studentsList);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        deleteStudent(req, resp);

    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String path = req.getRequestURI();
        if (isDeleteStudent(path)) {
//            studentDao.deleteEntity(studentDao.getEntityById(getIdFromPath(path)).get());

            //test. Method is working. now I need to write correct deleteMethod In Dao. update is just to test how it works
            studentDao.updateEntity(studentDao.getEntityById(getIdFromPath(path)).get().setFirstName("Different"));
        }
        List<Student> allstudents = studentDao.getAll();
        forwardListStudents(req, resp, allstudents);
    }

    private boolean isDeleteStudent(String pathInfo) {
        return splitPathBySlashes(pathInfo)[1].equals("deletestudent");
    }

    private Long getIdFromPath(String pathInfo) {
        return Long.valueOf(splitPathBySlashes(pathInfo)[2]);
    }

    private String[] splitPathBySlashes(String pathInfo) {
        return pathInfo.split("/");
    }
}
