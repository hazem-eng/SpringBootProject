package com.example.microfinancepi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;
    private String user_firstname;
    private String user_lastname;
    private String email_address;
    private String user_password;
    @Enumerated
    private User_role role;
    private String profile_picture;
    private String user_phone;
    private String Fn_rapport;
    private String badge;
    private String Sector_activite;

    @ManyToMany(mappedBy = "userSet")
    @JsonIgnore
    private Set<Event> eventSet;

}
