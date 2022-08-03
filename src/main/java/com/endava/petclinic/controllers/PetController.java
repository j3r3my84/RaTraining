package com.endava.petclinic.controllers;

import com.endava.petclinic.models.Pet;
import com.endava.petclinic.models.PetType;
import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;

public class PetController {

    public static Pet generateNewRandomPet(){
        Faker faker = new Faker();
        Pet pet = new Pet();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        pet.setName(faker.dog().name());
        pet.setOwner(OwnerController.genarateNewRandomOwner());
        pet.setType(new PetType(faker.animal().name()));
        pet.setBirthDate(formatter.format(faker.date().birthday(1,10)));
        return pet;
    }
}
