package com.chy.mapper;

import com.chy.model.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper  extends Mapper<User>{

    List<String> diyXml();

    @Select(" select 'annotation' ")
    String diyAnnotation();
}