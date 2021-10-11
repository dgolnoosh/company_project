package edu.karamoozi.model.service;

import edu.karamoozi.model.dao.AssetDAO;
import edu.karamoozi.model.dto.AssetDTO;
import edu.karamoozi.model.dto.EmployeeDTO;

import java.util.List;

public class AssetService {
    private AssetDAO assetDAO =new AssetDAO();

    public List<AssetDTO> getListAssetService(String employeid ){
        List<AssetDTO>list=assetDAO.getAllAssetsDAO(employeid);
        return list;
    }

    public AssetDTO getAsset(String assetId ){
        AssetDTO assetDTO=assetDAO.getAsset(assetId);
        return assetDTO;
    }

    public boolean addAssetService(AssetDTO assetDTO){
        return assetDAO.addAsset(assetDTO);
    }

    public boolean deleteAssetService(String assetId){
        return assetDAO.removeAsset(assetId);
    }

    public boolean editAssetService(AssetDTO assetDTO) {
        return assetDAO.editAssetDAO(assetDTO);
    }
}
