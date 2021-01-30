package org.example.javaagent;

import org.example.javaagent.service.OrderService;

/**
 * <p>Title: org.example.javaagent.App </p>
 *
 * <p>Description:  </p>
 *
 * <p>Company: </p>
 *
 * <p>date: 2021/1/28 22:50 </p>
 *
 * @author: jt-ape
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        for(;;) {
            Thread.sleep(500);

            new OrderService().order();
        }
    }
}
