package com.application.skill;

import com.google.common.collect.*;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.util.*;

public class TestGuavaCollection {

    @Test
    public void test1(){
        String[] words = {"关注","guava集合","影子","大宗师苦荷","影子","guava集合"};
//        Map<String, Integer> counts = new HashMap<String, Integer>();
//        for (String word : words) {
//            Integer count = counts.get(word);
//            if (count == null) {
//                counts.put(word, 1);
//            } else {
//                counts.put(word, count + 1);
//            }
//        }
//
//        Integer count = counts.get("影子");
//        System.out.println("count:"+count);

        HashMultiset<@Nullable Object> multiset = HashMultiset.create();

        for (String word : words) {
            multiset.add(word);
        }


        Integer count = multiset.count("影子");
        System.out.println("count:"+count);
    }

    @Test
    public void test2(){
//        Map<String, List<Object>> map = new HashMap<>();
//
//        List<Object> nameList = new ArrayList();
//        nameList.add("Jack");
//        nameList.add("Tom");
//
//
//        List<Object> ageList = new ArrayList();
//        ageList.add(23);
//        ageList.add(45);
//        map.put("name",nameList);
//        map.put("age",ageList);
//
//        System.out.println(map);


        //HashMultimap<@Nullable Object, @Nullable Object> hashMultimap = HashMultimap.create();
        ArrayListMultimap<@Nullable Object, @Nullable Object> hashMultimap = ArrayListMultimap.create();

        hashMultimap.put("name","Jack");
        hashMultimap.put("name","Tom");

        hashMultimap.put("age",34);
        hashMultimap.put("age",34);

        System.out.println(hashMultimap);


    }


    @Test
    public void test3(){
//        Map<String,Integer> nameToId = new HashMap<>();
//        Map<Integer,String> idToName = new HashMap<>();
//        nameToId.put("Jack",33);
//        idToName.put(33,"Jack");
//        //维护双向关系,每次都需要修改两个map;
//
//        nameToId.put("Tom",45);
//        idToName.put(45,"Tom");

        HashBiMap<@Nullable Object, @Nullable Object> hashBiMap = HashBiMap.create();

        hashBiMap.put("JACK",33);
        hashBiMap.put("Tom",45);

        //hashBiMap.put("multi",33);//value重复会抛出异常
        hashBiMap.forcePut("multi",33);

        System.out.println(hashBiMap.inverse().get(33));
        System.out.println(hashBiMap.get("JACK"));

        System.out.println(hashBiMap.inverse().inverse()==hashBiMap);

    }


    @Test
    public void test4(){
        //List<String> list = Lists.asList("1", new String[]{"2", "aaa"});
        ArrayList<String> list = Lists.newArrayList("aaa", "bbb", "ccc");
        System.out.println(list);

    }

    @Test
    public void partitionTest(){
        List<String> list = Lists.newArrayList("a","b","c","d","e");
        //将list按大小为2分隔成多个list
        List<List<String>> splitList = Lists.partition(list,2);
        System.out.println(splitList);

    }

    @Test
    public void cartesianProcustTest(){
        List<String> list1 = Lists.newArrayList("a","b");
        List<String> list2 = Lists.newArrayList("d","e");
        //获取多个list的笛卡尔集
        List<List<String>> list = Lists.cartesianProduct(list1,list2);
        System.out.println(list);
    }
    @Test
    public void transFormTest(){
        List<String> list = Lists.newArrayList("a","b","c");
        //把list中的每个元素拼接一个1
        List<String> list1 = Lists.transform(list,str -> str + "1");
        System.out.println(list1);
    }


    @Test
    public void combinationsTest(){
        //将集合中的元素按指定的大小分隔，指定大小的所有组合
        Set<String> set1 = Sets.newHashSet("a","b","c","d");
        Set<Set<String>> sets = Sets.combinations(set1,3);
        for(Set<String> set : sets){
            System.out.println(set);
        }
    }


    @Test
    public void differenceTest(){
        Set<String> set1 = Sets.newHashSet("a","b","d");
        Set<String> set2 = Sets.newHashSet("d","e","f");
        //difference返回：从set1中剔除两个set公共的元素
        System.out.println(Sets.difference(set1,set2));
    }

    @Test
    public void powerSetTest(){
        Set<String> set1 = Sets.newHashSet("a","b","c");
        //获取set可分隔成的所有子集
        Set<Set<String>> allSet = Sets.powerSet(set1);
        for(Set<String> set : allSet){
            System.out.println(set);
        }
    }


    @Test
    public void asMapTest(){
        Set<String> set = Sets.newHashSet("a","b","c");
        //将set转成Map,key为set元素,value为每个元素的长度
        Map<String,Integer> map = Maps.asMap(set,String::length);
        System.out.println(map);
    }


    @Test
    public void filterEntriesTest(){
        Map<String,String> map1 = Maps.newHashMap();
        map1.put("a","1");
        map1.put("b","2");
        map1.put("c","3");
        Map<String,String> result = Maps.filterEntries(map1,item -> item.getValue().equalsIgnoreCase("2"));
        System.out.println(result);

    }


    @Test
    public void transFormEntriesTest(){
        Map<String,String> map1 = Maps.newHashMap();
        map1.put("a","1");
        map1.put("b","2");
        map1.put("c","3");
        Map<String,String> result = Maps.transformEntries(map1,(k,v) -> k + v);
        System.out.println(result);
    }


    @Test
    public void test5(){
        List<Integer> list = Lists.newArrayList(1,2,3);
        List<Integer> unmodifiableList = Collections.unmodifiableList(list);

        // [1, 2, 3]
        System.out.println(list);
        // [1, 2, 3]
        System.out.println(unmodifiableList);
        // list修改，list1也会被修改
        list.add(4);
        // [1, 2, 3, 4]
        System.out.println(unmodifiableList);
    }


    @Test
    public void test6(){
        List<Integer> list = Lists.newArrayList(1,2,3);
        ImmutableList<Integer> immutableList = ImmutableList.copyOf(list);

        // [1, 2, 3]
        System.out.println(list);
        // [1, 2, 3]
        System.out.println(immutableList);
        System.out.println("------------------------");
        // list修改，list1也会被修改
        list.add(4);
        System.out.println("list:"+list);
        // [1, 2, 3, 4]
        System.out.println("immutableList:"+immutableList);
    }






}
