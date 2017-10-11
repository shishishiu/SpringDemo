package com.example.demo.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.example.demo.entity.MemberEntity;

public class ExcelBuilder extends AbstractXlsxView  {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

	       String fileName = new String("POI.xlsx".getBytes("MS932"), "ISO-8859-1");
	       
	       @SuppressWarnings("unchecked")
	       List<MemberEntity> list = (List<MemberEntity>) model.get("list");
	       
	       
	       response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	       
	        Sheet sheet = workbook.createSheet("Spring");

	        Row header = sheet.createRow(0);
	        header.createCell(0).setCellValue("ID");
	        header.createCell(1).setCellValue("Name");

	        // Create data cells
	        int rowCount = 1;
	        for (MemberEntity member : list){
	            Row courseRow = sheet.createRow(rowCount++);
	            courseRow.createCell(0).setCellValue(member.getLoginId());
	            courseRow.createCell(1).setCellValue(member.getUsername());
	        }

		
	}

}
