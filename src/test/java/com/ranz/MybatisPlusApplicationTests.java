package com.ranz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ranz.mapper.UserMapper;
import com.ranz.pojo.User;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
//@MapperScan("com.ranz.mapper")
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = userMapper.selectById(1);
        user.setAge(20);
        user.setEmail("chenx@PROANV.COM");

        User user2 = userMapper.selectById(1);
        user2.setAge(22);
        user2.setEmail("jiang@PROANV.COM");
        int i = userMapper.updateById(user2);

        int iw = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    void Test2() {
        Page<User> var1=new Page<>(2,3);
        IPage<User> userIPage = userMapper.selectPage(var1, null);
        var1.getRecords().forEach(System.out::println);
        System.out.println(var1.getTotal());
    }

}
