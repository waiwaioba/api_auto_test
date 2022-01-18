package com.test.day01;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/24 21:22
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class RestAssuredTest {
    public static void main(String[] args) {
        //1、简单get请求
        /*given().
        when().
                get("http://mall.lemonban.com:8107/prod/prodListByTagId?tagId=2&size=12").
        then().
                log().body();*/
        //2、简单的post请求
       /* String jsonData="{\"principal\":\"waiwai\",\"credentials\":\"lemon123456\",\"appType\":3,\"loginType\":0}";
        given().
                log().all().
                header("Content-Type","application/json").
                body(jsonData).
        when().
                post("http://mall.lemonban.com:8107/login").
        then().
                log().all();*/
    }

    @Test
    public void getDemo01(){
        //get请求参数直接拼接到URL地址的后面
        given().
        when().
                get("http://mall.lemonban.com:8107/prod/prodListByTagId?tagId=2&size=12").
        then().
                log().body();
    }

    @Test
    public void getDemo02(){
        //get请求参数写到given()后面
        given().
                queryParam("tagId",2).
                queryParam("size",12).
        when().
                get("http://mall.lemonban.com:8107/prod/prodListByTagId").
        then().
                log().body();
    }

    @Test
    public void postDemo01(){
        //post请求：form表单传参
        given().
                header("Content-Type","application/x-www-form-urlencoded").
                body("loginame=admin&password=e10adc3949ba59abbe56e057f20f883e").
        when().
                post("http://erp.lemfix.com/user/login").
        then().
                log().body();
    }

    @Test
    public void postDemo02(){
        String jsonData="{\"principal\":\"waiwai\",\"credentials\":\"lemon123456\",\"appType\":3,\"loginType\":0}";
        //post请求：json类型传参
        given().
                header("Content-Type","application/json").
                body(jsonData).
        when().
                post("http://mall.lemonban.com:8107/login").
        then().
                log().body();
    }

   @Test
    public void postDemo03(){
        String xmlData= "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<suite>\n" +
                "    <class>测试xml</class>\n" +
                "</suite>";
        //post请求：json类型传参
        given().
                header("Content-Type","application/xml").
                body(xmlData).
        when().
                post("http://www.httpbin.org/post").
        then().
                log().body();
    }

    @Test
    public void postDemo04(){
        //post请求：文件上传
        given().
                log().all().
                header("Authorization","bearer6ffab321-288c-4a07-a877-d6b51a959193").
                multiPart(new File("D:\\test.png")).
        when().
                post("http://mall.lemonban.com:8108/admin/file/upload/img").
        then().
                log().body();
    }
}
