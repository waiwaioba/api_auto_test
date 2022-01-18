package com.lemon.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/31 20:39
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class Environment {
    //设计一个Map结构 类似于Postman的环境变量区域
    public static Map<String,Object> envMap = new HashMap<String, Object>();

    /**
     * 向环境变量中存储对应的键值对
     * @param varName
     * @param varValue
     */
    public static void saveToEnvironment(String varName,Object varValue){
        Environment.envMap.put(varName,varValue);
    }

    /**
     * 从环境变量区域中取得对应的值
     * @param varName
     * @return
     */
    public static Object getEnvironment(String varName){
        return Environment.envMap.get(varName);
    }

    /**
     * 字符串类型数据参数化替换
     * @param inputParam 接口入参
     * @return 参数化替换之后的结果
     */
    public static String replaceParams(String inputParam){
        //{"basketId":0,"count":1,"prodId":"#prodId#","shopId":1,"skuId":#skuId#}
        //{"basketId":0,"count":1,"prodId":"101","shopId":1,"skuId":202}
        //识别#XXX# 正则表达式？？
        //prodId=101
        //skuId =202
        String regex ="#(.+?)#";
        //编译得到Pattern模式对象
        Pattern pattern = Pattern.compile(regex);
        //通过pattern的matcher匹配，得到匹配器-->搜索
        Matcher matcher = pattern.matcher(inputParam);
        //循环在原始的字符串中来找符合正则表达式对应的字符串
        while (matcher.find()){
            //matcher.group(0) 表示整个匹配到的字符串
            String wholeStr=matcher.group(0);
            //matcher.group(1) 分组的第一个结果，#XX#里面的XX
            String subStr = matcher.group(1);
            //替换#XX#
            inputParam = inputParam.replace(wholeStr,Environment.getEnvironment(subStr)+"");
        }
        return inputParam;
    }

    /**
     * 字符串类型数据参数化替换
     * @param headersMap 请求头
     * @return 参数化替换之后的结果
     */
    public static Map<String,Object> replaceParams(Map<String,Object> headersMap){
        //把Map转成字符串
        String datas = JSONObject.toJSONString(headersMap);
        datas = replaceParams(datas);
        //把字符串再转成Map
        return JSONObject.parseObject(datas);
    }
}
