package com.valuequo.buckswise.web.rest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.valuequo.buckswise.service.AmfiService;
import com.valuequo.buckswise.service.dto.AmfiDTO;

import org.apache.poi.sl.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AmfiUtilityResource {

    public static final String FILE_PATH = "src/main/resources/NAVII.xlsx";
    Cell cel;

    @Autowired
    private AmfiDTO amfiDTO;

    @Autowired
    private AmfiService amfiService;

    @GetMapping("/json")
    public void textToJson() throws FileNotFoundException {
        try {

            ArrayList<AmfiDTO> al = new ArrayList<AmfiDTO>();
        	Workbook workbook = WorkbookFactory.create(new File(FILE_PATH));
            
            Iterator<org.apache.poi.ss.usermodel.Sheet> sheetIterator = workbook.sheetIterator();
            while(sheetIterator.hasNext())
            {
                org.apache.poi.ss.usermodel.Sheet sheet = sheetIterator.next();
                System.out.println("=>" + sheet.getSheetName());
            }
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            DataFormatter formatter = new DataFormatter();
            
            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                // create the object of DTO class
                AmfiDTO json1 = new AmfiDTO();

                // set value to DTO object
                json1.setSchemeCode(formatter.formatCellValue(row.getCell(0)));
                json1.setISINDivPayoutISINGrowth(formatter.formatCellValue(row.getCell(1)));
                json1.setISINDivReinvestment(formatter.formatCellValue(row.getCell(2)));
                json1.setSchemeName(formatter.formatCellValue(row.getCell(3)));
                json1.setNetAssetValue(formatter.formatCellValue(row.getCell(4)));
                json1.setDate(formatter.formatCellValue(row.getCell(5)));
                al.add(json1);
            }
            amfiService.save(al);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(al);
            System.out.println(jsonInString);
            workbook.close();
       } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @GetMapping("/getDetails")
    public void amfiData() {
        amfiService.getAmfiCode();
    }

}