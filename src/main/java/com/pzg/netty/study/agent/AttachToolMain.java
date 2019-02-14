package com.pzg.netty.study.agent;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

/**
 * 需要使用到tool.jar
 * 将agent附加到目标进程
 * @author PENGZHIGANG
 * @date
 */
public class AttachToolMain {
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();

        for (VirtualMachineDescriptor vmd  : list) {
            if (vmd.displayName().endsWith("RunLoopAccountMain")) {
                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                // 传入参数
                virtualMachine.loadAgent("D:\\ja.jar", "argument for agent");
                System.out.println("ok");
                virtualMachine.detach();
            }
        }
    }
}
