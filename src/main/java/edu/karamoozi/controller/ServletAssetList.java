package edu.karamoozi.controller;

import edu.karamoozi.model.dto.AssetDTO;
import edu.karamoozi.model.dto.EmployeeDTO;
import edu.karamoozi.model.service.AssetService;
import edu.karamoozi.model.service.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletAssetList", value = "/ServletAssetList")
public class ServletAssetList extends HttpServlet {
    AssetService assetService=new AssetService();
    EmployeeService employeeService=new EmployeeService();
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("employeeid");
//        String eid=request.getParameter("eid");
       EmployeeDTO employeeDTO= employeeService.findEmployeeServlet(id);
       String employeename=employeeDTO.getfName();
        String employeefamily=employeeDTO.getlName();
        List<AssetDTO> list1 =assetService.getListAssetService(id);
        request.setAttribute("list_asset",list1);
        request.setAttribute("employeename",employeename);
        request.setAttribute("employeefamily",employeefamily);
        request.getRequestDispatcher("AssetList.jsp").forward(request,response);
    }


}
