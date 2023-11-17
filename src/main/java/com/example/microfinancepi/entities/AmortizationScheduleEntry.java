package com.example.microfinancepi.entities;
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
public class AmortizationScheduleEntry implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer year;
    private Double capitalRemaining;
    private Double principal;
    private Double interest;
    private Double annuity;


    public AmortizationScheduleEntry( int year, double capitalRemaining, double principal, double interest, double annuity) {
        this.year = year;
        this.capitalRemaining = capitalRemaining;
        this.principal = principal;
        this.interest = interest;
        this.annuity = annuity;
    }

}
