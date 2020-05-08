package com.nick.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author nick
 */
public class MyTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("loading class: " + className);
        if(!"".equals(className)) {
            return classfileBuffer;
        }

        CtClass cl = null;
        try {
            ClassPool classPool = ClassPool.getDefault();
            cl = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
            CtMethod ctMethod = cl.getDeclaredMethod("test");
            System.out.println("Get method name: " + ctMethod.getName());

            ctMethod.insertBefore("System.out.println(\" Dynamic inserted comments \");");
            ctMethod.insertAfter("System.out.println($_);");

            byte[] transformed = cl.toBytecode();
            return transformed;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return classfileBuffer;
    }
}
