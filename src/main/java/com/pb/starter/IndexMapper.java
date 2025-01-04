package com.pb.starter;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IndexMapper {

    @Select("SELECT COUNT(*) FROM test")
    int selectCount();
}
