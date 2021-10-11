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

@WebServlet(name = "ServletAddAsset", value = "/ServletAddAsset")
public class ServletAddAsset extends HttpServlet {
    AssetService assetService=new AssetService();
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                 if (request.getParameter("Submit") != null) {
                String title = request.getParameter("Title");
                String color = request.getParameter("Color");
                String employeeid = request.getParameter("employeeid");


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
                     AssetDTO assetDTO=new AssetDTO();
                     EmployeeDTO employeeDTO=new EmployeeDTO();
                     employeeDTO.setEmployeeId(Integer.parseInt(employeeid));
//                    assetDTO.setAssetId(employeeDTO.getEmployeeId());
                     assetDTO.setColor(color);
                     assetDTO.setTitle(title);
                     assetDTO.setEmployeeDTO(employeeDTO);
                     boolean r =  assetService.addAssetService(assetDTO);
                if(r) {
                    response.sendRedirect("ServletAssetList?done=1&employeeid="+employeeid);
//                    request.getRequestDispatcher("ServletAssetList?done=1").forward(request, response);
                    return;
                }
                else{
                    response.sendRedirect("employee?done=0&employeeid="+employeeid);
//                    request.getRequestDispatcher("ServletAssetList?done=0").forward(request, response);

                    return;
                }
          }
            else {
                request.getRequestDispatcher("addAsset.jsp").forward(request, response);
            }
        }
    }


