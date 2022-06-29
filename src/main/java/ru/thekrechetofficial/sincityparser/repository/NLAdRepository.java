/*
 * The Krechet Software
 */
package ru.thekrechetofficial.sincityparser.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.thekrechetofficial.sincityparser.entity.NLAd;

/**
 *
 * @author theValidator <the.validator@yandex.ru>
 */
@Repository
public interface NLAdRepository extends JpaRepository<NLAd, Long> {
    
    Optional<NLAd> findById(long id);
    
    Optional<NLAd> findByOfferId(long offerId);
    
    @Query(value = "SELECT offerid "
                    + "FROM public.nlads "
                    + "WHERE offerid "
                    + "IN (:offers)", nativeQuery = true)
    List<String> getAllExist(@Param("offers") List<String> parsedOffers);
    
}
