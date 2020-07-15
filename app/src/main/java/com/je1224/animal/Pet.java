package com.je1224.animal;

public class Pet {
    String petName;
    String petGender;
    String petBirth;

    public Pet() {
    }

    public Pet(String petName, String petGender, String petBirth) {
        this.petName = petName;
        this.petGender = petGender;
        this.petBirth = petBirth;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    public String getPetBirth() {
        return petBirth;
    }

    public void setPetBirth(String petBirth) {
        this.petBirth = petBirth;
    }
}
