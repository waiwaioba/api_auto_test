package com.lemon.testcases;

import com.lemon.apidefinition.ApiCall;
import com.lemon.encryption.MD5Util;
import com.lemon.encryption.RSAManager;
import com.lemon.util.Environment;
import io.restassured.response.Response;
import org.testng.annotations.Test;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2022/1/14 20:15
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class EncryptTest {
    @Test
    public void test_md5(){
        String data="123456";
        //找开发沟通加密方式，找他们去要加密jar
        String encryptData = MD5Util.stringMD5(data);
        ApiCall.erpLogin("loginame=admin&password="+encryptData);
    }

    @Test
    public void test_rsa(){
        //前程贷项目没有在登录接口里限制加密处理，而是其他的接口（充值）
        String data01 = "{\n" +
                "    \"mobile_phone\": \"13329334510\",\n" +
                "    \"pwd\": \"12345678\"\n" +
                "}";
        Response res = ApiCall.futureloanLogin(data01);
        String token = res.jsonPath().get("data.token_info.token");
        int memberId=res.jsonPath().get("data.id");
        //充值接口请求
        String sign = getSign(token);
        long timestamp =  System.currentTimeMillis()/1000;
        Environment.saveToEnvironment("member_id",memberId);
        Environment.saveToEnvironment("timestamp",timestamp);
        Environment.saveToEnvironment("sign",sign);
        String data02 = "{\n" +
                "    \"member_id\": #member_id#,\n" +
                "    \"amount\": 10000.0,\n" +
                "    \"timestamp\": #timestamp#,\n" +
                "    \"sign\": \"#sign#\"\n" +
                "}";
        ApiCall.futureloanRecharge(data02,token);
    }

    public static String getSign(String token) {
        //获取秒级的时间戳
        long timestamp = System.currentTimeMillis()/1000;
        //String token = "eyJhbGciOiJIUzUxMiJ9.eyJtZW1iZXJfaWQiOjgxMDUyOCwiZXhwIjoxNjQyMTY0NTk0fQ.rzHU6OAvgczz0cWCPyYOsavI8Yw5OKBlVFTJMJUlZxryWMT6fpB0n3mG3tzklMx9t_N_leH8rgH8e5k2rEBCBg";
        //取token的前面50位
        String subStr = token.substring(0,50);
        //拼接时间戳
        String newStr = subStr+timestamp;
        //使用导入的加密jar来进行rsa加密
        String sign = null;
        try {
            sign = RSAManager.encryptWithBase64(newStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }
}
