package com.ranz.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ranz.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
* @Description: 继承 basemapper 全部方法
* @Author: J.Flying 
* @Date: 2020/10/10 
*/
@Repository // 代表持久层
public interface UserMapper extends BaseMapper<User> {

}
