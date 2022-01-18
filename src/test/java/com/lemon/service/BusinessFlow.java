package com.lemon.service;

import com.lemon.apidefinition.ApiCall;
import com.lemon.util.Environment;
import io.restassured.response.Response;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/29 20:34
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class BusinessFlow {

    /**
     * 登录->搜索->商品信息场景组合接口调用
     * @return 商品信息接口的响应数据
     */
    public static Response login_search_info(){
        //场景组合是由多个接口请求组装的
        //1、登录接口
        String loginData="{\"principal\":\"waiwai\",\"credentials\":\"lemon123456\",\"appType\":3,\"loginType\":0}";
        Response loginRes = ApiCall.login(loginData);
        //提取token
        String token = loginRes.jsonPath().get("access_token");
        //保存到环境变量中
        Environment.saveToEnvironment("token",token);
        //2、搜索接口
        String searchData = "prodName=&categoryId=&sort=0&orderBy=0&current=1&isAllProdType=true&st=0&size=12";
        Response searchRes = ApiCall.searchProduct(searchData);
        int prodId = searchRes.jsonPath().get("records[0].prodId");
        //保存到环境变量中
        Environment.saveToEnvironment("prodId",prodId);
        //3、商品信息接口
        Response infoRes = ApiCall.productInfo(prodId);
        return infoRes;
    }

}
