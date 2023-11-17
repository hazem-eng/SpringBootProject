package com.example.microfinancepi.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_request;
    private String request_date;
    private Integer amount_req;
    private String description;
    private String gender;
    private Integer monthly_pay;
    private String term_loan;
    private String start_repayment;

  @ManyToOne(cascade = CascadeType.ALL)
  @JsonIgnore
  private Offers_Credit offer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;
    @OneToOne

    private AccOrRef accOrRef;


}
