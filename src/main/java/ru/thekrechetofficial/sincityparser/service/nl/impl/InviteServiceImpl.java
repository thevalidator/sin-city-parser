/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.service.nl.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.thekrechetofficial.sincityparser.entity.Invintation;
import ru.thekrechetofficial.sincityparser.repository.InviteRepository;
import ru.thekrechetofficial.sincityparser.service.nl.InviteService;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
@Service
@Transactional
public class InviteServiceImpl implements InviteService {

    private final InviteRepository repository;

    @Autowired
    public InviteServiceImpl(InviteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<String> getDistinctEmails() {

        return repository.findDistinctEmails();

    }

    @Override
    public void saveAll(List<String> emails) {
        if (!emails.isEmpty()) {
            List<Invintation> invintations = new ArrayList<>();
            LocalDateTime now = LocalDateTime.now();
            for (String e : emails) {
                System.out.println(e);
                invintations.add(new Invintation(e, now));
            }
            repository.saveAllAndFlush(invintations);
        }
    }

}
