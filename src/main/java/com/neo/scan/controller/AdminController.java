package com.neo.scan.controller;

import com.neo.scan.mapper.UserMapper;
import com.neo.scan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Map;

/**
 * @Description Author neo
 * Date 2020/11/3 7:46
 */

@Controller
public class AdminController {
    @Autowired
    private UserMapper userMapper;



    @RequestMapping("/admin")
    public String admin() {
        return "adminMain";
    }


    @RequestMapping("/jiaohao")
    public String jiaohao(@RequestParam(name = "id", required = false, defaultValue = "1") Integer id,
                          @RequestParam(name = "preId",required = false,defaultValue = "0")Integer preId,
                          Map<String, Object> map) {

        if (preId!=0){
            userMapper.updatePreUser(preId);
        }

        //找到当前待叫号的第一个人
        User firstUser = userMapper.selectFirstUser();
//        User userById = userMapper.findUserById(id);
        if (firstUser!=null){
            map.put("user", firstUser);
            map.put("id", firstUser.getXuhao());
            return "jiaohao";
        }else{
            map.put("msg","当前没有正在挂号等待的病人");
            return "jiaohao";
        }

    }


    @RequestMapping("/guanli")
    public String guanli(){
        return "guanli";
    }

    @RequestMapping("/list")
    public String list(Map<String, Object> map) {
        List<User> allUsers = userMapper.findAll();
        if (allUsers.size()!=0){
            map.put("users", allUsers);
            return "list";
        }else {
            map.put("msg","当前没有正在挂号等待的病人");
            return "list";
        }

    }


    //重新开始排号
    @RequestMapping("/restart")
    public String restart(Map<String, Object> map) {
        userMapper.restart();   //重新开始排号
        map.put("msg","号码以重置，可以开始排号");
        return "guanli";
    }
}
