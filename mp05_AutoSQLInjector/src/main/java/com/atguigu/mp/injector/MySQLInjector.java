package com.atguigu.mp.injector;

/**
 * @author linzihao
 * @create 2022-10-30-15:25
 */

import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.mapper.AutoSqlInjector;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

/**
 * 自定义全局操作
 *
 * 步骤：
 * 1) 在 Mapper 接口中定义相关的 CRUD 方法
 * 2) 扩展 AutoSqlInjector inject 方法，实现 Mapper 接口中方法要注入的 SQL
 * 3) 在 MP 全局策略中，配置 自定义注入器
 */
public class MySQLInjector extends AutoSqlInjector {

    /**
     * 扩展inject方法，完成自定义全局操作
     */
    @Override
    public void inject(Configuration configuration, MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table) {
        //将EmployeeMapper中自定义的deleteall方法，处理成mappedstatement对象，加入到configuration中

        //1.注入的SQL语句
        String sql = "delete from " + table.getTableName();

        //2.注入的方法名 一定要与EmployeeMapper中的方法一致
        String method = "deleteAll";

        //3.构造SqlSource对象
        SqlSource sqlSource = languageDriver.createSqlSource(configuration,sql,modelClass);

        //4.构造一个删除的MappedStatement
        this.addDeleteMappedStatement(mapperClass,method,sqlSource);

    }
}
