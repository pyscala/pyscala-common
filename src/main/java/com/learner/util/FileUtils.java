package com.learner.util;



import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.List;

/**
 * Created by liufangliang on 2018/1/26.
 */
@Slf4j
public class FileUtils {
    /**
     * 从文件中读出字节数组
     * @param filePath
     * @return
     * @throws Exception
     */
    public static byte [] readByteFromFile(String filePath) throws Exception{
        File file = new File(filePath);
        if(file.exists()){
            if (file.length()>Integer.MAX_VALUE){
                throw new Exception("file is too big !");
            }
            ByteArrayOutputStream outputStream=null;
            FileInputStream inputStream=null;
            try {
                outputStream=new ByteArrayOutputStream((int)file.length());
                inputStream=new FileInputStream(file);
                byte [] buffer=new byte[2048];
                int len=-1;
                while ((len=inputStream.read(buffer))!=-1){
                    outputStream.write(buffer,0,len);
                }
                return outputStream.toByteArray();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
               if(inputStream!=null){
                   inputStream.close();
               }
               if (outputStream!=null){
                   outputStream.close();
               }
            }
        }else {
            throw new FileNotFoundException("file not exist !");
        }
        return null;
    }

    /**
     * 把字节数组写进文件中
     * @param path
     * @param cont
     */
    public static void writeByteToFile(String path,byte[] cont){
        File file=new File(path);
        if(!file.exists()){
            try {
                FileOutputStream outputStream=new FileOutputStream(file);
                outputStream.write(cont);
                outputStream.close();
            }catch (Exception e ){
                e.printStackTrace();
            }

        }else {
            System.out.println( path+"文件已经存在 ！ ");
        }
    }

    public static String readFile  (String path) throws Exception{
            return new String(readByteFromFile(path));
    }

    public static void createExcel(String excelPath,String sheetName,List<List<String>> lists) {

        if(lists!=null ){
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(sheetName);
            for (int i = 0; i < lists.size(); i++) {
                List<String> rowDetail=lists.get(i);
                if(rowDetail!=null){
                    HSSFRow row =sheet.createRow(i);
                    for (int i1 = 0; i1 < rowDetail.size(); i1++) {
                        row.createCell(i1).setCellValue(rowDetail.get(i1));
                    }
                }
            }
            try {
                FileOutputStream fos = new FileOutputStream(excelPath);
                workbook.write(fos);
                log.info("写入成功,共"+lists.size()+"条。");
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        HSSFRow row = sheet.createRow(0);
//
//        HSSFCell cell = row.createCell(0);
//        cell.setCellValue("分类");
//        cell = row.createCell(1);
//        cell.setCellValue("子分类");
//        cell = row.createCell(2);
//        cell.setCellValue("品牌");
//        cell = row.createCell(3);
//        cell.setCellValue("产品名称");
//        cell = row.createCell(4);
//        cell.setCellValue("产品名称去掉空格");
//        cell = row.createCell(5);
//        cell.setCellValue("回收人数");
//
//
//
//        for (int i = 0; i < products.size(); i++) {
//
//            HSSFRow row1 = sheet.createRow(i + 1);
//            AhsRecycleProduct product = products.get(i);
//            row1.createCell(0).setCellValue(product.getCategory());
//            row1.createCell(1).setCellValue(product.getSecondCategory());
//            row1.createCell(2).setCellValue(product.getBrand());
//            row1.createCell(3).setCellValue(product.getProductName());
//            row1.createCell(4).setCellValue(product.getSimpleProductName());
//            row1.createCell(5).setCellValue(product.getRecycleCount());
//        }

    }


    public static void main(String[] args) {
        try{
//            String str=FileUtils.readFile("/Users/liufangliang/PycharmProjects/spider/spider/observer.py");
//            System.out.println(new String(str));
//            FileUtils.writeByteToFile("/Users/liufangliang/Pictures/21513154760_.pic_thumb2.java",str.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
//        Chatbot chatbot= ChatbotManager.instance().get(Webhook.LOG);
//        chatbot.send("ok l ma ");

    }
}
