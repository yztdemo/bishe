package com.bishe.service;

import com.bishe.entity.StudentCourse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class XSSService {

    @Autowired
    private StudentCourseService studentCourseService;

    @Value("${classes.path}")
    private String path;

    public Boolean printxl(String clasees) {
        Boolean msg=true;
        OutputStream out = null;
        List<StudentCourse> courses = this.studentCourseService.selectCrouseNameLesson(clasees);
        String [] week={"星期一","星期二","星期三","星期四","星期五"};
        String [] time={"上午第一节8.00-9.40",
                "上午第二节10.00-11.45",
                "下午第一节14.00-15.40",
                "下午第二节16.00-17.45",
                "晚上第一节19.00-20.45",};
        try {

            String filename=clasees+".xlsx";
            String filepath=this.path+File.separator+filename;
            File file = new File(filepath);
            file.createNewFile();
            Workbook workbook =new XSSFWorkbook();
            Sheet sheet = (Sheet)workbook.createSheet("课程表");
            int RowRule= 0;
            int CellRule= 0;
            Row row = sheet.createRow(0);
            for (StudentCourse cours : courses) {
               for (int i = 0; i < time.length; i++) {
                   if ((CellRule)<time.length){
                       Row row1 = sheet.createRow(i+1);
                       row1.createCell(0).setCellValue(time[i]);
                       CellRule++;
                   }
                   for (int j = 0; j < week.length; j++) {
                       if ((RowRule)<week.length){
                           row.createCell(j+1).setCellValue(week[j]);
                           RowRule++;
                       }
                       if (cours.getC_time().equals(week[j]+"，"+time[i])){
                           Row timefield = sheet.createRow(i+1);
                           timefield.createCell(0).setCellValue(time[i]);
                           timefield.createCell(j+1).setCellValue(cours.getC_name());
                       }
                   }
               }
           }

            out=new FileOutputStream(filepath);
            workbook.write(out);

             //如果文件在的话就删除文件
//            if (file.exists()) {
//                //如果存在文件就删除
//                file.delete();
//            }
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        msg=false;
        return msg;
    }

}
