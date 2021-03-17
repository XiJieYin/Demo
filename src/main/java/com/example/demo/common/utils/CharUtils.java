package com.example.demo.common.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CharUtils {

    public static void createAsciiPic(final InputStream inputStream, OutputStream outputStream) {
        final String base = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ";// 字符串由复杂到简单
        try {
            final BufferedImage image = ImageIO.read(inputStream);
            for (int y = 0; y < image.getHeight(); y += 2) {
                for (int x = 0; x < image.getWidth(); x++) {
                    final int pixel = image.getRGB(x, y);
                    final int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
                    final float gray = 0.299f * r + 0.578f * g + 0.114f * b;
                    final int index = Math.round(gray * (base.length() + 1) / 255);
//                    System.out.print(index >= base.length() ? " " : String.valueOf(base.charAt(index)));
                    if(index >= base.length()){
                        outputStream.write(" ".getBytes());
                    }else{
                        outputStream.write(base.charAt(index));
                    }
                }
//                System.out.println();
                outputStream.write("\n".getBytes());
            }
            outputStream.flush();
            outputStream.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
