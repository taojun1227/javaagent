package org.example.javaagent;


import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * <p>Title: org.example.javaagent.Attach </p>
 *
 * <p>Description:  </p>
 *
 * <p>Company: </p>
 *
 * <p>date: 2021/1/28 23:58 </p>
 *
 * @author: jt-ape
 */
public class Attach {
    public static void main(String[] args) throws Exception {
        // 查找所有JVM经常
        List<VirtualMachineDescriptor> attachs = VirtualMachine.list();
        attachs.stream().forEach(jvm -> {
            System.out.println(jvm.displayName()+"："+ jvm.id());
        });

        System.out.println("请输入jvm进程ID");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        for(VirtualMachineDescriptor jvm:attachs){
           if(s.equals(jvm.id())) {
               VirtualMachine attach = null;
               try {
                   attach = VirtualMachine.attach(jvm);
                   attach.loadAgent("E:\\self\\learn\\javaagent-demo-1.0-SNAPSHOT.jar","agentagent");
                   attach.detach();
               } catch (AttachNotSupportedException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               } catch (AgentLoadException e) {
                   e.printStackTrace();
               } catch (AgentInitializationException e) {
                   e.printStackTrace();
               }

              break;
           }
        }


    }
}
