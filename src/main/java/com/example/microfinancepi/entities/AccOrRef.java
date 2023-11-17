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

@Data
@Builder

@Entity
public class AccOrRef implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_AccOrRef;
    private String check_loan;


    @OneToOne
    @JsonIgnore
    private Request request;



}
