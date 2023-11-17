package com.example.microfinancepi.Controllers;

import com.example.microfinancepi.entities.OfferStatistics;
import com.example.microfinancepi.entities.Offers_Credit;
import com.example.microfinancepi.entities.Request;
import com.example.microfinancepi.services.Offers_CreditService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/Offers_Credit")
public class  Offers_CreditRestController {
    private Offers_CreditService offers_creditService;




    @PostMapping("/add")
    Offers_Credit addOffers_Credit(@RequestBody Offers_Credit offers_credit) {
        return offers_creditService.AddOffers_Credit(offers_credit);
    }

    @GetMapping("/all")
    List<Offers_Credit> retrieveAllOffers_Credits() {

        return offers_creditService.retrieveAllOffers_Credits();
    }

    @GetMapping("/get/{id}")
    Offers_Credit retrieveOffers_Credit(@PathVariable("id") Integer Id_offer) {
        return offers_creditService.retrieveOffers_Credit(Id_offer);
    }

    @DeleteMapping("/delete/{id}")
    void RemoveOffers_Credit(@PathVariable("id") Integer Id_offer) {
        offers_creditService.removeOffers_Credit(Id_offer);
    }

    @PutMapping("/update")
    Offers_Credit updateOffers_Credit(@RequestBody Offers_Credit offers_credit) {
        return offers_creditService.updateOffers_Credit(offers_credit);
    }
  /*  @GetMapping("/{id_offer}/{id_request}/amortization")
    public ResponseEntity<Map<Integer, Map<String, Double>>> calculateCapitalAmortiAndRestant(
            @PathVariable Integer id_offer, @PathVariable Integer id_request) {
        Map<Integer, Map<String, Double>> result = offers_creditService.calculateCapitalAmortiAndRestant(id_offer, id_request);
        return ResponseEntity.ok(result);
    }*/
    @GetMapping("/{amount}/{repaymentPeriod}/offer")
    public ResponseEntity<List<Offers_Credit>> getOffer(@PathVariable Integer amount,
                                                        @PathVariable String repaymentPeriod) {
        List<Offers_Credit> matchingOffers =  offers_creditService.findMatchingOffer(amount, repaymentPeriod);
        if (matchingOffers != null){
            return  ResponseEntity.ok(matchingOffers);
        }
        else {
            return ResponseEntity.notFound().build();
        }



    }
   @GetMapping("/statistics/{id_offer}")
    public ResponseEntity<OfferStatistics> getOfferStatistics(@PathVariable ("id_offer")Integer id_offer) {
        OfferStatistics statistics = offers_creditService.getOfferStatistics(id_offer);
        return ResponseEntity.ok(statistics);
    }




}






