package com.neo.scan.mapper;

import com.neo.scan.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description Author neo
 * Date 2020/10/28 13:58
 */
@Mapper
public interface UserMapper {

    User findUser(String username,Integer userAge, String userSex);

    void insert(User user);

    User select(String username, Integer userAge,String userSex);
}
