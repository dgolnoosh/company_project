package edu.karamoozi.controller;

import edu.karamoozi.model.dto.AssetDTO;
import edu.karamoozi.model.dto.EmployeeDTO;
import edu.karamoozi.model.dto.LevelDTO;
import edu.karamoozi.model.service.AssetService;
import edu.karamoozi.model.service.EmployeeService;
import edu.karamoozi.model.service.LevelService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletEditAsset", value = "/ServletEditAsset")
public class ServletEditAsset extends HttpServlet {
    EmployeeService employeeService=new EmployeeService();
    AssetService assetService=new AssetService();
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String assetid = request.getParameter("assetid");
        if (request.getParameter("Submit") != null) {
            String title = request.getParameter("Title");
            String color = request.getParameter("Color");
            //String employeeid = request.getParameter("employeeid");
            String errorMessage = "";
            boolean hasError = false;
            if(title == null || title.isEmpty()) {
                errorMessage = "Please enter Title";
                hasError = true;
            } else if(color == null || color.isEmpty()) {
                errorMessage = "Please enter color";
                hasError = true;
            }

            if(hasError) {
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("addAsset.jsp").forward(request, response);
                return;
            }
            AssetDTO assetDTO=assetService.getAsset(assetid);
            assetDTO.setColor(color);
            assetDTO.setTitle(title);
            boolean r = assetService.editAssetService(assetDTO);
            if (r) {
                response.sendRedirect("ServletAssetList?done=1&employeeid="+assetDTO.getEmployeeDTO().getEmployeeId());
                return;
            }
            else {
                response.sendRedirect("ServletAssetList?done=0&employeeid="+assetDTO.getEmployeeDTO().getEmployeeId());
                return;
            }
        }else {
            AssetDTO asset = assetService.getAsset(assetid);
            request.setAttribute("asset", asset);
            request.getRequestDispatcher("editAsset.jsp").forward(request, response);
        }

    }


}
