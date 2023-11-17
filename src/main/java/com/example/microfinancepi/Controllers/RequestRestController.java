package com.example.microfinancepi.Controllers;

import com.example.microfinancepi.entities.AccOrRef;
import com.example.microfinancepi.entities.AmortizationScheduleEntry;
import com.example.microfinancepi.entities.Request;
import com.example.microfinancepi.services.RequestService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")

@RestController
@AllArgsConstructor
@RequestMapping("/Request")

public class RequestRestController {
    private RequestService requestService;

    @PostMapping("/add")
    Request addRequest(@RequestBody Request request){
        return requestService.AddRequest(request);
    }


    @GetMapping("/all")
    List<Request> retrieveAllRequests(){

        return requestService.retrieveAllRequests();
    }


    @GetMapping("/AllMatching")
    List<AccOrRef> retrieveAllMatchings(){
        return requestService.retrieveAllMatchings();
    }


    @GetMapping("/get/{id}")
    Request retrieveRequest(@PathVariable("id") Integer IdRequest){
        return requestService.retrieveRequest(IdRequest);
    }
    @DeleteMapping("/delete/{id_req}")
    void RemoveRequest(@PathVariable("id_req") Integer IdRequest){
        requestService.removeRequest(IdRequest);
    }
    @PutMapping ("/update")
    Request updateRequest(@RequestBody Request request){
        return requestService.updateRequest(request);
    }
    @PutMapping   ("/assignreqtooffers_credit/{id_request}/{id_offer}")
    public Request assignreqtooffers_credit(@PathVariable("id_request") Integer id_request, @PathVariable("id_offer") Integer id_offer){
        return requestService.assignRequestToOffers_Credit(id_request,id_offer);
    }


    @PostMapping("/mat/{id_request}")
    void matching(@PathVariable("id_request") Integer id_request){
        requestService.matching(id_request);
    }



    @GetMapping("/users/{id_request}/loans")
    public ResponseEntity<List<String>> getLoanStatusForUser(@PathVariable Integer id_request) {
        List<String> loanStatusList = requestService.check_loan(id_request);
        return ResponseEntity.ok(loanStatusList);
    }

    @GetMapping("/amortization/{requestId}")
    public List<AmortizationScheduleEntry> getAmortizationSchedule(@PathVariable Integer requestId) {
        return requestService.calculateAmortizationTable(requestId);
    }












 }


