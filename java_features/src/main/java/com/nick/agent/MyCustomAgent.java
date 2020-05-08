package com.nick.agent;

//import com.sun.tools.attach.VirtualMachine;

import java.lang.instrument.Instrumentation;

/**
 * @author nick
 */
public class MyCustomAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("MyCustomAgent.premain");
        customLogic(inst);
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("agentmain");
        customLogic(inst);
    }

    private static void customLogic(Instrumentation inst) {
        inst.addTransformer(new MyTransformer(), true);
        Class[] classes = inst.getAllLoadedClasses();
        for(Class cls : classes) {
            System.out.println(cls.getName());
        }
    }
}
