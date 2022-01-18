package com.test.day02;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/27 20:07
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class ResponseTest {
    @Test
    public void test01(){
        String jsonData="{\"principal\":\"waiwai\",\"credentials\":\"lemon123456\",\"appType\":3,\"loginType\":0}";
        //post请求：json类型传参
        Response res=
        given().header("Content-Type","application/json").
                body(jsonData).
        when().
                post("http://mall.lemonban.com:8107/login").
        then().
                log().all().
                extract().response();
        //响应状态码
        //System.out.println(res.getStatusCode());
        //接口的响应时间(ms)
        //System.out.println(res.time());
        //获取接口的响应头信息
        //所有的
        //System.out.println(res.getHeaders());
        //System.out.println(res.getHeader("Content-Type"));
        //System.out.println(res.getHeader("Set-Cookie"));

        //解析响应体数据
        //获取【昵称】
        /*System.out.println(res.jsonPath().get("nickName"));
        System.out.println(res.jsonPath().get("expires_in"));*/
    }

    @Test
    public void test02(){
        Response res=
                given().
                when().
                        get("http://mall.lemonban.com:8107/prod/prodListByTagId?tagId=2&size=12").
                then().
                        log().all().
                        extract().response();
        //下标为0表示json数组的第一个元素,1..2...以此类推
        //下标为-1表示json数组的最后一个元素，-2表示倒数第二个，以此类推
        System.out.println(res.jsonPath().get("records[-2].price").toString());
    }

    @Test
    public void test03(){
        Response res=
                given().
                when().
                        get("http://httpbin.org/xml").
                then().
                        log().all().
                        extract().response();
        System.out.println(res.xmlPath().get("slideshow.slide[0].title").toString());
        System.out.println(res.xmlPath().get("slideshow.slide[1].item[0].em").toString());
    }

    @Test
    public void test04(){
        Response res=
                given().
                when().
                        get("https://www.baidu.com/").
                then().
                        log().all().
                        extract().response();
       // System.out.println(res.htmlPath().get("html.head.title"));
        //获取属性 @选择对应的属性
        //System.out.println(res.htmlPath().get("html.head.meta[2].@content"));
        //System.out.println(res.htmlPath().get("html.head.meta[1].@http-equiv"));
    }

    @Test
    public void test05(){
        String jsonData="{\"principal\":\"waiwai\",\"credentials\":\"lemon123456\",\"appType\":3,\"loginType\":0}";
        //post请求：json类型传参
        Response res=
                given().header("Content-Type","application/json").
                        body(jsonData).
                when().
                        post("http://mall.lemonban.com:8107/login").
                then().
                        log().all().
                        extract().response();
        int statusCode = res.getStatusCode();
        Assert.assertEquals(statusCode,2000);
        System.out.println("hello assertion");
        String nickName = res.jsonPath().get("nickName");
        Assert.assertEquals(nickName,"waiwai");

    }

    @Test
    public void test06(){
        String jsonData="{\"principal\":\"waiwai\",\"credentials\":\"lemon123456\",\"appType\":3,\"loginType\":0}";
        //post请求：json类型传参
        Response res=
                given().header("Content-Type","application/json").
                        body(jsonData).
                        when().
                        post("http://mall.lemonban.com:8107/login").
                        then().
                        log().all().
                        extract().response();
        //购物车请求
        Response res2=
                given().header("Content-Type","application/json").
                        body(jsonData).
                        when().
                        post("http://mall.lemonban.com:8107/login").
                        then().
                        log().all().
                        extract().response();
        int statusCode = res.getStatusCode();
        Assert.assertEquals(statusCode,2000);
        System.out.println("hello assertion");
        String nickName = res.jsonPath().get("nickName");
        Assert.assertEquals(nickName,"waiwai");

    }



}
