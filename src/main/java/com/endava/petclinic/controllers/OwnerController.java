package com.endava.petclinic.controllers;

import com.endava.petclinic.models.Owner;
import com.github.javafaker.Faker;

public class OwnerController {

    public static Owner genarateNewRandomOwner(){
        Faker faker = new Faker();
        Owner owner = new Owner();
        owner.setFirstName( faker.name().firstName() );
        owner.setLastName( faker.name().lastName() );
        owner.setCity( faker.address().city() );
        owner.setAddress( faker.address().streetAddress() );
        owner.setTelephone( faker.number().digits( 10 ) );
        return owner;
    }
}
