package com.ranz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: mybatis-plus
 * @description:
 * @author: J.Flying
 * @create: 2020-10-10 14:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

//    id BIGINT(20) NOT NULL COMMENT '主键ID',
//    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
//    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
//    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
//    PRIMARY KEY (id);


    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;
    private Integer age;
    private String email;

    @Version
    private Integer version;

}
