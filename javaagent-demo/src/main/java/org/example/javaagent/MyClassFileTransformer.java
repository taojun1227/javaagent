package org.example.javaagent;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * <p>Title: org.example.javaagent.My </p>
 *
 * <p>Description:  </p>
 *
 * <p>Company: </p>
 *
 * <p>date: 2021/1/28 23:20 </p>
 *
 * @author: jt-ape
 */
public class MyClassFileTransformer implements ClassFileTransformer {
    /**
     * 字节码被加载到虚拟机前会调用这个方法
     * @param loader 类加载器
     * @param className 类的全限定名
     * @param classBeingRedefined 如果这是由重新定义或重新转换触发的，则这个类存在重新定义或重新转换，否则为null
     * @param protectionDomain 正在定义或重新定义的类的保护域
     * @param classfileBuffer 类文件格式的输入字节缓冲区-不能修改
     * return byte[] 不想改变任何代码，那么返回null。否则，应该创建一个新的byte[]
     */
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.equals("org/example/javaagent/service/OrderService")) {
            System.out.println(className + "：进入ClassFileTransformer");
            try {
                ClassPool classPool = ClassPool.getDefault();
                classPool.appendClassPath(new LoaderClassPath(loader));
                CtClass clazz = classPool.makeClass(new ByteArrayInputStream(classfileBuffer), false);

                //重写toString方法，将sex属性加入返回结果中。
                CtMethod method = clazz.getDeclaredMethod("order");
                method.insertBefore("System.out.print(\"我在这个方法前面加点东西哈\");");
                return clazz.toBytecode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
