package com.application.skill;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalListeners;
import org.junit.Test;

import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestGuavaCache {
    @Test
    public void test1() throws ExecutionException {

        //创建Guava缓存
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build(new CacheLoader<String, String>() {

                    //当缓存里面没有相应的key的时候，会自动调用load方法
                    @Override
                    public String load(String key) throws Exception {
                        //去数据库查询
                        return "load_" + key;
                    }
                });

        String key1 = loadingCache.get("key1");
        System.out.println("key1:"+key1);


        System.out.println("------------------");
        loadingCache.put("key2","value2");
        String key2 = loadingCache.get("key2");
        System.out.println("key2:"+key2);


    }

    @Test
    public void test2() throws ExecutionException {

        //创建Guava缓存
        Cache<String, String> cache =CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build();

        String key1 = "key1";
        String value1 = cache.get(key1, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "call_" + key1;
            }
        });
        System.out.println("key1:"+value1);


    }



    /**
     * 初始化缓存
     * @param cache
     */
    public static void initCache(LoadingCache cache) throws ExecutionException {
        /* 前三条记录 */
        for (int i = 1; i <= 3; i++) {

            //get时候没数据 会 load装配进去   相当一个连接数据源，如果缓存没有 则读取数据源，加载load方法
            cache.get(String.valueOf(i));
        }
    }

    /**
     * 打印缓存中所有的内容
     * @param cache
     */
    public  void displayCache(LoadingCache cache){
        Iterator its = cache.asMap().entrySet().iterator();
        while (its.hasNext()) {
            System.out.println(its.next().toString());
        }
    }


    @Test
    public void testClear1() throws ExecutionException {

        //创建Guava缓存
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(3)//最大的元素个数是3
                //读写缓存后多久过期
                //.expireAfterAccess(3, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {

                    //当缓存里面没有相应的key的时候，会自动调用load方法
                    @Override
                    public String load(String key) throws Exception {
                        //去数据库查询
                        return "load_" + key;
                    }
                });

        //初始化
        initCache(loadingCache);
        displayCache(loadingCache);

        System.out.println("------------------");
        loadingCache.get("4");//往里面添加一个元素
        displayCache(loadingCache);



    }


    @Test
    public void testClear2() throws ExecutionException, InterruptedException {

        //创建Guava缓存
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(3)//最大的元素个数是3
                //读写缓存后多久过期
                //.expireAfterAccess(3, TimeUnit.SECONDS)
                ////写多长时间后删除
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {

                    //当缓存里面没有相应的key的时候，会自动调用load方法
                    @Override
                    public String load(String key) throws Exception {
                        //去数据库查询
                        return "load_" + key;
                    }
                });

        //初始化
        initCache(loadingCache);
        Thread.sleep(1000);
        //不会调用load()方法
        loadingCache.getIfPresent("3");//访问了一个元素
        System.out.println("------------------");

        displayCache(loadingCache);
        //歇了2.1秒
        Thread.sleep(2100);
        //最后缓存中会留下1
        System.out.println("=============================");
        displayCache(loadingCache);
        Thread.sleep(1100);
        System.out.println("=============================");
        displayCache(loadingCache);//没有数据了


    }


    /**
     * 测试缓存清除策略3：基于引用的删除
     */
    @Test
    public void testClear3() throws InterruptedException, ExecutionException {
        LoadingCache<String, Object> cache = CacheBuilder.newBuilder()
                ////最大缓存个数 3
                .maximumSize(3)
                //读写缓存后多久过期
                //.expireAfterAccess(3, TimeUnit.SECONDS)
                //写多长时间后删除
                //.expireAfterWrite(3, TimeUnit.SECONDS)
                //基于引用的删除
                .weakValues()
                .build(new CacheLoader<String, Object>() {
                    //读数据源 加载数据到缓存
                    @Override
                    public String load(String key) throws Exception {
                        return "value_"+key;
                    }
                });

        Object value = new Object();
        cache.put("1",value);
        value = new Object(); //原对象不再有强引用
        // 强制垃圾回收
        System.gc();
        System.out.println(cache.getIfPresent("1"));

    }


    @Test
    public void test4(){

        RemovalListener<String, String> listener = notification -> {
            if(notification.wasEvicted()){
                //监听方法中特别慢
                System.out.println("------耗时操作--------begin");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("------耗时操作--------end");

                RemovalCause cause = notification.getCause();
                System.out.println("remove cause is "+cause.toString());
                System.out.println("[" + notification.getKey() + ":" + notification.getValue() + "] is removed!");
            }
        };
        Cache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(4)
                .recordStats()//统计
                //添加同步删除监听
                //.removalListener(listener)
                //添加异步删除监听
                .removalListener(RemovalListeners.asynchronous(listener, Executors.newSingleThreadExecutor()))
                .build();

        cache.put("guava1","guava1");
        cache.put("guava2","guava1");
        cache.put("guava3","guava1");
        cache.put("guava4","guava1");

        //cache.put("guava5","guava1");//第5个时候删除

        System.out.println(cache.stats().toString());
        System.out.println("-----------主程序end-------------");

    }
}
