package com.example.microfinancepi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvent;
    private String nameEvent;
    private String descriptionEvent;
    private LocalDate dateEvent;
    @Enumerated(EnumType.STRING)
    private TypeEvent type;
    @OneToMany(mappedBy ="event" )
    @JsonIgnore
    private List<ShareHolder> shareHolders;


}
