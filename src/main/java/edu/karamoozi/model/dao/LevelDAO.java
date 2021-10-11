package edu.karamoozi.model.dao;

import edu.karamoozi.model.database.PoolConnection;
import edu.karamoozi.model.dto.LevelDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LevelDAO {
public List<LevelDTO> getAllLevels(){
    List<LevelDTO>list=new ArrayList<>();
    try {
    Connection connection= PoolConnection.getConnection();
    String sql="select * from LEVELS";
    PreparedStatement ps=connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            LevelDTO levelDTO=new LevelDTO();
            levelDTO.setLevelId(rs.getInt("LEVELID"));
            levelDTO.setLevelTitle(rs.getString("LEVELTITLE"));
            list.add(levelDTO);
        }
        return list;
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }

}
}
