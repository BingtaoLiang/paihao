package com.neo.scan.model;

import lombok.Data;

/**
 * @Description Author neo
 * Date 2020/10/28 12:44
 */

@Data
public class User {

    /*主键id*/
    private Integer id;

    /*姓名*/
    private String username;

    /*年龄*/
    private Integer userAge;

    /*性别*/
    private String userSex;

    /*手机号码*/
    private String userPhone;

    /*是否被叫过号*/
    private Integer flag;

    /*病人序号*/
    private Integer xuhao;

}
