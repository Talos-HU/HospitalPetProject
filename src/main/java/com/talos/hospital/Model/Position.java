package com.talos.hospital.Model;

public enum Position {
    MEDICAL_STUDENT("Medical Student"),
    INTERN("Intern"),
    RESIDENT("Resident"),
    FELLOW("Fellow"),
    ATTENDING_PHYSICIAN("Attending Physician"),
    RESEARCHER("Researcher");

    private final String position;

    Position(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return position;
    }


}
