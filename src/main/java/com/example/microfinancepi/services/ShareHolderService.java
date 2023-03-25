package com.example.microfinancepi.services;

import com.example.microfinancepi.entities.ShareHolder;

import java.util.List;

public interface ShareHolderService {
    List<ShareHolder> retrieveAllShareHolder();
    ShareHolder AddShareHolder(ShareHolder shareholder);
    void removeShareHolder(Integer numShareholder);
    ShareHolder retrieveShareHolder(Integer numShareholder);
    ShareHolder updateShareHolder(ShareHolder shareholder);
    ShareHolder assignShareHolderToEvent(Integer idShareHolder, Integer idEvent);

    List<ShareHolder> findShareholdersWithMoreThanOneEvent();


}
