package com.talos.hospital.Model.Enum;

public enum Pretence {
    NORMATIV("Normative"),
    COVERED("Covered by insurance"),
    HM("Restricted for National Defense"),
    GM("General medicine"),
    UB("Industrial Accident"),
    EK("Full coverage for lifelong diseases"),
    EE("90, 70 or 50% coverage for lifelong diseases"),
    TA("Full price");

    private final String pretence;

    Pretence(String pretence) {
        this.pretence = pretence;
    }

    public String getPretence() {
        return pretence;
    }


    @Override
    public String toString() {
        return pretence;
    }
}
