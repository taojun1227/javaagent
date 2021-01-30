package org.example.javaagent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * <p>Title: org.example.javaagent.MyJavaAgent </p>
 *
 * <p>Description:  </p>
 *
 * <p>Company: </p>
 *
 * <p>date: 2021/1/28 23:15 </p>
 *
 * @author: jt-ape
 */
public class MyJavaAgent {

    /**
     *  这个是静态调用，在启动jvm添加-javaagent参数后会调用
     *  运行在main方法之前，虚拟机会先尝试运行这个
     * @param agentArgs 加载agent时传递给agent的参数
     * @param inst 提供检测 Java 编程语言代码所需的服务。检测是向方法中添加字节码，
     * @return: void
     * @Author: jt-ape
     * @Date: 2021/1/28 23:18
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("调用方法:premain(String agentArgs, Instrumentation inst)");
        System.out.println("javaagent 参数："+agentArgs);
        inst.addTransformer(new MyClassFileTransformer(),true);
    }

    /**
     * 运行在main方法之前，不存在上面方法，虚拟机才会运行这个
     * @param agentArgs
     * @return: void
     * @Author: jt-ape
     * @Date: 2021/1/28 23:18
     */
    public static void premain(String agentArgs) {

    }


    /**
     *  动态调用，虚拟机启动之后执行
     *  虚拟机会先尝试运行这个
     * @param agentArgs 加载agent时传递给agent的参数
     * @param inst 提供检测 Java 编程语言代码所需的服务。检测是向方法中添加字节码，
     * @return: void
     * @Author: jt-ape
     * @Date: 2021/1/28 23:54
     */
    public static void agentmain(String agentArgs, Instrumentation inst){
        System.out.println("调用方法:agentmain(String agentArgs, Instrumentation inst)");
        System.out.println("javaagent 参数："+agentArgs);

        inst.addTransformer(new MyClassFileTransformer(),true);

        Class[] allLoadedClasses = inst.getAllLoadedClasses();
        for (Class allLoadedClass : allLoadedClasses) {
            if (allLoadedClass.getName().equals("org.example.javaagent.service.OrderService")) {
                try {

                    inst.retransformClasses(allLoadedClass);
                    break;
                } catch (UnmodifiableClassException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     *  不存在上面的agentmain方法才会调用本方法
     * @param agentArgs
     * @return: void
     * @Author: jt-ape
     * @Date: 2021/1/30 14:27
     */
    public static void agentmain(String agentArgs){

    }
}
