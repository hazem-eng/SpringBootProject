package com.example.microfinancepi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Offers_Credit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_offer;
    @Enumerated(EnumType.STRING)
    private Type_of_credit credit;
    private Integer max_amount;
    private Integer min_amount;
    private String interest_rate;
    private String image;

    private String date_creation;

    private String repayment_period;



    @OneToMany(mappedBy ="offer" )
    @JsonIgnore
    private List<Request> requests;







}
