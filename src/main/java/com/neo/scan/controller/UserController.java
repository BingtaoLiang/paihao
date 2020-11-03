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
            if (dbUser.getXuhao()==0 ){  //说明昨天挂过号，检查过了,对他重新排号
                User lastUser = userMapper.selectLastUser();        //数据库中找最后一名排号中的患者
                Integer lastUserId = 0;
                if (lastUser == null) { //如果当前数据库中没有在排号的患者，将lastUserId设为0
                    lastUserId = 1;
                } else {
                    lastUserId = lastUser.getXuhao();   //否则，拿到最后一个患者的序号继续排号
                    lastUserId = lastUserId + 1;
                }
                userMapper.repaihao(username, userAge,userSex,lastUserId);
//                Integer xuhao = dbUser.getXuhao();
                String username1 = dbUser.getUsername();
                Integer userAge1 = dbUser.getUserAge();
                String userSex1 = dbUser.getUserSex();
                String userPhone1 = dbUser.getUserPhone();
                map.put("number", lastUserId);
                map.put("username", username1);
                map.put("userAge", userAge1);
                map.put("userSex", userSex1);
                map.put("userPhone", userPhone1);

            }else{
                Integer number = dbUser.getXuhao();
                Integer userAge1 = dbUser.getUserAge();
                String userSex1 = dbUser.getUserSex();
                String userPhone1 = dbUser.getUserPhone();
                map.put("number", number);
                map.put("username", username);
                map.put("userAge", userAge1);
                map.put("userSex", userSex1);
                map.put("userPhone", userPhone1);
            }

        } else {
            User lastUser = userMapper.selectLastUser();        //数据库中找最后一名排号中的患者
            Integer lastUserId = 0;
            if (lastUser == null) { //如果当前数据库中没有在排号的患者，将lastUserId设为0
                lastUserId = 0; //!!!!!!!!!!!!!!
            } else {
                lastUserId = lastUser.getXuhao();   //否则，拿到最后一个患者的序号继续排号
                lastUserId = lastUserId + 1;
            }
            User user = new User();
            user.setUsername(username);
            user.setUserAge(userAge);
            user.setUserSex(userSex);
            user.setUserPhone(userPhone);
            user.setFlag(0);
            user.setXuhao(lastUserId);
            userMapper.insert(user);
            Integer number = user.getXuhao();
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
        User dbUser = userMapper.select(username, userAge, userSex);
        if (dbUser != null) {
            Integer number = dbUser.getXuhao();
            String userPhone = dbUser.getUserPhone();
            map.put("number", number);
            map.put("username", username);
            map.put("userAge", userAge);
            map.put("userSex", userSex);
            map.put("userPhone", userPhone);
            return "result";
        } else {
            map.put("msg", "该用户还未领取号码，请先填写信息领取号码~");
            map.put("text-align", "center");
            map.put("color1", "ghostwhite");
            map.put("color2", "red");

            return "register";
        }

    }

}
