package com.capgemini.gamecapmates.mapper;

import com.capgemini.gamecapmates.GameCapMatesBoardApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameCapMatesBoardApplication.class)
public class UserMapperTest {

    @Test
    public void checkingCalculateAge() {
        UserMapper userMapper = new UserMapper();

        int age = userMapper.calculateAge(LocalDate.of(1995, 7, 10));

        Assert.assertEquals(23, age);

    }
}
