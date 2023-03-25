package com.example.microfinancepi.services;

import com.example.microfinancepi.entities.Event;

import java.util.List;

public interface EventService {
    List<Event> retrieveAllEvents();

    Event AddEvent(Event event);

    void removeEvent(Integer numEvent);

    Event retrieveEvent(Integer numEvent);

    Event updateEvent(Event event);

    List<Event> findByShareHolders_LastNameShareholderAndShareHolders_FirstNameShareholder(String lastNameShareholder, String FirstNameShareholder);

    Double getTotalInvestmentInEvent(int eventId);


    List<Event> getEventsWithin24Hours();
}