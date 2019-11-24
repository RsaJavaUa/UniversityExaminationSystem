package controllers;

import dao.SpecialityDao;
import entities.Exam;
import entities.Speciality;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet(urlPatterns = {"/university"})
public class UniversityServlet extends HttpServlet {

    private final SpecialityDao specialityDao = new SpecialityDao();
    private final Map<String, String> namesAndImages = new HashMap<>();


    @Override
    public void init() {
        List<String> allNames = specialityDao.getAllNames();
        List<String> images = new ArrayList<>(allNames.size());
        images.add("https://ibb.co/JrLfDWn");
        images.add("https://ibb.co/mFRGhNP");
        images.add("https://ibb.co/JrLfDWn");
        images.add("https://ibb.co/kJ5Q7rZ");
        for (int i = 0; i < allNames.size(); i++) {
            namesAndImages.put(allNames.get(i), images.get(i));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nextJSPadress = "/university.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSPadress);
        req.setAttribute("namesAndImages", namesAndImages);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nextJSPadress = "/university.jsp";
        List<Speciality> all = specialityDao.getAll();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSPadress);
        req.setAttribute("specialities", all);
        dispatcher.forward(req, resp);
    }

}
