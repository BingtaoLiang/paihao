package com.neo.scan.controller;

import com.neo.scan.mapper.UserMapper;
import com.neo.scan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
                          Map<String, Object> map) {

        User userById = userMapper.findUserById(id);
        map.put("user", userById);
        map.put("id", userById.getId());
        return "jiaohao";
    }


    @RequestMapping("/list")
    public String list(Map<String, Object> map) {
        List<User> allUsers = userMapper.findAll();
        map.put("users", allUsers);
        return "list";
    }

//    @RequestMapping("/list")
//    public String list( Map<String,Object> map,
//                        @RequestParam(name = "page",defaultValue = "1") Integer page,
//                        @RequestParam(name = "size",defaultValue = "6") Integer size){
////        List<User> allUsers = userMapper.findAll();
////        map.put("users",allUsers);
//        PaginationDTO list = userService.list(page, size);
//        map.put("list",list);
//
//        return "list";
//    }
}
