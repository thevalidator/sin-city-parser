/*
 * The Krechet Software
 */

package ru.thekrechetofficial.sincityparser.service.nl.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.thekrechetofficial.sincityparser.entity.NLAd;
import ru.thekrechetofficial.sincityparser.repository.NLAdRepository;
import ru.thekrechetofficial.sincityparser.service.nl.NLAdService;


/**
 * @author theValidator <the.validator@yandex.ru>
 */
@Service
@Transactional
public class NLAdServiceImpl implements NLAdService {
    
    private final NLAdRepository repository;
    
    @Autowired
    public NLAdServiceImpl(NLAdRepository nlRepository) {
        this.repository = nlRepository;
    }

    @Override
    public void save(NLAd ad) {
        repository.saveAndFlush(ad);
    }

    @Override
    public void saveAll(List<NLAd> ads) {
        repository.saveAllAndFlush(ads);
    }

    @Override
    public List<String> getExist(List<String> parsedOffers) {
        return repository.getAllExist(parsedOffers);
        
    }
    

}
