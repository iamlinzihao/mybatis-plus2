package com.atguigu.mp.metaObjectHandler;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 自定义公共字段填充处理器
 * @author linzihao
 * @create 2022-10-30-17:04
 */
public class MyMetaObjectHandler extends MetaObjectHandler {

    //插入操作 自动填充
    @Override
    public void insertFill(MetaObject metaObject) {

        //获取到需要被填充的字段
        Object name = getFieldValByName("name", metaObject);
        if (name == null){
            System.out.println("************** 插入操作 满足填充条件 ***************");
            setFieldValByName("name", "linzihao", metaObject);
        }

    }

    //更新操作 自动填充
    @Override
    public void updateFill(MetaObject metaObject) {

        //获取到需要被填充的字段
        Object name = getFieldValByName("name", metaObject);
        if (name == null){
            System.out.println("************** 更新操作 满足填充条件 ***************");
            setFieldValByName("name", "lzh", metaObject);
        }

    }
}
