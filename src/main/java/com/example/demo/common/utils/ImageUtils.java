package com.example.demo.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageUtils {

    public static void toFontImage(InputStream inputStream, OutputStream outputStream, String text) {
        //字体大小
        int size = 11;
        try {
            //获取图片缓存区
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            // 读取图片尺寸
            int w = bufferedImage.getWidth();
            int h = bufferedImage.getHeight();
            int minW = bufferedImage.getMinX();
            int minH = bufferedImage.getMinY();
            BufferedImage outImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            // 画笔
            Graphics2D graphics2D = outImage.createGraphics();
            graphics2D.setColor(null);
            // 填充
            graphics2D.fillRect(0, 0, w, h);
            // 字符循环
            int strIndex = 0;
            // 循环写入
            for (int j = minH; j < h; j = j + size) {
                for (int i = minW; i < w; i = i + size) {
                    int pixel = bufferedImage.getRGB(i, j);
                    graphics2D.setColor(new Color(pixel));
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics2D.setFont(new Font("微软雅黑", Font.PLAIN, size));
                    graphics2D.drawString(String.valueOf(text.charAt(strIndex)), i, j);
                    if (strIndex >= text.length() - 1) {
                        strIndex = 0;
                    } else {
                        strIndex++;
                    }
                }
            }
            graphics2D.dispose();
            ImageIO.write(outImage, "jpg", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
