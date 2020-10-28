package com.neo.scan.controller;

import com.neo.scan.mapper.UserMapper;
import com.neo.scan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Description Author neo
 * Date 2020/10/28 13:18
 */
@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    public String userInfo() {
        return "userInfo";
    }


    @RequestMapping("/paihao")
    public String paihao(Map<String, Object> map,
                         @RequestParam("username") String username,
                         @RequestParam("userAge") Integer userAge,
                         @RequestParam("userSex") String userSex,
                         @RequestParam("userPhone") String userPhone) {
        User dbUser = userMapper.findUser(username, userAge, userSex);
        if (dbUser != null) {
            Integer number = dbUser.getId();
            map.put("number", number);
            map.put("username", username);
            map.put("userSex", userSex);
            map.put("userPhone", userPhone);
        } else {
            User user = new User();
            user.setUsername(username);
            user.setUserAge(userAge);
            user.setUserSex(userSex);
            user.setUserPhone(userPhone);
            userMapper.insert(user);
            Integer number = user.getId();
            map.put("number", number);
            map.put("username", username);
            map.put("userAge", userAge);
            map.put("userSex", userSex);

            map.put("userPhone", userPhone);
        }

        return "result";
    }
}