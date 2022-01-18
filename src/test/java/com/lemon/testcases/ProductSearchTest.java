package com.lemon.testcases;

import com.lemon.apidefinition.ApiCall;
import com.lemon.common.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/27 21:50
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class ProductSearchTest extends BaseTest {
    @Test
    public void test_searchproduct_success(){
        //1、准备测试数据
        String data="冰箱";
        //2、发起接口请求
        String inputParam = "prodName="+data+"&categoryId=&sort=0&orderBy=0&current=1&isAllProdType=true&st=0&size=12";
        Response res = ApiCall.searchProduct(inputParam);
        //3、断言
        //3-1、响应状态码
        int status =res.getStatusCode();
        Assert.assertEquals(status,200);
        //3-2、根据prodName是否包含“冰箱”
        String prodName = res.jsonPath().get("records[0].prodName");
        Assert.assertTrue(prodName.contains(data));
    }
}
