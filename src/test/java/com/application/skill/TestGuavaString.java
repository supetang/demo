package com.application.skill;


import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TestGuavaString {

    @Test
    public void testString(){
        String str = "hell,world,123,,test";
        String[] split = str.split(",");
        for (String s : split) {
            System.out.println("s:"+s);
        }
    }

    @Test
    public void joinerTest(){
        List<String> list = Lists.newArrayList("wowo","kkk",null,"wowoowowwow");
        //String joinStr = Joiner.on(",").skipNulls().join(list);
        String joinStr = Joiner.on(",").useForNull("----").join(list);
        System.out.println(joinStr);
    }

    @Test
    public void withMapTest() {
        Map<Integer, String> maps = Maps.newHashMap();
        maps.put(1, "哈哈");
        maps.put(2, "压压");

        String joinStr = Joiner.on(",").withKeyValueSeparator(":").join(maps);

        System.out.println(joinStr);


    }

    @Test
    public void splitterListTest() {
        //String test = "34344,34,34,哈哈";
        //String test = "    34344,34,34,,哈哈    ";

        String test = "343443434哈哈";
        //List<String> stringList = Splitter.on(",").trimResults().splitToList(test);
        //List<String> stringList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(test);
        List<String> stringList = Splitter.fixedLength(4).splitToList(test);
        for (String str : stringList) {
            System.out.println("str:"+str);
        }

    }

    /**
     * collapseFrom 配到的字符做替换
     */
    @Test
    public void testCollapseFrom() {
        String input = "     Ting Feng        ";
        String testStr = "123aaa";
        String resultStr = CharMatcher.inRange('0','9').retainFrom(testStr);

        System.out.println("resultStr:"+resultStr);


        String result = CharMatcher.breakingWhitespace().collapseFrom(input, '*');
        System.out.println(result);     // *Ting*Feng*

        result = CharMatcher.is(' ').collapseFrom(input, '-');
        System.out.println(result);     // -Ting-Feng-
    }

    /**
     * trimFrom 去空格
     * trimLeadingFrom 左边去空格
     * trimTrailingFrom右边去空格
     */
    @Test
    public void testTrim() {
        System.out.println(CharMatcher.breakingWhitespace().trimFrom("     Ting Feng     "));           // Ting Feng
        System.out.println(CharMatcher.breakingWhitespace().trimLeadingFrom("     Ting Feng     "));    // Ting Feng
        System.out.println(CharMatcher.breakingWhitespace().trimTrailingFrom("     Ting Feng     "));   //      Ting Feng
    }


    /**
     * is 匹配参数之内的所有字符
     * isNot 匹配参数之外的所有字符
     */
    @Test
    public void testIs_isNot(){
        String input = "a, c, z, 1, 2";
        System.out.println(CharMatcher.is(',').retainFrom(input));   // ,,,,
        System.out.println(CharMatcher.is(',').removeFrom(input));   // a c z 1 2
//
//        System.out.println(CharMatcher.isNot(',').retainFrom(input));   // a c z 1 2
//        System.out.println(CharMatcher.isNot(',').removeFrom(input));   // ,,,,
    }


    /**
     * matchesAllOf 判断sequence所有字符是否都被charMatcher匹配
     * matchesAnyOf 判断sequence中是否存在字符被charMatcher匹配
     * matchesNoneOf 判断sequence所有字符是否都没被charMatcher匹配
     */
    @Test
    public void test_matchesAllOf_matchesAnyOf_matchesNoneOf(){
        String input = "**e,l.lo,}12";

        CharMatcher matcher = CharMatcher.is(',');
        System.out.println(matcher.matchesAllOf(input));    // false

        matcher = CharMatcher.is(',');
        System.out.println(matcher.matchesAnyOf(input));    // true

        matcher = CharMatcher.is('?');
        System.out.println(matcher.matchesNoneOf(input));   // true
    }


    /**
     * 匹配任意字符
     */
    @Test
    public void testAny() {
        String input = "H*el.lo,}12";

        CharMatcher matcher = CharMatcher.any();
        String result = matcher.retainFrom(input);
        System.out.println(result); //  H*el.lo,}12

        matcher = CharMatcher.anyOf("Hel");
        System.out.println(matcher.retainFrom(input)); // Hell
        System.out.println(matcher.removeFrom(input)); // *.o,}12
    }




}
