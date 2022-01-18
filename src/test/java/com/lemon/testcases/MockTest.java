package com.lemon.testcases;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2022/1/14 21:43
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class MockTest {
    @Test
    public void test_moco(){
        String inputParams = "{\n" +
                "    \"phone\":\"13323234545\",\n" +
                "    \"pwd\":\"123456\"\n" +
                "}";
        Map<String,Object> map = new HashMap<>();
        map.put("Content-Type","application/json");
        map.put("X-Lemonban-Media-Type","lemonban.v1");
        given().
                log().all().
                headers(map).
                body(inputParams).
        when().
                post("http://127.0.0.1:9999/pay").
        then().
                log().all();
    }
}
