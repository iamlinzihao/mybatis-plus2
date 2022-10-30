package com.atguigu.mp.test;


import com.atguigu.mp.beans.Employee;
import com.atguigu.mp.beans.User;
import com.atguigu.mp.mapper.EmployeeMapper;
import com.atguigu.mp.mapper.UserMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author linzihao
 * @create 2022-10-29-14:56
 */
public class TestMP {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    EmployeeMapper employeeMapper = ctx.getBean("employeeMapper",EmployeeMapper.class);

    UserMapper userMapper = ctx.getBean("userMapper", UserMapper.class);

    /**
     * 测试公共字段填充
     */
    @Test
    public void testMetaObjectHandler(){

        User user = new User();
        user.setId(5);
        user.setLogicFlag(1);
        userMapper.updateById(user);

    }

    /**
     * 测试逻辑删除
     */
    @Test
    public void testLogicDelete(){

        // Integer integer = userMapper.deleteById(1);
        // System.out.println(integer);

        User user = userMapper.selectById(1);
        System.out.println(user);

    }

    /**
     * 测试自定义全局操作
     */
    @Test
    public void testMySqlInjector(){

        int i = employeeMapper.deleteAll();
        System.out.println("result: " + i);

    }

    /**
     * 测试乐观锁插件
     */
    @Test
    public void testOptimisticLocker(){

        //更新操作
        Employee employee = new Employee();
        employee.setId(16);
        employee.setLastName("jntm");
        employee.setEmail("jntm@sina.com");
        employee.setGender("0");
        employee.setAge(18);
        employee.setVersion(2);

        employeeMapper.updateById(employee);

    }

    /**
     * 测试性能分析插件
     */
    @Test
    public void testPerformance(){

        Employee employee = new Employee();
        employee.setLastName("Julia老师");
        employee.setEmail("Julia@sina.com");
        employee.setGender("0");
        employee.setAge(22);

        employeeMapper.insert(employee);

    }

    /**
     * 测试SQL执行分析插件
     */
    @Test
    public void testSQLExplain(){

        employeeMapper.delete(null); //全表删除

    }

    /**
     * 测试分页插件
     */
    @Test
    public void testPage(){

        Page<Employee> page = new Page<>(2, 3);
        List<Employee> employees = employeeMapper.selectPage(page, null);
        System.out.println(employees);

        System.out.println("=========================获取分页的相关信息=========================");

        System.out.println("总记录数：" + page.getTotal());
        System.out.println("当前页码：" + page.getCurrent());
        System.out.println("总页码：" + page.getPages());
        System.out.println("每页的条数：" + page.getSize());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());

        //将查询对象封装到page对象中
        page.setRecords(employees);
    }
}
