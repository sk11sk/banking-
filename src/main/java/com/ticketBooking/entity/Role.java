package com.ticketBooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table( name  = "roles")
public class Role {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String roleName;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private List<User>user  = new ArrayList<>();

}