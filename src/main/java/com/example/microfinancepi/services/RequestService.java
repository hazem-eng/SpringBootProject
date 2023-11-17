package com.example.microfinancepi.services;


import com.example.microfinancepi.entities.AccOrRef;
import com.example.microfinancepi.entities.AmortizationScheduleEntry;
import com.example.microfinancepi.entities.Request;


import java.util.List;

public interface RequestService {
    List<AccOrRef> retrieveAllMatchings();
    List<Request> retrieveAllRequests();
    Request AddRequest(Request request);
    void removeRequest(Integer numRequest);
    Request retrieveRequest(Integer numRequest);
    Request updateRequest(Request request);
     Request assignRequestToOffers_Credit(Integer id_request, Integer id_offer);

    AccOrRef matching (Integer id_request);
    List<String> check_loan(Integer id_request);
    List<AmortizationScheduleEntry> calculateAmortizationTable(Integer i_request);

















}
