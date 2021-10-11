package edu.karamoozi.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletExample", value = "/add")
public class ServletExample extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/plain");
//        String e = request.getParameter("e");
        response.getWriter().print("1");
    }

}
