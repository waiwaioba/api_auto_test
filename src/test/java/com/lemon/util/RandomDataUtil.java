package com.lemon.util;

import com.github.javafaker.Faker;

import java.util.Locale;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2022/1/10 20:13
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class RandomDataUtil {
    public static void main(String[] args) {
        //使用Faker库 生成随机的数据
        Faker faker = new Faker();
        System.out.println(faker.name().lastName());
        /*System.out.println(faker.phoneNumber().cellPhone());
        System.out.println(faker.address().city());
        System.out.println(faker.address().fullAddress());
        System.out.println(faker.name().fullName());*/

    }

    /**
     * 获取未被注册的手机号码
     * @return 未注册的手机号码
     */
    public static String getUnregisterPhone(){
        //1、随机生成手机号码
        Faker faker = new Faker(Locale.CHINA);
        String randomPhone = faker.phoneNumber().cellPhone();
        //2、查库-》去判别数据库中有没有这个手机号码
        String sql = "SELECT COUNT(*) FROM tz_user WHERE user_mobile='"+randomPhone+"';";
        //3、循环遍历
        while(true){
            long count = (long)JDBCUtils.querySingleData(sql);
            if(count == 0){
                //没有被注册 -- 符合要求
                break;
            }else if(count == 1){
                //已经被注册 -- 再一次生成一个新的手机号码
                randomPhone = faker.phoneNumber().cellPhone();
                sql = "SELECT COUNT(*) FROM tz_user WHERE user_mobile='"+randomPhone+"';";
            }
        }
        return randomPhone;
    }

    /**
     * 生成未被注册的用户名
     * @return 用户名
     */
    public static String getUnregisterName(){
        //1、随机生成手机号码
        Faker faker = new Faker();
        String randomName = faker.name().lastName();
        //2、查库-》去判别数据库中有没有这个手机号码
        String sql = "SELECT COUNT(*) FROM tz_user WHERE user_name='"+randomName+"';";
        //3、循环遍历
        while(true){
            long count = (long)JDBCUtils.querySingleData(sql);
            if(count == 0){
                //没有被注册 -- 符合要求
                break;
            }else if(count == 1){
                //已经被注册 -- 再一次生成一个新的手机号码
                randomName = faker.name().lastName();
                sql = "SELECT COUNT(*) FROM tz_user WHERE user_name='"+randomName+"';";
            }
        }
        return randomName;
    }
}
