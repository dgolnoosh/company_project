package edu.karamoozi.model.dao;

import edu.karamoozi.model.database.PoolConnection;
import edu.karamoozi.model.dto.EmployeeDTO;
import edu.karamoozi.model.dto.LevelDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDAO {
    public List<EmployeeDTO> getAllEmployee(String name, String family, String date1, String date2, int levelId) {
        try(Connection connection = PoolConnection.getConnection()) {
            List<EmployeeDTO> list = new ArrayList<>();
            List<String> sqllist = new ArrayList<>();
            String sql = "SELECT e.*, l.LevelTitle FROM EMPLOYEE e join LEVELS l ON e.LEVELID=l.LEVELID Where 1=1";
            if (!name.equals("")) {
                sql += " and FNAME like ?";
                sqllist.add(String.format("%%%s%%", name));
            }
            if (!family.equals("")) {
                sql += " and LNAME like ?";
                // sqllist.add("%"+family+"%");
                sqllist.add(String.format("%%%s%%", family));
            }
            if (!date1.equals("") && !date2.equals("")) {
                sql += " and HIRE_DATE between to_date(?,'yyyy-MM-dd') and to_date(?,'yyyy-MM-dd')";
                sqllist.add(date1);
                sqllist.add(date2);
            }
            if (levelId != 0) {
                sql += " and e.LEVELID = ?";
                sqllist.add(levelId + "");
            }
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0; i < sqllist.size(); i++) {
                ps.setString(i + 1, sqllist.get(i));
            }
            ResultSet resultSet = ps.executeQuery();
//            String sql ="SELECT e.*, l.LevelTitle FROM EMPLOYEE e join LEVELS l ON e.LEVELID=l.LEVELID Where 1=1";
//            sql+=" and FNAME like ?";
//            sql+=" and LNAME like ?";
//            sql+=" and HIRE_DATE between to_date(?,'yyyy-MM-dd') and to_date(?,'yyyy-MM-dd')";
//            if (levelId != 0){
//                sql+=" and e.LEVELID = ?";
//            }
//            PreparedStatement ps=connection.prepareStatement(sql);
//            ps.setString(1,"%"+name+"%");
//            ps.setString(2,"%"+family+"%");
//            ps.setString(3,  date1);
//            ps.setString(4,  date2);
//            if (levelId != 0)
//                ps.setInt(5, levelId);
//            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()) {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setEmployeeId(resultSet.getInt("EMPLOYEEID"));
                employeeDTO.setfName(resultSet.getString("FNAME"));
                employeeDTO.setHireDate(resultSet.getString("HIRE_DATE"));
                LevelDTO temp = new LevelDTO();
                temp.setLevelId(resultSet.getInt("LEVELID"));
                temp.setLevelTitle(resultSet.getString("LEVELTITLE"));
                employeeDTO.setLevelDTO(temp);
                employeeDTO.setlName(resultSet.getString("LNAME"));
                list.add(employeeDTO);
            }
            return list;
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addEmployeeDTO(EmployeeDTO employeeDTO) {
        try( Connection connection = PoolConnection.getConnection()) {
//            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(employeeDTO.getHireDate());
            LocalDate localDate = LocalDate.parse(employeeDTO.getHireDate(), DateTimeFormatter.ISO_LOCAL_DATE);
            String sql = "INSERT INTO EMPLOYEE VALUES (SEQ_EMPLOYEE.nextval,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, employeeDTO.getEmployeeId());
            ps.setString(1, employeeDTO.getfName());
            ps.setDate(2, java.sql.Date.valueOf(localDate));
            ps.setInt(3, employeeDTO.getLevelDTO().getLevelId());
            ps.setString(4, employeeDTO.getlName());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkEmployeeIdDao(String id) {
        try ( Connection connection = PoolConnection.getConnection();){
            String sql = "select EMPLOYEEID from EMPLOYEE where EMPLOYEEID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEmployeeIdDao(String id ) {
        try(Connection connection = PoolConnection.getConnection();){
        String sql = "delete from EMPLOYEE where EMPLOYEEID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
        }
    }

    public boolean editEmployeeIdDao(EmployeeDTO employeeDTO) {
        try( Connection connection = PoolConnection.getConnection()) {
            LocalDate localDate = LocalDate.parse(employeeDTO.getHireDate(), DateTimeFormatter.ISO_LOCAL_DATE);
            String sql = "update EMPLOYEE set FNAME=?, HIRE_DATE=?, LEVELID=? , LNAME=?  where EMPLOYEEID = ?";
//            String sql = "update EMPLOYEE set FNAME=?, HIRE_DATE=to_data(?,'yyyy-MM-dd'), LEVELID=? , LNAME=?  where EMPLOYEEID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, employeeDTO.getfName());
            ps.setDate(2, java.sql.Date.valueOf(localDate));
            ps.setInt(3, employeeDTO.getLevelDTO().getLevelId());
            ps.setString(4, employeeDTO.getlName());
            ps.setString(5, String.valueOf(employeeDTO.getEmployeeId()));
//            ps.setString(5, id);
          int rsl= ps.executeUpdate();
          if (rsl ==1){
              return true;
             }
          else {
              return false;
                  }
             }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
            }
        }


    public EmployeeDTO findEmployee(String employeeid) {
        try (Connection connection = PoolConnection.getConnection()) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            String sql = "select * from EMPLOYEE WHERE EMPLOYEEID=? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, employeeid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employeeDTO.setfName(rs.getString("FNAME"));
                employeeDTO.setlName(rs.getString("LNAME"));
                employeeDTO.setHireDate(rs.getString("HIRE_DATE"));
                employeeDTO.setLevelDTO(new LevelDTO());
                employeeDTO.getLevelDTO().setLevelId(rs.getInt("LEVELID"));
            }
            return employeeDTO;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}