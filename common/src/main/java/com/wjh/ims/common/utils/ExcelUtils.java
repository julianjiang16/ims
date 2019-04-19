package com.wjh.ims.common.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.List;

/**
 * Copyright (C), 2015-2019
 * FileName: ExcelUtils
 * Author:   jcj
 * Date:     2019/3/25 14:31
 * Description: excel 工具类
 */
public class ExcelUtils {

    public static Workbook createExcelWithoutRender(List<List<String>> lists, String[] titles, String name) throws IOException {

        //创建新的工作薄
        Workbook wb = new HSSFWorkbook();
        try {
            // 创建第一个sheet（页），并命名
            Sheet sheet = wb.createSheet(name);
            // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
            for (int i = 0; i < titles.length; i++) {
                sheet.setColumnWidth((short) i, (short) (35.7 * 150));
            }

            // 创建第一行
            Row row = sheet.createRow((short) 0);

            // 创建两种单元格格式
            CellStyle cs = wb.createCellStyle();
            CellStyle cs2 = wb.createCellStyle();

            // 创建两种字体
            Font f = wb.createFont();
            Font f2 = wb.createFont();

            // 创建第一种字体样式（用于列名）
            f.setFontHeightInPoints((short) 10);
            f.setColor(IndexedColors.BLACK.getIndex());
            f.setBoldweight(Font.BOLDWEIGHT_BOLD);

            // 创建第二种字体样式（用于值）
            f2.setFontHeightInPoints((short) 10);
            f2.setColor(IndexedColors.BLACK.getIndex());

            // 设置第一种单元格的样式（用于列名）
            setCellStyle(cs, f);

            // 设置第二种单元格的样式（用于值）
            setCellStyle(cs2, f2);

            //设置列名
            for (int i = 0; i < titles.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(titles[i]);
                cell.setCellStyle(cs);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            }
            if (lists == null || lists.size() == 0) {
                return wb;
            }
            //设置每行每列的值
            for (short i = 0; i < lists.size(); i++) {
                // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
                // 创建一行，在页sheet上
                Row row1 = sheet.createRow((short) i+1);
                for (short j = 0; j < lists.get(i).size(); j++) {
                    // 在row行上创建一个方格
                    Cell cell = row1.createCell(j);
                    cell.setCellValue(lists.get(i).get(j));
                    cell.setCellStyle(cs2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }

    private static void setCellStyle(CellStyle cs, Font f) {
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);
    }
}
