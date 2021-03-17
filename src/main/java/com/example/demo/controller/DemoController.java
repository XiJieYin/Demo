package com.example.demo.controller;

import com.example.demo.common.utils.CharUtils;
import com.example.demo.common.utils.ImageUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("demo")
public class DemoController {

    @RequestMapping("getInfo")
    public String getInfo(){
        return "hello~";
    }

    @RequestMapping("gen")
    public void gen(@RequestParam String text, MultipartFile file, HttpServletResponse response){
        response.setContentType("image/jpeg");
        try {
            ImageUtils.toFontImage(file.getInputStream(),response.getOutputStream(),text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("genChar")
    public void genChar(MultipartFile file, HttpServletResponse response){
        response.setContentType("text");
        try {
            CharUtils.createAsciiPic(file.getInputStream(),response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
