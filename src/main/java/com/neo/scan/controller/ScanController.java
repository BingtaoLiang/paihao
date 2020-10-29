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

    // 这里说明一点，想要验证放入session中或者redies中，验证让过即可。
// 和验证码的实现原理是一样，是不过换了一种验证方式。
    @RequestMapping("/createQRcode")
    public void createQRcode(HttpServletResponse response) {
//        String contents = "http://professorsun.cn";
//        String contents = "http://47.95.248.109:8888/";
        String contents = "http://localhost:8888/";
        int width = 500;
        int height = 500;
        int margin = 2;

        try {
            BufferedImage QRcode = ZxingUtils.createQRImage(contents, width, height, margin);

            String logoPath = "src/main/resources/static/images/logo.png";
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
