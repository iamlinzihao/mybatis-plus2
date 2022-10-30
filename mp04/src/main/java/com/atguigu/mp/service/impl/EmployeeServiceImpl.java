package com.atguigu.mp.service.impl;

import com.atguigu.mp.beans.Employee;
import com.atguigu.mp.mapper.EmployeeMapper;
import com.atguigu.mp.service.EmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzh
 * @since 2022-10-30
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    /**
     * 可见EmployeeServiceImpl帮我们继承了ServiceImpl对象
     * 1.ServiceImpl中注入了Mapper接口，直接在EmployeeServiceImpl中使用
     * 2.在ServiceImpl中提供了CRUD方法，可以直接使用
     */

}
