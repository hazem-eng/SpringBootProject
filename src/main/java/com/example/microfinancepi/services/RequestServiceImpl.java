package com.example.microfinancepi.services;


import com.example.microfinancepi.entities.AccOrRef;
import com.example.microfinancepi.entities.AmortizationScheduleEntry;
import com.example.microfinancepi.entities.Offers_Credit;
import com.example.microfinancepi.entities.Request;
import com.example.microfinancepi.repositories.IAccOrRefRepository;
import com.example.microfinancepi.repositories.IOffers_CreditRepository;
import com.example.microfinancepi.repositories.IRequestRepository;
import com.example.microfinancepi.repositories.UserRepository;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {
    private IRequestRepository IRequestrepository;
    private IOffers_CreditRepository iOffersCreditRepository;
    private UserRepository userRepository;

    private IAccOrRefRepository acc;


    @Override
    public List<AccOrRef> retrieveAllMatchings() {
        return acc.findAll();
    }

    @Override
    public List<Request>retrieveAllRequests() {
        return IRequestrepository.findAll();
    }

    @Override
    public Request AddRequest(Request request) {
        return IRequestrepository.save(request);
    }

    @Override
    public void removeRequest(Integer numRequest) {
        IRequestrepository.deleteById(numRequest);
    }

    @Override
    public Request retrieveRequest(Integer numRequest) {
        return IRequestrepository.findById(numRequest).orElse(null);
    }

    @Override
    public Request updateRequest(Request request) {
        return IRequestrepository.save(request);
    }

    @Override
    public Request assignRequestToOffers_Credit(Integer id_request, Integer id_offer) {
        Request request=IRequestrepository.findById(id_request).orElse(null);
        Offers_Credit offers_credit=iOffersCreditRepository.findById(id_offer).orElse(null);
        request.setOffer(offers_credit);
        return IRequestrepository.save(request);
    }



    @Override
    public AccOrRef matching(Integer id_request ) {
        List<AccOrRef> all =acc.findAll();

        Request req = IRequestrepository.findById(id_request).orElse(null);
        AccOrRef azz = new AccOrRef();


       /*   if (req.getOffer()!=null&& req.getUser().getId_user() != null) {
              for (AccOrRef verif : all) {
                  if (verif.getRequest().getOffer().getId_offer() == req.getOffer().getId_offer() && verif.getRequest().getUser().getId_user() == req.getUser().getId_user()) {
                      return null;

                  }
              }*/



              if (req.getOffer().getMax_amount() >= req.getAmount_req() && req.getOffer().getMin_amount() <= req.getAmount_req()) {
                  azz.setRequest(req);
                  azz.setCheck_loan("Request accepted");
                  req.setAccOrRef(azz);


              }


               else {
                  azz.setRequest(req);
                  azz.setCheck_loan("Request refused");
                  req.setAccOrRef(azz);


              }


        return acc.save(azz);
    }



    @Override
    public List<String> check_loan(Integer id_request) {
        List<String> All= new ArrayList<>();
        Request req = IRequestrepository.findById(id_request).orElse(null);
        if (req.getAccOrRef().getId_AccOrRef()==null) {
            return null;
        }
                AccOrRef accOrRef = req.getAccOrRef();
                if (accOrRef.getCheck_loan() != null) {
                    All.add(" Request Number: " + accOrRef.getRequest().getId_request() + " : " + accOrRef.getCheck_loan()  /*+   " :Offer Credit Numéro: "+ req.getOffer().getId_offer()*/);
                }


        return All;
    }

    public List<AmortizationScheduleEntry> calculateAmortizationTable(Integer i_request) {
        // Récupérer la demande de crédit correspondante à l'identifiant fourni
        Request request = IRequestrepository.findById(i_request).orElse(null);

        if (request == null) {
            throw new IllegalArgumentException("Invalid request id");
        }

        // Récupérer l'offre de crédit correspondante à la demande
        Offers_Credit offer = request.getOffer();
        if (offer == null) {
            throw new IllegalArgumentException("Request has no associated offer");
        }

        // Calculer les paramètres de l'amortissement
        double interestRate = Double.parseDouble(offer.getInterest_rate());
        int loanTerm = Integer.parseInt(request.getTerm_loan());
        int loanAmount = request.getAmount_req();
        double monthlyInterestRate = interestRate / 12;
        double annuity = loanAmount * monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -loanTerm));

        // Générer le tableau d'amortissement
        List<AmortizationScheduleEntry> table = new ArrayList<>();
        double capitalRemaining = loanAmount;
        for (int i = 0; i < loanTerm; i++) {
            double interest = capitalRemaining * monthlyInterestRate;
            double principal = annuity - interest;
            if (principal > capitalRemaining) {
                principal = capitalRemaining;
            }
            capitalRemaining -= principal;
            table.add(new AmortizationScheduleEntry(i+1, capitalRemaining, principal, interest, annuity));
        }
        return table;
    }



}




























