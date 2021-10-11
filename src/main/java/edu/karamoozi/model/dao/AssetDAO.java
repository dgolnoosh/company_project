package edu.karamoozi.model.dao;

import edu.karamoozi.model.database.PoolConnection;
import edu.karamoozi.model.dto.AssetDTO;
import edu.karamoozi.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssetDAO {

    //++++++++++++++++++++++++++++++++++++getAllAssets+++++++++++++++++++++++++
    public List<AssetDTO> getAllAssetsDAO(String employeeId){
        try ( Connection connection = PoolConnection.getConnection()){
            List<AssetDTO>list=new ArrayList<>();
            String sql="SELECT e.*, l.LNAME , L.FNAME , L.LEVELID FROM ASSET e join EMPLOYEE l ON e.EMPLOYEEID=l.EMPLOYEEID where 1=1";
            sql+="and e.EMPLOYEEID = ?";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1,employeeId);
            ResultSet rs= ps.executeQuery();
            while (rs.next()) {
                AssetDTO assetDTO1 = new AssetDTO();
                assetDTO1.setAssetId(rs.getInt("ASSETID"));
                assetDTO1.setTitle(rs.getString("TITLE"));
                assetDTO1.setColor(rs.getString("COLOR"));
                EmployeeDTO temp = new EmployeeDTO();
                temp.setEmployeeId(rs.getInt("LEVELID"));
                temp.setlName(rs.getString("LNAME"));
                temp.setfName(rs.getString("FNAME"));
                temp.setEmployeeId(rs.getInt("EMPLOYEEID"));
                assetDTO1.setEmployeeDTO(temp);
              list.add(assetDTO1);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //++++++++++++++++++++++++++++++++++++addAsset+++++++++++++++++++++++++
    public AssetDTO getAsset(String assetId){
        try ( Connection connection = PoolConnection.getConnection()){
            String sql="SELECT e.*, l.LNAME , L.FNAME , L.LEVELID FROM ASSET e join EMPLOYEE l ON e.EMPLOYEEID=l.EMPLOYEEID where e.\"assetID\"=?";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1,assetId);
            ResultSet rs= ps.executeQuery();
            if (rs.next()) {
                AssetDTO assetDTO1 = new AssetDTO();
                assetDTO1.setAssetId(rs.getInt("ASSETID"));
                assetDTO1.setTitle(rs.getString("TITLE"));
                assetDTO1.setColor(rs.getString("COLOR"));
                EmployeeDTO temp = new EmployeeDTO();
                temp.setEmployeeId(rs.getInt("LEVELID"));
                temp.setlName(rs.getString("LNAME"));
                temp.setfName(rs.getString("FNAME"));
                temp.setEmployeeId(rs.getInt("EMPLOYEEID"));
                assetDTO1.setEmployeeDTO(temp);
                return assetDTO1;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //++++++++++++++++++++++++++++++++++++addAsset+++++++++++++++++++++++++
    public boolean addAsset(AssetDTO assetDTO ){
        try( Connection connection = PoolConnection.getConnection()) {
            String sql= "INSERT INTO ASSET VALUES (SEQ_ASSET.nextval,?,?,?)";
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setString(1,assetDTO.getTitle());
            ps.setString(2,assetDTO.getColor());
            ps.setInt(3,assetDTO.getEmployeeDTO().getEmployeeId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //++++++++++++++++++++++++++++++++++++removeAsset+++++++++++++++++++++++++
    public boolean removeAsset(String id)
    {
        try(Connection connection = PoolConnection.getConnection()) {
        String sql= "delete from ASSET where \"assetID\"= ?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
         }
    }


    public boolean editAssetDAO(AssetDTO assetDTO) {
        try (Connection connection = PoolConnection.getConnection()){
            String sql = "update ASSET set TITLE=?, COLOR=? where \"assetID\" = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, assetDTO.getTitle());
            ps.setString(2, assetDTO.getColor());
            ps.setString(3, String.valueOf(assetDTO.getAssetId()));
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
}
