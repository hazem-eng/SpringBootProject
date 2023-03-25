package com.example.microfinancepi.repositories;

import com.example.microfinancepi.entities.ShareHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IShareholderRepository extends JpaRepository<ShareHolder,Integer> {

    @Query("SELECT s FROM ShareHolder s WHERE s.event.idEvent = :eventId")
    List<ShareHolder> findByEventId(@Param("eventId") int eventId);

    @Query("SELECT sh, COUNT(DISTINCT e) AS eventsCount " +
            "FROM ShareHolder sh " +
            "JOIN sh.event e " +
            "GROUP BY sh.idShareholder " +
            "HAVING COUNT(DISTINCT e) > 1 " +
            "ORDER BY eventsCount DESC")
    List<Object[]> findShareHoldersWithMostEvents();

}
