package com.nick.object;

import lombok.Data;

@Data
public class Device implements Cloneable{

    private Long id;

    private String name;

    private String location;

    public static Device of(Long id, String name, String location) {
        Device device = new Device();
        device.id = id;
        device.name = name;
        device.location = location;
        return device;
    }

    @Override
    public Device clone() {
        Device device = null;
        try {
            device = (Device) super.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return device;
    }

    @Override
    public String toString() {
        return "id: " + this.id + " || " +
                "name: " + this.name + " || " +
                "location: " + this.location;
    }

    protected void testProtect() {

    }

    void testDefaultAccess() {

    }

}
