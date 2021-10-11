package edu.karamoozi.controller;

import edu.karamoozi.model.dto.EmployeeDTO;
import edu.karamoozi.model.dto.LevelDTO;
import edu.karamoozi.model.service.EmployeeService;
import edu.karamoozi.model.service.LevelService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/ServletEditEmployee")
public class ServletEditEmployee extends HttpServlet {
    EmployeeService employeeService=new EmployeeService();
    LevelService levelService=new LevelService();
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("employeeid");
        if (request.getParameter("Submit") != null) {
            String fname = request.getParameter("Name");
            String lname = request.getParameter("Family");
            String hireDate = request.getParameter("Hire_date");
            String levelId = request.getParameter("Level");
            String errorMessage = "";
            boolean hasError = false;
            if (fname == null || fname.isEmpty()) {
                errorMessage = "Please enter First Name";
                hasError = true;
            } else if (lname == null || lname.isEmpty()) {
                errorMessage = "Please enter last Name";
                hasError = true;
            } else if (hireDate == null || hireDate.isEmpty()) {
                errorMessage = "Please enter hiredate";
                hasError = true;
            } else if (levelId == null || levelId.isEmpty()) {
                errorMessage = "Please enter level";
                hasError = true;
            }
            if (hasError) {
                request.setAttribute("errorMessage", errorMessage);
                List<LevelDTO> levelDTOS = levelService.getlistLevel();
                request.setAttribute("list_level", levelDTOS);
                request.getRequestDispatcher("editEmployee.jsp").forward(request, response);
                //response.sendRedirect("addEmployee.jsp");
                return;
            }
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmployeeId(Integer.parseInt(id));
            employeeDTO.setfName(fname);
            employeeDTO.setlName(lname);
            employeeDTO.setHireDate(hireDate);
            LevelDTO levelDTO = new LevelDTO();
            int levelid = Integer.parseInt(levelId);
            levelDTO.setLevelId(levelid);
            employeeDTO.setLevelDTO(levelDTO);
            boolean r = employeeService.editEmployeeService(employeeDTO);
            if (r) {
                response.sendRedirect("employee?done=1");
                return;
            }
            else {
                response.sendRedirect("employee?done=0");
                return;
            }
        }else {
            List<LevelDTO> levelDTOS = levelService.getlistLevel();
            request.setAttribute("list_level", levelDTOS);
            EmployeeDTO employeeDTO = employeeService.findEmployeeServlet(id);
            request.setAttribute("employee", employeeDTO);
            request.getRequestDispatcher("editEmployee.jsp").forward(request, response);
        }

    }
}
