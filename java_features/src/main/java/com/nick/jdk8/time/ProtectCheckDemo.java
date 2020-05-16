package com.nick.jdk8.time;

import com.nick.object.Device;

public class ProtectCheckDemo extends Device {

    public void test() {
        super.testProtect();
        Device device = new Device();
    }

}
