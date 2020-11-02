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
    public String index() {
        return "index";
    }

    @RequestMapping("/register")
    public String userInfo() {
        return "register";

    }

    @RequestMapping("/paihao")
    public String paihao(Map<String, Object> map,
                         @RequestParam("username") String username,
                         @RequestParam("userAge") Integer userAge,
                         @RequestParam("userSex") String userSex,
                         @RequestParam("userPhone") String userPhone) {
//        User dbUser = userMapper.findUser(username, userAge, userSex,userPhone);
        User dbUser = userMapper.findUser(username, userAge);
        if (dbUser != null) {
            Integer number = dbUser.getId();
            Integer userAge1 = dbUser.getUserAge();
            String userSex1 = dbUser.getUserSex();
            String userPhone1 = dbUser.getUserPhone();
            map.put("number", number);
            map.put("username", username);
            map.put("userAge", userAge1);
            map.put("userSex", userSex1);
            map.put("userPhone", userPhone1);
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



    //查找
    @RequestMapping("/select")
    public String select() {
        return "select";
    }

    @RequestMapping("/selectUser")
    public String selectUser(Map<String, Object> map,
                             @RequestParam("username") String username,
                             @RequestParam("userAge") Integer userAge,
                             @RequestParam("userSex") String userSex) {
        User dbUser = userMapper.select(username, userAge,userSex);
        if (dbUser!=null){
            Integer number = dbUser.getId();
            String userPhone = dbUser.getUserPhone();
            map.put("number", number);
            map.put("username", username);
            map.put("userAge", userAge);
            map.put("userSex", userSex);
            map.put("userPhone", userPhone);
            return "result";
        }else {
            map.put("msg","该用户还未领取号码，请先填写信息领取号码~");
            map.put("text-align","center");
            map.put("color1","ghostwhite");
            map.put("color2","red");

            return "register";
        }

    }

}
