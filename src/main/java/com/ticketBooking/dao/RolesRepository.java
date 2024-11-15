package com.ticketBooking.dao;


import com.ticketBooking.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role, Long > {

    Optional<Role>findByRoleName (String role);

}
