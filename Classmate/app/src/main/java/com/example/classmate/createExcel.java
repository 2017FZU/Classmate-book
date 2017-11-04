package com.example.classmate;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.example.classmate.item.Person;
import java.io.File;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CreateExcel {

    private Context context;
    private File file;
    private static final String APP_FOLDER = "Classmate";
    private static final String FILENAME = "Classmates.xls";
    public static final String LOCAL_EXCEL = APP_FOLDER + "/" + FILENAME;

    public CreateExcel(Context context) {
        this.context = context;
    }

    public void createExcel(List<Person> persons) {
        try {
            file = saveExcelToLocal();

            // file = new File("Classmates.xls");
            WritableWorkbook wwb = Workbook.createWorkbook(file);
            WritableSheet ws = wwb.createSheet("第一页", 0);

            //创建字体，参数1：字体样式，参数2：字号，参数3：粗体
            WritableFont font = new WritableFont(WritableFont.createFont("楷体"), 14, WritableFont.BOLD);
            WritableCellFormat format = new WritableCellFormat(font);
            //设置对齐方式为水平居中
            format.setAlignment(Alignment.CENTRE);
            //设置对齐方式为垂直居中
            format.setVerticalAlignment(VerticalAlignment.CENTRE);

            ws.mergeCells(0, 0, 6, 1);
            Label header = new Label(0, 0, "通讯录", format);
            ws.addCell(header);
            Label cell = new Label(0, 2, "姓名");
            ws.addCell(cell);
            Label cell2 = new Label(1, 2, "家庭地址");
            ws.addCell(cell2);
            Label cell3 = new Label(2, 2, "电话");
            ws.addCell(cell3);
            Label cell4 = new Label(3, 2, "微信");
            ws.addCell(cell4);
            Label cell5 = new Label(4, 2, "邮箱");
            ws.addCell(cell5);
            Label cell6 = new Label(5, 2, "QQ");
            ws.addCell(cell6);
            Label cell7 = new Label(6, 2, "个性语言");
            ws.addCell(cell7);

            int size = persons.size();
            for (int i = 0; i < size; i++) {
                Person person = persons.get(i);
                int row = 3 + i;
                ws.addCell(new Label(0, row, person.name));
                ws.addCell(new Label(1, row, person.address));
                ws.addCell(new Label(2, row, person.phone));
                ws.addCell(new Label(3, row, person.wechat));
                ws.addCell(new Label(4, row, person.email));
                ws.addCell(new Label(5, row, person.qq));
                ws.addCell(new Label(6, row, person.message));
            }

            wwb.write();
            wwb.close();


//            Toast.makeText(context, "您的文件已保存至" + APP_FOLDER + "/" + FILENAME, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {

        }

        /*int res;
		for (int i = 0; i < res.size(); i++) {
			String name = "          ";
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

    public void updatExcel(List<Person> persons) {
        try {
            //获取excel文件
            Workbook workbook = Workbook.getWorkbook(file);
            //打开文件的副本，并且制定数据写回到原文件
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(file, workbook);

            //修改工作表的数据
            WritableSheet sheet = writableWorkbook.getSheet(0);
//            sheet.addCell(new Label(0, 0, "覆盖单元格内容"));
//            for (int i = 0; i < 7; i++) {
//                sheet.addCell(new Label(i, row, "覆盖单元格内容"));
//            }
            int size = persons.size();
            for (int i = 0; i < size; i++) {
                Person person = persons.get(i);
                int row = 3 + i;
                sheet.addCell(new Label(0, row, person.name));
                sheet.addCell(new Label(1, row, person.address));
                sheet.addCell(new Label(2, row, person.phone));
                sheet.addCell(new Label(3, row, person.wechat));
                sheet.addCell(new Label(4, row, person.email));
                sheet.addCell(new Label(5, row, person.qq));
                sheet.addCell(new Label(6, row, person.message));
            }

            //添加一张新的工作表
//            WritableSheet sheet1 = writableWorkbook.createSheet("第二张工作表", 1);
//            sheet1.addCell(new Label(0, 0, "第二页的数据"));

            //关闭
            writableWorkbook.close();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public File saveExcelToLocal() {
        File appDir;
        String rootPath = "";
        StringBuilder filePath = new StringBuilder("");
        if (haveSDCard()) {
            rootPath = Environment.getExternalStorageDirectory().toString();
            appDir = new File(Environment.getExternalStorageDirectory(), APP_FOLDER);
        } else {
            rootPath = Environment.getDataDirectory().toString();
            appDir = new File(Environment.getDataDirectory(), APP_FOLDER);
        }

        filePath.append(rootPath).append(File.separator).append(APP_FOLDER).append(File.separator);

        if (!appDir.exists()) {
            appDir.mkdirs();
        }
//        System.out.println("=========== " + appDir);
//        File file = new File(appDir, FILENAME);

        return new File(appDir, FILENAME);

//        System.out.println("=========== " + file);
        // Toast.makeText(context, "您的文件已保存至" + APP_FOLDER + "/" + file.getName(), Toast.LENGTH_SHORT).show();

    }

    private Boolean haveSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}


