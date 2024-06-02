package ua.com.kisit2024.course_coffeemachine_2024_372.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.Drinks;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class SaveDrinkToDBFromExcel {


    public List<Drinks> saveListDrinkToDbFromExcel(String path) {

        List<Drinks> drinks = new ArrayList<>();

        InputStream is = null;
        Workbook wb = null;

        try {
            is = new FileInputStream(path);
            try {
                wb = new HSSFWorkbook(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        Sheet sheet = wb.getSheetAt(0);

        Iterator<Row> ri = sheet.iterator();
        while (ri.hasNext()){
            Row row = ri.next();
            String name = row.getCell(0).getStringCellValue();
            String decription = row.getCell(1).getStringCellValue();
            BigDecimal price = new BigDecimal(row.getCell(2).getNumericCellValue());
            String image = row.getCell(3).getStringCellValue();

            drinks.add(new Drinks(name, decription, price, image));
        }

        return drinks;
    }


    public List<Drinks> saveListDrinkToDbFromExcel2(String path) {
        List<Drinks> drinks = new ArrayList<>();

        InputStream is = null;
        XSSFWorkbook wb = null;

        try {
            is = new FileInputStream(path);
            try {
                wb = new XSSFWorkbook(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        XSSFSheet sheet = wb.getSheetAt(0);
        Iterator ri = sheet.rowIterator();

        while (ri.hasNext()){
            XSSFRow row = (XSSFRow) ri.next();

            String name = row.getCell(0).getStringCellValue();
            String description = row.getCell(1).getStringCellValue();
            BigDecimal price = new BigDecimal(row.getCell(2).getNumericCellValue());
            String image = row.getCell(3).getStringCellValue();

            drinks.add(new Drinks(name, description, price, image));
        }


        return drinks;
    }

}
