package controllers;

import dao.UserDao;
import entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/exam", "/deletestudent/*"})
public class ExamServlet extends HttpServlet {

    UserDao userDao = new UserDao();

    private void forwardListStudents(HttpServletRequest req, HttpServletResponse resp, List<User> studentsList)
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
        String  path = req.getRequestURI();
        if (isDeleteStudent(path)) {
//            studentDao.deleteEntity(studentDao.getEntityById(getIdFromPath(path)).get());

            //test. Method is working. now I need to write correct deleteMethod In Dao. update is just to test how it works
            userDao.updateEntity(userDao.getEntityById(getIdFromPath(path)).get().setFirstName("Different"));
        }
        List<User> allstudents = userDao.getAll();
        forwardListStudents(req, resp, allstudents);
    }

    private boolean isDeleteStudent(String pathInfo) {
        return "deletestudent".equals(PathUtil.getElementOfPathArray(pathInfo, 1));
    }

    private Long getIdFromPath(String pathInfo) {
        return Long.valueOf(PathUtil.getElementOfPathArray(pathInfo, 2));
    }
}
