package com.nick.object;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class CloneTest {

    public static void main(String arg[]) throws Exception {
        Device device1 = Device.of(1L, "D1", "B1");
        Study study1 = Study.of(1L, "S1", device1);

        Study study2 = new Study();
        BeanUtils.copyProperties(study2, study1);
        study2.getDevice().setId(2L);
        study2.setDesc("S2");

        System.out.println(study1.toString());
        System.out.println(study2.toString());

        System.out.println("========================================================");

        Study study3 = (Study) BeanUtils.cloneBean(study1);
        study3.setDesc("S3");
        study3.getDevice().setName("D3");
        System.out.println(study1.toString());
        System.out.println(study3.toString());

        System.out.println("========================================================");

        Study study4 = study1.clone();
        study4.getDevice().setName("D4");
        System.out.println(study1);
        System.out.println(study4);
    }
}
