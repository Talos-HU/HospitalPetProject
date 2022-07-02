package com.talos.hospital.Model.Enum;

public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;

    }

    @Override
    public String toString() {
        return gender;
    }
}
