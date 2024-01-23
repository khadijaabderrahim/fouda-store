package com.sema4.foudastore.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    // id, firstname, lastname, email
    private Long id;
    private String firstname;

    private String lastname;

    private String email;


}
