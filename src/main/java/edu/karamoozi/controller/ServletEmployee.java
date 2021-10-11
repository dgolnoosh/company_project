package edu.karamoozi.controller;

import edu.karamoozi.model.dto.EmployeeDTO;
import edu.karamoozi.model.dto.LevelDTO;
import edu.karamoozi.model.service.EmployeeService;
import edu.karamoozi.model.service.LevelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
//import java.sql.Date;
@WebServlet("/employee")
public class ServletEmployee extends HttpServlet {
    EmployeeService employeeService=new EmployeeService();
    LevelService levelService=new LevelService();
     protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         EmployeeDTO employeeDTO=new EmployeeDTO();
         String name = request.getParameter("Name");
         name = name != null && !name.isEmpty() ? name : "";
         String family = request.getParameter("Family");
         family = (family != null) && !family.isEmpty() ? family : "";

            String date1=request.getParameter("Date1");
            String date2=request.getParameter("Date2");
         date1 = date1 != null && !date1.isEmpty() ? date1 : "";
         date2 = date2 != null && !date2.isEmpty() ? date2 : "";
         String levelid=request.getParameter("Level");
         levelid = levelid != null && !levelid.isEmpty() ? levelid : "0";
         int levelid1= Integer.parseInt(levelid);
//         int levelid=0;
//         java.sql.Date sqlDate1 = null;
//         java.sql.Date sqlDate2 = null;
//          java.sql.Date sqlDate1 = new java.sql.Date(0);
//         java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
//         if(!name.equals("") || !family.equals("") || !date1.equals("")){
         List<EmployeeDTO> list1 = employeeService.getlistEmployee(name,family,date1,date2,levelid1);
         request.setAttribute("list_employee",list1);
//         }

         List<LevelDTO>list2=levelService.getlistLevel();
         request.setAttribute("list_level",list2);


         request.getRequestDispatcher("employee.jsp").forward(request,response);
    }
}
