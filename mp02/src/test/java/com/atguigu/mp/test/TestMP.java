package com.atguigu.mp.test;

import com.atguigu.mp.beans.Employee;
import com.atguigu.mp.mapper.EmployeeMapper;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author linzihao
 * @create 2022-10-29-14:56
 */
public class TestMP {

    private ApplicationContext ioc =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    /**
     * AR 复杂分页操作
     */
    @Test
    public void testARPage(){

        Employee employee = new Employee();
        Page<Employee> employeePage = employee.selectPage(
                new Page<Employee>(1, 2),
                new EntityWrapper<Employee>()
                        .like("last_name", "老师")
        );

        List<Employee> records = employeePage.getRecords();
        System.out.println(records);

    }

    /**
     * AR 修改操作
     */
    @Test
    public void testARUpdate(){

        Employee employee = new Employee();
        employee.setId(14);
        employee.setLastName("ybbdy");
        employee.setEmail("6666@qq.com");
        employee.setGender(1);

        boolean b = employee.updateById();

        System.out.println(b);

    }

    /**
     * AR 查询操作
     */
    @Test
    public void testARSelect(){

        Employee employee = new Employee();
        // employee.setId(14);
        // Employee employee1 = employee.selectById(14);
        // System.out.println(employee1);

        // List<Employee> employees = employee.selectAll();
        // System.out.println(employees);

        // List<Employee> employees1 = employee.selectList(new EntityWrapper().like("last_name", "老师"));
        // System.out.println(employees1);

        int count = employee.selectCount(new EntityWrapper().eq("gender", 0));
        System.out.println(count);


    }

    /**
     * AR 插入操作
     */
    @Test
    public void testARinsert(){

        Employee employee = new Employee();
        employee.setLastName("ybb");
        employee.setEmail("666@qq.com");
        employee.setGender(0);

        boolean insert = employee.insert();

        System.out.println(insert);

    }

}
