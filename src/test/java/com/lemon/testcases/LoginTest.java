package com.lemon.testcases;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONObject;
import com.lemon.apidefinition.ApiCall;
import com.lemon.common.BaseTest;
import com.lemon.pojo.CaseData;
import com.lemon.util.ExcelUtil;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static io.restassured.RestAssured.given;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/27 21:37
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class LoginTest extends BaseTest {
    @Test
    public void test_login_success(){
        //1、准备测试数据
        String jsonData="{\"principal\":\"waiwai\",\"credentials\":\"lemon123456\",\"appType\":3,\"loginType\":0}";
        //2、直接调用登录的接口请求
        Response res = ApiCall.login(jsonData);
        //3、断言
        int statusCode = res.getStatusCode();
        Assert.assertEquals(statusCode,200);
        String nickName = res.jsonPath().get("nickName");
        Assert.assertEquals(nickName,"waiwai");
    }

    @Test(dataProvider = "getLoginDatasFromExcel" )
    public void test_login_from_excel(CaseData caseData){
        //1、准备测试数据
        //String jsonData="{\"principal\":\"waiwai\",\"credentials\":\"lemon123456\",\"appType\":3,\"loginType\":0}";
        //2、直接调用登录的接口请求
        Response res = ApiCall.login(caseData.getInputParams());
        //3、断言
        String assertDatas = caseData.getAssertResponse();
        assertResponse(assertDatas,res);
    }

    @DataProvider
    public Object[] getLoginDatasFromExcel(){
        return ExcelUtil.readExcel(0).toArray();
    }
}
