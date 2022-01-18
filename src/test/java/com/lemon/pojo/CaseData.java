package com.lemon.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2021/12/29 21:29
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class CaseData {
    @Excel(name="用例编号")
    private int caseId;

    @Excel(name="用例标题")
    private String caseTitle;

    @Excel(name="接口入参")
    private String inputParams;

    @Excel(name="响应断言")
    private String assertResponse;

    @Excel(name="数据库断言")
    private String assertDB;

    public CaseData() {
    }

    public CaseData(int caseId, String caseTitle, String inputParams, String assertResponse, String assertDB) {
        this.caseId = caseId;
        this.caseTitle = caseTitle;
        this.inputParams = inputParams;
        this.assertResponse = assertResponse;
        this.assertDB = assertDB;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getInputParams() {
        return inputParams;
    }

    public void setInputParams(String inputParams) {
        this.inputParams = inputParams;
    }

    public String getAssertResponse() {
        return assertResponse;
    }

    public void setAssertResponse(String assertResponse) {
        this.assertResponse = assertResponse;
    }

    public String getAssertDB() {
        return assertDB;
    }

    public void setAssertDB(String assertDB) {
        this.assertDB = assertDB;
    }

    @Override
    public String toString() {
        return "CaseData{" +
                "caseId=" + caseId +
                ", caseTitle='" + caseTitle + '\'' +
                ", inputParams='" + inputParams + '\'' +
                ", assertResponse='" + assertResponse + '\'' +
                ", assertDB='" + assertDB + '\'' +
                '}';
    }
}
