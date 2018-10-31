package com.chy.mapper;

import com.chy.model.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper  extends Mapper<User>{

    String diyXml();

    @Select(" select 'annotation' ")
    String diyAnnotation();
}