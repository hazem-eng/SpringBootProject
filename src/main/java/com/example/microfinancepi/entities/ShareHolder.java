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
public class ShareHolder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idShareholder;
    private String lastNameShareholder;
    private String FirstNameShareholder;
    private double investment;
    private String Email;
    private int numTel;
    @Enumerated(EnumType.STRING)
    private TypeShareholder partner;
    @ManyToOne
    @JsonIgnore
    private Event event;
}
