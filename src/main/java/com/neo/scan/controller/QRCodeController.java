package com.neo.scan.controller;

import com.neo.scan.util.QRCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @Description Author neo
 * Date 2020/10/28 12:57
 */
@Controller
public class QRCodeController {

//    @RequestMapping(value = "/createCommonQRCode")
//    public void createCommonQRCode(HttpServletResponse response, String url) throws Exception {
//
//        ServletOutputStream stream = null;
//
//
//        try {
//
//            stream = response.getOutputStream();
//
//            //使用工具类生成二维码
//
//            QRCodeUtil.encode(url, stream);
//
//        } catch (Exception e) {
//
//            e.getStackTrace();
//
//        } finally {
//
//            if (stream != null) {
//
//                stream.flush();
//
//                stream.close();
//
//            }
//
//        }
//
//    }


    @RequestMapping(value = "/createCommonQRCode")
    public void createCommonQRCode(HttpServletResponse response) throws Exception {

        ServletOutputStream stream = null;
        String url = "www.baidu.com";

        try {

            stream = response.getOutputStream();

            //使用工具类生成二维码

            QRCodeUtil.encode(url, stream);

        } catch (Exception e) {

            e.getStackTrace();

        } finally {

            if (stream != null) {

                stream.flush();

                stream.close();

            }

        }

    }


    /**
     * 根据 url 生成 带有logo二维码
     */

    @RequestMapping(value = "/createLogoQRCode")

    public void createLogoQRCode(HttpServletResponse response, String url) throws Exception {

        ServletOutputStream stream = null;

        try {

            stream = response.getOutputStream();

            String logoPath = Thread.currentThread().getContextClassLoader().getResource("").getPath()

                    + "templates" + File.separator + "logo.jpg";

//使用工具类生成二维码

            QRCodeUtil.encode(url, logoPath, stream, true);

        } catch (Exception e) {

            e.getStackTrace();

        } finally {

            if (stream != null) {

                stream.flush();

                stream.close();

            }

        }

    }
}

