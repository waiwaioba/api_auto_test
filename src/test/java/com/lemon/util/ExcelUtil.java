package com.lemon.util;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.lemon.pojo.CaseData;
import java.io.File;
import java.util.List;

/**
 * @author 歪歪欧巴
 * @Description TODO
 * @date 2022/1/7 20:39
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 */
public class ExcelUtil {
    public static final String EXCEL_FILE_PATH="src\\test\\resources\\caseData.xlsx";

    /**
     * 读取外部Excel文件中的数据
     * @param sheetNum sheet的编号（从0开始）
     * @return 读取到数据
     */
    public static List<CaseData> readExcel(int sheetNum){
        ImportParams importParams = new ImportParams();
        importParams.setStartSheetIndex(sheetNum);
        //读取的文件src\test\resources\caseData.xlsx
        List<CaseData> datas = ExcelImportUtil.importExcel(new File(EXCEL_FILE_PATH),
                CaseData.class,importParams);
        return datas;
    }

}
