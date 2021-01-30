package org.example.javaagent.service;

/**
 * <p>Title: org.example.javaagent.service.OrderService </p>
 *
 * <p>Description:  </p>
 *
 * <p>Company: </p>
 *
 * <p>date: 2021/1/28 22:50 </p>
 *
 * @author: jt-ape
 */
public class OrderService {
    public void order() {
        System.out.println(Thread.currentThread().getName()+"下了一个订单。。。");
    }
}
