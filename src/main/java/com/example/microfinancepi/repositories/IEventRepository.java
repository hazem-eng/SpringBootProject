package com.example.microfinancepi.repositories;

import com.example.microfinancepi.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IEventRepository extends JpaRepository<Event,Integer> {
    @Query("select e from Event e inner join e.shareHolders shareHolders where shareHolders.lastNameShareholder = ?1 and shareHolders.FirstNameShareholder = ?2")
    List<Event> findByShareHolders_LastNameShareholderAndShareHolders_FirstNameShareholder(String lastNameShareholder, String FirstNameShareholder);
    @Query("SELECT SUM(s.investment) FROM ShareHolder s WHERE s.event.idEvent = :eventId")
    Double findByToltalInvestmentEvent(@Param("eventId") int eventId);
    @Query("select i from Event i where i.dateEvent between ?1 and ?2")
    List<Event> findByEventDateBetween(LocalDate date1, LocalDate date2);
}

