package com.ticketBooking.dto;


import lombok.Data;

@Data
public class UserDTO {

    private String name;

    private String email;
    private String password;

  private RoleDTO role;
}
