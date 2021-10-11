package edu.karamoozi.model.service;

import edu.karamoozi.model.dao.EmployeeDAO;
import edu.karamoozi.model.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
   private EmployeeDAO employeeDAO=new EmployeeDAO();
    public List<EmployeeDTO> getlistEmployee(String name, String family, String date1, String date2, int levelId){
        List<EmployeeDTO> list1 = employeeDAO.getAllEmployee(name, family, date1, date2, levelId);
        return list1;
    }
    public boolean addEmployeeService(EmployeeDTO employeeDTO){
        return employeeDAO.addEmployeeDTO(employeeDTO);
    }
    public boolean checkEmployeeIdService(String id) {
        return employeeDAO.checkEmployeeIdDao(id);
    }

    public boolean deleteEmployeeService(String id) {
        return employeeDAO.deleteEmployeeIdDao(id);
    }
    public boolean editEmployeeService(EmployeeDTO employeeDTO) {
        return employeeDAO.editEmployeeIdDao(employeeDTO);
    }
public EmployeeDTO findEmployeeServlet(String employeeid){
        return employeeDAO.findEmployee(employeeid);
}

}
