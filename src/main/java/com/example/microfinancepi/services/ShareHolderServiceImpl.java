package com.example.microfinancepi.services;

import com.example.microfinancepi.entities.Event;
import com.example.microfinancepi.entities.ShareHolder;
import com.example.microfinancepi.repositories.IEventRepository;
import com.example.microfinancepi.repositories.IShareholderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ShareHolderServiceImpl implements ShareHolderService {
    private IShareholderRepository Ishareholderrepository;
    private IEventRepository iEventRepository;
    @Override
    public List<ShareHolder> retrieveAllShareHolder()  {
        return Ishareholderrepository.findAll();
    }

    @Override
    public ShareHolder AddShareHolder(ShareHolder shareholder) {
        return Ishareholderrepository.save(shareholder);
    }

    @Override
    public void removeShareHolder(Integer numShareholder) {
        Ishareholderrepository.deleteById(numShareholder);
    }

    @Override
    public ShareHolder retrieveShareHolder(Integer numShareholder) {
        return Ishareholderrepository.findById(numShareholder).orElse(null);
    }

    @Override
    public ShareHolder updateShareHolder(ShareHolder shareholder) {
        return Ishareholderrepository.save(shareholder);
    }
    @Override
    public ShareHolder assignShareHolderToEvent(Integer idShareHolder, Integer idEvent){
        ShareHolder shareholder=Ishareholderrepository.findById(idShareHolder).orElse(null);
        Event event=iEventRepository.findById(idEvent).orElse(null);
        shareholder.setEvent(event);
        return Ishareholderrepository.save(shareholder);
    }
    @Override
    public List<ShareHolder> findShareholdersWithMoreThanOneEvent() {
        List<ShareHolder> shareholders = Ishareholderrepository.findAll();
        Map<Integer, Integer> countMap = new HashMap<>();
        for (ShareHolder shareholder : shareholders) {
            int count = shareholder.getEvent().getShareHolders().size();
            countMap.put(shareholder.getIdShareholder(), count);
        }
        List<ShareHolder> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > 1) {
                Optional<ShareHolder> optionalShareholder = Ishareholderrepository.findById(entry.getKey());
                if (optionalShareholder.isPresent()) {
                    result.add(optionalShareholder.get());
                }
            }
        }
        return result;
    }






}
