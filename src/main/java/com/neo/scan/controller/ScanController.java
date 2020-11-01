package com.neo.scan.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.neo.scan.util.ZxingUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ScanController {


    //填写信息，获取号码
    @RequestMapping("/createQRcode")
    public void createQRcode(HttpServletResponse response) {
//        String contents = "http://47.95.248.109:8090/";
        String contents = "http://180.76.52.59:8090/";
        int width = 500;
        int height = 500;
        int margin = 2;

        try {
            BufferedImage QRcode = ZxingUtils.createQRImage(contents, width, height, margin);

            String logoPath = "src/main/resources/static/images/logo.jpg";
            int logoSize = 4;
            BufferedImage qRImageWithLogo = ZxingUtils.addQRImagelogo(QRcode, width, height, logoPath, logoSize);

            // 写入返回
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qRImageWithLogo, "jpg", baos);

            byte[] QRJPG = baos.toByteArray();
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");

            ServletOutputStream os = response.getOutputStream();
            os.write(QRJPG); // 自此完成一套，图片读入，写入流，转为字节数组，写入输出流
            os.flush();
            os.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//填写姓名和年龄，查询自己的号码
    @RequestMapping("/createQRcode2")
    public void createQRcode2(HttpServletResponse response) {
        String contents = "http://47.95.248.109:8090/select";
        int width = 500;
        int height = 500;
        int margin = 2;

        try {
            BufferedImage QRcode = ZxingUtils.createQRImage(contents, width, height, margin);

            String logoPath = "src/main/resources/static/images/logo.jpg";
            int logoSize = 4;
            BufferedImage qRImageWithLogo = ZxingUtils.addQRImagelogo(QRcode, width, height, logoPath, logoSize);

            // 写入返回
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qRImageWithLogo, "jpg", baos);

            byte[] QRJPG = baos.toByteArray();
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");

            ServletOutputStream os = response.getOutputStream();
            os.write(QRJPG); // 自此完成一套，图片读入，写入流，转为字节数组，写入输出流
            os.flush();
            os.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
