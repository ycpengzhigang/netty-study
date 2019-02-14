package com.pzg.netty.study.agent;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * @author PENGZHIGANG
 * @date
 */
public class TimeStatClassAdapter extends ClassVisitor {

    public TimeStatClassAdapter(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    public MethodVisitor visitorMethod(final int access, final String name, final String desc, final String signature, final String[] exception){
        // ctrl +  alter + v 提取本地变量
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exception);
        MethodVisitor wrappedMv = null;

        if (mv != null) {
            if (name.equals("operation")) {
                wrappedMv = new TimeStatMethodAdapter(mv);
            }
        }
        return wrappedMv;
    }

}
