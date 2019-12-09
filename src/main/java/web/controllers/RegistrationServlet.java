package web.controllers;

import dao.SpecialityDao;
import dao.UserDao;
import entities.User;
import enums.Role;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    private UserDao userDao = new UserDao();
    private SpecialityDao specialityDao = new SpecialityDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        req.setAttribute("user", user);
        req.setAttribute("specialityNames", specialityDao.getAllNames());
            setNextPage("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = setValuesToUser(req);
        userDao.saveEntity(user);
        req.getSession().setAttribute("User", user);
//        setNextPage("/university").forward(req, resp);
        resp.sendRedirect("/university");
    }

    private RequestDispatcher setNextPage(String nextPageName) {
        return getServletContext().getRequestDispatcher(nextPageName);
    }

    private User setValuesToUser(HttpServletRequest request) {
        String name = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String specialityName = request.getParameter("specialityName");
        return new User().setFirstName(name).setLastName(lastName).setEmail(email).setSpecialityName(specialityName)
                .setPassword(password).setRole(Role.STUDENT);
    }


}
