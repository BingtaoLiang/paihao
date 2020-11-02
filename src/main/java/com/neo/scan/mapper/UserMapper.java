package com.neo.scan.mapper;

import com.neo.scan.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description Author neo
 * Date 2020/10/28 13:58
 */
@Mapper
public interface UserMapper {

    User findUser(String username,Integer userAge, String userSex,String userPhone);

    void insert(User user);

    User select(String username, Integer userAge);

}
