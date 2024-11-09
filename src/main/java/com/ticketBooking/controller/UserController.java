package com.ticketBooking.controller;


import com.ticketBooking.dao.RolesRepository;
import com.ticketBooking.dao.UserRepository;
import com.ticketBooking.dto.RoleDTO;
import com.ticketBooking.dto.UserDTO;
import com.ticketBooking.entity.Role;
import com.ticketBooking.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    RolesRepository roleRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    //http://localhost:8081/api/user/registerUser
    @PostMapping("/registerUser")
    public ResponseEntity<String>  registerUser(@RequestBody UserDTO userDTO){


        User user = new User();
        user.setName(userDTO.getName());

        user.setEmail(userDTO.getEmail());
      user.setPassword(passwordEncoder.encode(userDTO.getPassword()));


        RoleDTO role = userDTO.getRole();

        Role existingRole = roleRepository.findByRoleName(role.getRoleName()).get();

        user.setRole(existingRole);

         existingRole.getUser().add(user);

         userRepository.save(user);




//
//        Role role = new Role();
//        role.setId(1);
//        role.setName("ROLE_ADMIN");
//
//        Role role1 = new Role(); // for  defining  new role to the user you need to  create new method like this  means signup 2 which will directly
//        //  map the incoming  user to role 2
//        role1.setId(2);
//        role1.setName("ROLE_USER");
//
//        roleRepository.save(role);  //save parent object
//        roleRepository.save(role1);  //save parent object
//
//
//        List<Role> roles  = new ArrayList<>();
//        roles.add(role);
//
//        user.setRole(roles);         // child object.set(parent object)
//        userRepository.save(user);   //   save  child object
//

       return new ResponseEntity<>("user Registered", HttpStatus.CREATED);
    }

    //http://localhost:8081/api/user/addRole
    @PostMapping("/addRole")
    public void addRole(){

        Role role = new Role();

        role.setId(1);
        role.setRoleName("ADMIN");

        roleRepository.save(role);

        Role role1 = new Role();

        role1.setId(2);
        role1.setRoleName("USER");

        roleRepository.save(role1);

    }


}
