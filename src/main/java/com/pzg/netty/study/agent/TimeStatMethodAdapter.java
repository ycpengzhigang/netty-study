package com.pzg.netty.study.agent;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * @author PENGZHIGANG
 * @date
 */
public class TimeStatMethodAdapter extends MethodVisitor implements Opcodes {

    public TimeStatMethodAdapter( MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    public  void visitCode(){
        visitMethodInsn(Opcodes.INVOKESTATIC,"com.pzg.netty.study.agent.TimeStat","start","()V");
        super.visitCode();
    }

    /**
     * 重写快捷键 ctrl + o     ctrl + alter + o  去掉不必要的导入 alter + 7 显示所有的字段列表
     *           atler + / 提示信息
     */
    @Override
    public void visitInsn(int opcode) {
        if (opcode >= RETURN && opcode <= RETURN) {
            visitMethodInsn(Opcodes.INVOKESTATIC,"com.pzg.netty.study.agent.TimeStat","end","()V");
        }
        mv.visitInsn(opcode);
    }
}
