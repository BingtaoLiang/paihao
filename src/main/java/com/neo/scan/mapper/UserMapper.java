package com.neo.scan.mapper;

import com.neo.scan.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description Author neo
 * Date 2020/10/28 13:58
 */
@Mapper
public interface UserMapper {

//    User findUser(String username,Integer userAge, String userSex,String userPhone);
    User findUser(String username,Integer userAge);

    void insert(User user);

    User select(String username, Integer userAge,String userSex);

    User findUserById(Integer id);

    List<User> findAll();

    Integer countAll();

    User selectLastUser();

    void updatePreUser(Integer preId);

    User selectFirstUser();

    void restart();

    void repaihao(String username, Integer userAge, String userSex,Integer lastUserId);

//    List<User> selectBySomething(UserQueryDTO userQueryDTO);
}
