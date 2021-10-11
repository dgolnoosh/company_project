package edu.karamoozi.controller;

import edu.karamoozi.model.service.AssetService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletDeleteAsset", value = "/ServletDeleteAsset")
public class ServletDeleteAsset extends HttpServlet {
AssetService assetService=new AssetService();
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("assetid");
        boolean result1=assetService.deleteAssetService(id);
        if(result1) {
            response.sendRedirect("ServletAssetList?resultdelete=1");
            return;
        }
        else{
            response.sendRedirect("ServletAssetList?resultdelete=0");
            return;
        }
    }

}
