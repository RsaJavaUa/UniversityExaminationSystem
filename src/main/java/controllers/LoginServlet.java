package controllers;

import dao.UserDao;
import entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setNextPage("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User byEmailPassword = userDao.getByEmailPassword(email, password);
        if (Objects.nonNull(byEmailPassword)) {
            req.getSession().setAttribute("user", byEmailPassword);
            req.getRequestDispatcher("/university").forward(req, resp);
        } else {
            resp.sendRedirect("/404-error");
        }
    }

    private RequestDispatcher setNextPage(String nextPageName) {
        return getServletContext().getRequestDispatcher(nextPageName);
    }

}
