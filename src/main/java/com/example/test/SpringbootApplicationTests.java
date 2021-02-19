package com.example.test;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.DemoApplication;
import com.example.demo.redis.IRedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class SpringbootApplicationTests {

    private JSONObject json = new JSONObject();

    @Autowired
    private IRedisService redisService;

    @Test
    public void demo1() {
        //设置ip地址
        Jedis jedis = new Jedis("39.107.43.71", 6379);
        jedis.auth("xyzt2019");
        //保存数据
        jedis.set("name", "test");
        String val = jedis.get("name");
        System.out.println("*************************"+val);
        //释放资源
        jedis.close();
    }
    /**
     * 插入字符串
     */
    @Test
    public void setString() {
        redisService.setValue("redis_string_test", "springboot redis test");
    }

    /**
     * 获取字符串
     */
    @Test
    public void getString() {
        System.out.println("*************************"+ redisService.getValue("redis_string_test"));
    }
    /**
     * 插入对象
     */
    @Test
    public void setObject() {
        Person person = new Person("person", "male");
        redisService.set("redis_obj_test", json.toJSONString(person));
    }

    /**
     * 获取对象
     */
    @Test
    public void getObject() {
        String result = redisService.get("redis_obj_test");
        Person person = json.parseObject(result, Person.class);
        System.out.println(json.toJSONString(person));
    }

    /**
     * 插入对象List
     */
    @Test
    public void setList() {
        Person person1 = new Person("person1", "male");
        Person person2 = new Person("person2", "female");
        Person person3 = new Person("person3", "male");
        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);
        redisService.set("redis_list_test", json.toJSONString(list));
    }

    /**
     * 获取list
     */
    @Test
    public void getList() {
        String result = redisService.get("redis_list_test");
        List<String> list = json.parseArray(result, String.class);
        System.out.println(list);
    }

    @Test
    public void remove() {
        redisService.remove("redis_test");
    }

}

class Person {
    private String name;
    private String sex;

    public Person() {

    }

    public Person(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

