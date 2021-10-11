package edu.karamoozi.controller;

import edu.karamoozi.model.dto.AssetDTO;
import edu.karamoozi.model.service.AssetService;
import edu.karamoozi.model.service.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletDeleteEmployee", urlPatterns = "/ServletDeleteEmployee")
public class ServletDeleteEmployee extends HttpServlet {
    EmployeeService employeeService=new EmployeeService();
    AssetService assetService=new AssetService();
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeid=request.getParameter("employeeid");
        List<AssetDTO> assetDTOList = assetService.getListAssetService(employeeid);
        if(assetDTOList != null && !assetDTOList.isEmpty()){
            response.getWriter().print("2");
//            response.sendRedirect("employee?resultdelete=2&employeeid="+employeeid);
            return;
        }
        boolean result1=employeeService.deleteEmployeeService(employeeid);
        if(result1) {
            response.getWriter().print("1");
//            response.sendRedirect("employee?resultdelete=1&employeeid="+employeeid);
            return;
        }
        else{
            response.getWriter().print("0");
//            response.sendRedirect("employee?resultdelete=0&employeeid="+employeeid);
            return;
        }
    }
}
