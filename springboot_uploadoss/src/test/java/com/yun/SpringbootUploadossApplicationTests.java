package com.yun;

import com.yun.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootUploadossApplicationTests {


/*
    @Autowired
    private Girl girl;

    @Resource
    private Boy boy;
*/

   /* @Resource
    private ListFileCmd listFileCmd;*/
  /*  @Test
    void contextLoads() {
        System.out.println("女朋友是："+girl.toString());
    }
    @Test
    public void show(){
        System.out.println("明星是："+boy.toString());
    }*/

   /* @Test
    public void show2(){
        String cmd = listFileCmd.showListCmd();
        System.out.println(cmd);
    }*/

    @Autowired
    StudentService studentService;
    @Test
    public void test1(){
        studentService.sing();
        System.out.printf(studentService.getStudent().eat());
    }

}
