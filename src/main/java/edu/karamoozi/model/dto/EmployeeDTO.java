package edu.karamoozi.model.dto;

import java.util.Date;

public class EmployeeDTO {
    private int employeeId;
    private String fName;
    private String lName;
    private String hireDate;
    private LevelDTO levelDTO;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public LevelDTO getLevelDTO() {
        return levelDTO;
    }

    public void setLevelDTO(LevelDTO levelDTO) {
        this.levelDTO = levelDTO;
    }
}
