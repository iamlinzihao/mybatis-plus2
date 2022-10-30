package com.atguigu.mp.mapper;

import com.atguigu.mp.beans.Employee;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * Mapper接口
 * 基于Mybatis：在Mapper接口中编写CRUD方法，提供相对应的sql语句映射文件
 *
 * 基于MP：继承BaseMapper接口即可
 *          BaseMapper<T> 泛型T为当前Mapper所操作的实体类类型
 * @author linzihao
 * @create 2022-10-29-15:40
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 有关获取自增的主键值
     *     在mybatis中：
     *              Integer insertemployee(Employee employee);
     *              <insert useGenerateKey="true" keyProperty="id">SQL..</insert>
     *
     *     在MP中：自动设置回实体类中
     */
}
