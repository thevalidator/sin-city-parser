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

    @Query(value = "SELECT DISTINCT v.email \n"
            + "FROM (\n"
            + "		SELECT LOWER(SUBSTRING(contact, '[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*')) AS email   \n"
            + "		FROM nlads \n"
            + "		ORDER BY created_on DESC \n"
            + "		LIMIT 300\n"
            + "	) AS v \n"
            + "	\n"
            + "WHERE v.email is NOT NULL AND NOT EXISTS (SELECT i.email FROM invintation i) LIMIT 35",
            nativeQuery = true)
    List<String> findDistinctEmails();

}
