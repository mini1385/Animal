package com.je1224.animal;

public class PetInfo {
    String name;
    String birth;

    public PetInfo() {
    }

    public PetInfo(String name, String birth) {
        this.name = name;
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
