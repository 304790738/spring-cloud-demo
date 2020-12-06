package cn.bjtu.edu.common.dao.common;


import java.lang.annotation.*;

/**
 * 标识字段是否是可变的
 * Created by RyanTong on 2020/08/22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface Fixed {
}
