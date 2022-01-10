package com.application.skill;

import com.application.skill.crud.mapper.UserMapper;
import com.application.skill.crud.model.User;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = SkillApplicationTests.class)
public class SkillApplicationTests {

    public UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        for(User user:userList) {
            System.out.println(user);
        }
    }
}
