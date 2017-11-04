package mate;
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
	WritableWorkbook wwb = null;
	wwb.Workbook.createWorkbook(filename); //新建Excel文件
	WritableSheet ws =wwb.createSheet("通讯录",0);//Excel中建新表
	ws.mergeCells(0, 0, 7, 1);
	Label header = new Label(0, 0, "通讯录");
	ws.addCell(header);
	
	//建表头
	Label cell = new Label(0, 2, "姓名");
	ws.addCell(cell);
	Label cell = new Label(1, 2, "电话");
	ws.addCell(cell);
	Label cell = new Label(2, 2, "地址");
	ws.addCell(cell);
	Label cell = new Label(3, 2, "QQ");
	ws.addCell(cell);
	Label cell = new Label(4, 2, "wechat");
	ws.addCell(cell);
	Label cell = new Label(5, 2, "个人描述");
	ws.addCell(cell);
	
	//建表内容
	int res;//数据库返回变量
	for (int i = 0; i < res.size(); i++) {
		String name = "          ";//返回名称
		Label cell = new Label(0, i+2, name);
		ws.addCell(cell);
		String phone = "         ";
		Label cell = new Label(1, i+2, phone);
		ws.addCell(cell);
		String address = "       ";
		Label cell = new Label(2, i+2, address);
		ws.addCell(cell);
		String qq = "            ";
		Label cell = new Label(3, i+2, qq);
		ws.addCell(cell);
		String wechat = "        ";
		Label cell = new Label(4, i+2, wechat);
		ws.addCell(cell);
		String inform = "        ";
		Label cell = new Label(5, i+2, inform);
		ws.addCell(cell);
		
	}
	
	wwb.write();
	wwb.close();
	System.out.println("写入成功！\n");
}
