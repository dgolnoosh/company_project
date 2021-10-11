package edu.karamoozi.controller;

import edu.karamoozi.model.dto.EmployeeDTO;
import edu.karamoozi.model.dto.LevelDTO;
import edu.karamoozi.model.service.EmployeeService;
import edu.karamoozi.model.service.LevelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "dsf", urlPatterns = "/ServletAddEmployee")
public class ServletAddEmployee extends HttpServlet {
    EmployeeService employeeService=new EmployeeService();
    LevelService levelService=new LevelService();
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("Submit") != null) {
//            response.getWriter().println("This is after submit");
//            String id = request.getParameter("EmployeeID");
            String fname = request.getParameter("Name");
            String lname = request.getParameter("Family");
            String hireDate = request.getParameter("Hire_date");
            String levelId = request.getParameter("Level");

            String errorMessage = "";
            boolean hasError = false;
//            if(id == null || id.isEmpty()) {
//                errorMessage = "Please enter Employee ID";
//                hasError = true;
//            }
            if(fname == null || fname.isEmpty()) {
                errorMessage = "Please enter First Name";
                hasError = true;
            } else if(lname == null || lname.isEmpty()) {
                errorMessage = "Please enter last Name";
                hasError = true;
            }
            else if(hireDate == null || hireDate.isEmpty()) {
                errorMessage = "Please enter hiredate";
                hasError = true;
            }
            else if(levelId == null || levelId.isEmpty()) {
                errorMessage = "Please enter level";
                hasError = true;
            }
             if(hasError) {
                request.setAttribute("errorMessage", errorMessage);
                 List<LevelDTO> levelDTOS = levelService.getlistLevel();
                 request.setAttribute("list_level", levelDTOS);
                request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
                //response.sendRedirect("addEmployee.jsp");
                return;
            }

//**************            duplicate key
//            boolean checked=employeeService.checkEmployeeIdService(id1);
//            String error="";
//            if (checked){
//                error="enter another id .this already registtered id";
//           }else {
//                int id = Integer.parseInt(id1);

            EmployeeDTO employeeDTO=new EmployeeDTO();
            employeeDTO.setfName(fname);
            employeeDTO.setlName(lname);
            employeeDTO.setHireDate(hireDate);
            LevelDTO levelDTO=new LevelDTO();
            int levelid = Integer.parseInt(levelId);
            levelDTO.setLevelId(levelid);
            employeeDTO.setLevelDTO(levelDTO);
            boolean r = employeeService.addEmployeeService(employeeDTO);
            if(r) {
                response.sendRedirect("employee?done=1");
                return;
            }
            else{
                response.sendRedirect("employee?done=0");
                return;
            }



//****************   register
//            boolean r = employeeService.addEmployeeService(employeeDTO);
//            if(r)
//                response.sendRedirect("employee?done=1");
//            else
//                response.sendRedirect("employee?done=0");


/*
            String error="";
            String id1 = request.getParameter("EmployeeID");
            if (id1==null || id1.isEmpty()){
                error="id is empity";
            }else {
                boolean checked=employeeService.checkEmployeeIdService(id1);

                if (checked){
                    error="enter another id .this already registtered id";
                }else {
                    int id = Integer.parseInt(id1);
                    String name = request.getParameter("Name");
                    if (name==null){
                        error="name is empity";
                    }else {
                        String family = request.getParameter("Family");
                        if (family==null){
                            error="family is empity";
                        }else {
                            String levelid1 = request.getParameter("Level");
                            if (levelid1==null){
                                error="levelid1 is empity";
                            }else {
                                String hire_date = request.getParameter("Hire_date");
                                if (hire_date==null){
                                    error="hire_date is empity";
                                }else {
                                    EmployeeDTO employeeDTO=new EmployeeDTO();
                                    employeeDTO.setEmployeeId(id);
                                    employeeDTO.setfName(name);
                                    employeeDTO.setlName(family);
//                                    Date date1 = null;
//                                    try {
//                                        date1 = new SimpleDateFormat("yyyy-MM-dd").parse(hire_date);

                                    employeeDTO.setHireDate(hire_date);

                                    LevelDTO levelDTO=new LevelDTO();
                                    int levelid = Integer.parseInt(levelid1);
                                    levelDTO.setLevelId(levelid);
                                    employeeDTO.setLevelDTO(levelDTO);
                                    boolean r = employeeService.addEmployeeService(employeeDTO);
//                                    } catch (ParseException e) {
//                                        e.printStackTrace();
//                                    }

                                }
                            }
                        }
                    }
                }
            }
            request.setAttribute("errorlog", error);
*/
        }
//        else {
//            //first time
////            List<LevelDTO> levelDTOS = levelService.getlistLevel();
////            request.setAttribute("list_level", levelDTOS);
//        }
        else {
            List<LevelDTO> levelDTOS = levelService.getlistLevel();
            request.setAttribute("list_level", levelDTOS);
            request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
        }
   }
}
