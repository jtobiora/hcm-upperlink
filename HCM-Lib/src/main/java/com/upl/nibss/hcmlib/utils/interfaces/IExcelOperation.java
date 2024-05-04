package com.upl.nibss.hcmlib.utils.interfaces;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * Created by stanlee on 01/03/2018.
 */
public interface IExcelOperation {

    void writeDropDown(Workbook workbook, Sheet sheet) throws Exception;

    <T> List<T> fetchFromExcelSheet(Workbook workbook, int totalRowCount) throws Exception;

    void createAllCustomCell(Workbook workbook);

    void setTitle(Row row);

    <T> List<T> saveBulk(List<T> bulkObject) throws Exception;

    <T> List<T> generateAndSaveObjects(Workbook workbook, int totalRowCount) throws Exception;

}
