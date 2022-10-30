package com.atguigu.mp.test;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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

    private ApplicationContext ioc =
            new ClassPathXmlApplicationContext("applicationContext.xml");


    @Test
    public void testGenerator(){

        //1.全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig
                .setActiveRecord(true) // 是否支持AR模式
                .setAuthor("lzh")// 作者
                .setOutputDir("E:\\code02\\mybatis_plus\\mp03\\src\\main\\java") // 生成路径
                .setFileOverride(true) //文件覆盖
                .setIdType(IdType.AUTO) // 主键策略
                .setServiceName("%sService") // 设置生成的service接口是否以首字母是否为I 如：IEmployeeService
                .setBaseResultMap(true)
                .setBaseColumnList(true);

        //2.数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)  //设置数据库类型
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/mp")
                .setUsername("root")
                .setPassword("123456");

        //3.策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true) //全局大写命名
                .setDbColumnUnderline(true) //指定表名 字段名是否使用下划线
                .setNaming(NamingStrategy.underline_to_camel) //数据库表映射到实体类的命名策略
                .setTablePrefix("tbl_")
                .setInclude("tbl_employee"); //设置生成的表

        //4.包名策略
        PackageConfig packageConfig = new PackageConfig();
        packageConfig
                .setParent("com.atguigu.mp")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("beans")
                .setXml("mapper");

        //5.整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);

        //6.执行
        autoGenerator.execute();
    }
}
