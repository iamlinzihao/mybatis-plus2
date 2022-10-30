package com.atguigu.mp.mapper;

import com.atguigu.mp.beans.Employee;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lzh
 * @since 2022-10-30
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    int deleteAll();

}
