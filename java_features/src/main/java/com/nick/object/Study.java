package com.nick.object;

import lombok.Data;

@Data
public class Study implements Cloneable{

    private Long id;

    private String desc;

    private Device device;

    public static Study of(Long id, String desc, Device device) {
        Study study = new Study();
        study.id = id;
        study.desc = desc;
        study.device = device;
        return study;
    }

    public Study clone() {
        Study study = null;
        try {
            study = (Study) super.clone();
        } catch(CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        study.device = (Device) device.clone();
        return study;
    }

    public String toString() {
        return "id: " + this.id + " || " +
                "desc: " + this.desc + " || " +
                "device: " + this.device.toString();
    }
}
