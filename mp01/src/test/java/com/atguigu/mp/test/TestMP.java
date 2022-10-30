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

    private EmployeeMapper employeeMapper =
            ioc.getBean("employeeMapper",EmployeeMapper.class);

    /**
     * 条件构造器 删除操作
     */
    @Test
    public void testEntityWrapperDelete(){

        employeeMapper.delete(new EntityWrapper<Employee>()
                .eq("last_name", "Tom")
                .eq("age",45));
    }

    /**
     * 条件构造器 修改操作
     */
    @Test
    public void testEntityWrapperUpdate(){

        Employee employee = new Employee();
        employee.setLastName("三上悠亚");
        employee.setEmail("sanshangyouya@sina.com");

        Integer update = employeeMapper.update(employee,
                new EntityWrapper<Employee>()
                        .eq("last_name", "Tom")
                        .eq("age", 49)
        );

        System.out.println("result = " + update);

    }

    /**
     * 条件构造器 查询操作
     */
    @Test
    public void testEntityWrapperSelect(){

        //现有一个需求，我们需要分页查询 tbl_employee 表中，年龄在 18~50 之间性别为男且
        // 姓名为 Tom 的所有用户，
        // List<Employee> employees = employeeMapper.selectPage(new Page<Employee>(1, 2),
        //         new EntityWrapper<Employee>()
        //                 .between("age", 18, 50)
        //                 .eq("gender", 1)
        //                 .eq("last_name", "Tom")
        // );
        //
        // System.out.println(employees);

        List list = employeeMapper.selectPage(
                new Page<Employee>(1, 2),
                Condition.create()
                        .between("age", 18, 50)
                        .eq("gender", 1)
                        .eq("last_name", "Tom"));

        System.out.println(list);

        //查询tbl_employee表中，性别为女且带有“老师”字眼或者邮箱中带有“a”

        // List<Employee> employees = employeeMapper.selectList(
        //         new EntityWrapper<Employee>()
        //                 .eq("gender", 0)
        //                 .like("last_name", "老师")
        //                 //.or()             //使用or SQL：...WHERE (gender = ? AND last_name LIKE ? OR email LIKE ?)
        //                 .orNew()            //使用ornew SQL：...WHERE (gender = ? AND last_name LIKE ?) OR (email LIKE ?)
        //                 .like("email", "a")
        // );
        //
        // System.out.println(employees);

    }

    /**
     * 通用删除操作
     */
    @Test
    public void testCommonDelete(){

        //1.根据id删除
        Integer integer = employeeMapper.deleteById(8);
        System.out.println("result = " + integer);

        //2.用map封装条件删除
        // HashMap<String, Object> map = new HashMap<>();
        // map.put("last_name", "MP");
        // map.put("gender", 1);
        // Integer integer = employeeMapper.deleteByMap(map);
        // System.out.println(integer);

        //3.批量删除
        // ArrayList<Integer> list = new ArrayList<>();
        // list.add(5);
        // list.add(6);
        // Integer result = employeeMapper.deleteBatchIds(list);
        // System.out.println("result = " + result);

    }

    /**
     * 通用查询操作
     */
    @Test
    public void testCommonSelect(){
        //1.通过Id查询
        // Employee employee = employeeMapper.selectById(1);
        // System.out.println(employee);

        //2.通过多个列进行查询
        // Employee employee = new Employee();
        // employee.setId(1);
        // employee.setLastName("Tom");
        // Employee employee1 = employeeMapper.selectOne(employee);
        //
        // System.out.println(employee1);

        //3.通过多个id进行查询
        // ArrayList<Integer> list = new ArrayList<>();
        // list.add(1);
        // list.add(2);
        // list.add(3);
        // list.add(4);
        //
        // List<Employee> employees = employeeMapper.selectBatchIds(list);
        //
        // System.out.println(employees);

        //4.通过Map封装查询条件
        // HashMap<String, Object> map = new HashMap<>();
        // map.put("last_name", "Tom");
        // map.put("age",22);
        //
        // List<Employee> employees = employeeMapper.selectByMap(map);
        // System.out.println(employees);

        //5.分页查询
        // List<Employee> employees = employeeMapper.selectPage(new Page<> (3,2), null);
        // System.out.println(employees);

        //查询性别为女，根据age排序，简单分页
        // List<Employee> employees = employeeMapper.selectList(
        //         new EntityWrapper<Employee>()
        //                 .eq("gender", 0)
        //                 .orderBy("age")
        //                 // .orderDesc(Arrays.asList(new String[] {"age"}))
        //                 .last("desc limit 1,3")
        // );
        // System.out.println(employees);

    }


    /**
     * 通用更新操作
     */
    @Test
    public void testUpdate(){
        // 初始化修改对象
        Employee employee = new Employee();
        employee.setId(6);
        employee.setLastName("Mybatis111111");
        employee.setGender(1);
        employee.setAge(223);

        Integer integer = employeeMapper.updateAllColumnById(employee);

        // Integer integer = employeeMapper.updateById(employee);

        System.out.println("result = " + integer);

    }

    @Test
    public void testUpdate02(){
        Employee employee = new Employee();
        employee.setId(7);
        employee.setGender(1);
        Integer integer = employeeMapper.updateById(employee);
        System.out.println("result = " + integer);
    }


    /**
     * 插入的通用操作
     */
    @Test
    public void testCommonInsert(){

        //初始化employee
        Employee employee = new Employee();
        employee.setLastName("MP");
        // employee.setEmail("linzihao@163.com");
        // employee.setAge(22);
        employee.setGender(1);
        employee.setSalary(20000.0);
        //插入数据库
        Integer result = employeeMapper.insert(employee);
        System.out.println("result = " + result);

        Integer id = employee.getId();
        System.out.println("id = " + id);

    }

    @Test
    public void testAllColumn(){

        Employee employee = new Employee();
        employee.setLastName("MP");
        // employee.setEmail("linzihao@163.com");
        // employee.setAge(22);
        employee.setGender(1);
        employee.setSalary(20000.0);

        employeeMapper.insertAllColumn(employee);

        Integer id = employee.getId();
        System.out.println("id = " + id);
        /**
         * insert方法插入时，会根据实体类的属性进行判断，只有非空的属性才会写到sql语句中
         * insertAllColumn方法插入时，不管属性是否非空都会写到sql中
         */
    }


    @Test
    public void testDataSource() throws SQLException {

        DataSource ds = ioc.getBean("dataSource",DataSource.class);

        System.out.println(ds);

        Connection connection = ds.getConnection();

        System.out.println(connection);

    }

}
