package com.broadtech.databus.soar.common;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.UUID;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * @Author: leo.j
 * @desc:
 * @Date: 2022/3/19 3:43 下午
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        for (int i = 0; i < 55; i++) {
            System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        }

        //thread pool
       /* ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future future = executorService.submit(new TaskTest());
        Future future2 = executorService.submit(new TaskTest());
        Future future3 = executorService.submit(new TaskTest());
        Future future4 = executorService.submit(new TaskTest());
        Object o = future.get();
        System.out.println(o.toString());


        Object o2 = future2.get();
        System.out.println(o2.toString());

        Object o3 = future3.get();
        System.out.println(o3.toString());
        Object o4 = future4.get();
        System.out.println(o4.toString());*/
        /*executorService.execute(() -> {
            System.out.println("=== 工作中 ===");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/

        String regStr = "{\"field\":\"sip\",\"name\":\"源ip\",\"type\":\"String\",\"reg\":\"^((2(5[0-5]|[0-4]\\\\d))|[0-1]?\\\\d{1,2})(\\\\.((2(5[0-5]|[0-4]\\\\d))|[0-1]?\\\\d{1,2})){3}$\"}";

        JSONObject jsonObject = JSONObject.parseObject(regStr);
        String reg = jsonObject.getString("reg");
        System.out.println(reg);


    }
}
class TaskTest implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println();
        System.out.println(Thread.currentThread().getId() + ": 执行任务中1...");
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getId() + ": 执行任务中2...");
        return "任务结果";
    }
}

