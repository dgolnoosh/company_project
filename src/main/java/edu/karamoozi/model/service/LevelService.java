package edu.karamoozi.model.service;

import edu.karamoozi.model.dao.LevelDAO;
import edu.karamoozi.model.dto.LevelDTO;

import java.util.List;

public class LevelService {
    private LevelDAO levelDAO=new LevelDAO();
    public  List<LevelDTO> getlistLevel(){
        List<LevelDTO>list=levelDAO.getAllLevels();
        return list;
    }
}
