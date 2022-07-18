/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.thekrechetofficial.sincityparser.entity.Invintation;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
@Repository
public interface InviteRepository extends JpaRepository<Invintation, Long> {

    @Query(value = "SELECT DISTINCT lower(v.email) \n"
            + "FROM (\n"
                + "	SELECT SUBSTRING(c.contact, '[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*') AS email \n"
                + "	FROM (\n"
                + "	SELECT contact FROM nlads WHERE contact ILIKE '%@%.%' ORDER BY created_on DESC LIMIT 35\n"
                + "	) AS c \n"
            + "	) AS v\n"
            + "WHERE v.email IS NOT NULL AND NOT EXISTS (SELECT i.email FROM invintation i)",
            nativeQuery = true)
    List<String> findDistinctEmails();
    
    

}
