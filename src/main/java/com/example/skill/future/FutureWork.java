package com.example.skill.future;

import com.example.skill.model.User;
import com.google.common.collect.Lists;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureWork {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Future<?> submit = executor.submit(() -> {
            System.out.println("this task start do work");
            RestTemplate restTemplate = new RestTemplate();
            String forObject = restTemplate.getForObject("https://www.baidu.com/", String.class, new Object[]{});
            System.out.println(forObject);

//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            // TODO 模拟调用rpc的接口 或者是批量处理操作 或者是一些长时间的操作
            User user = new User();
            user.setUserName("tangchao");
            user.setSex(1);
            user.setPhoneNumBer("17688780000");
            List<User> list = Lists.newArrayList();
            list.add(user);
            return user;
        });

        // 相关的其他的业务处理逻辑

        System.out.println("other to do somethings");

        try {
            System.out.println(submit.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("has got the result");
    }
}
