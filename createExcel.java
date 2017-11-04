package com.example.classmate.view;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
public class createExcel {
	public void CreateExcel() {
		try {
			File filename = new File("Classmates.xls");
			WritableWorkbook wwb = Workbook.createWorkbook(filename);
			WritableSheet ws = wwb.createSheet("第一页", 0);
			ws.mergeCells(0, 0, 7, 1);
			Label header = new Label(0, 0, "通讯录");
			ws.addCell(header);
			Label cell = new Label(0, 2, "姓名");
			ws.addCell(cell);
			Label cell1 = new Label(1, 2, "家庭地址");
			ws.addCell(cell1);
			Label cell2 = new Label(2, 2, "电话ַ");
			ws.addCell(cell2);
			Label cell3 = new Label(3, 2, "微信");
			ws.addCell(cell3);
			Label cell4 = new Label(4, 2, "QQ");
			ws.addCell(cell4);
			Label cell5 = new Label(5, 2, "个性语言");
			ws.addCell(cell5);
			wwb.write();
			wwb.close();
		} catch (Exception e) {

		}


		//��������
		/*int res;//���ݿⷵ�ر���
		for (int i = 0; i < res.size(); i++) {
			String name = "          ";//��������
			Label cell = new Label(0, i + 2, name);
			ws.addCell(cell);
			String phone = "         ";
			Label cell = new Label(1, i + 2, phone);
			ws.addCell(cell);
			String address = "       ";
			Label cell = new Label(2, i + 2, address);
			ws.addCell(cell);
			String qq = "            ";
			Label cell = new Label(3, i + 2, qq);
			ws.addCell(cell);
			String wechat = "        ";
			Label cell = new Label(4, i + 2, wechat);
			ws.addCell(cell);
			String inform = "        ";
			Label cell = new Label(5, i + 2, inform);
			ws.addCell(cell);*/

		}
	}


