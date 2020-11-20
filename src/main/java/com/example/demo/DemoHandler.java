package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.PageReportData;
import com.example.demo.model.TableCells;
import com.example.demo.utils.OkHttpRequest;
import okhttp3.Response;
import org.apache.poi.hssf.usermodel.*;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author cxl
 * @date： 2020-11-19 15:34
 */
public class DemoHandler {
    public static void main(String[] args){
        handleJsonFile();
    }

    /**
     * 读取json文件
     * @param fileName
     * @return
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 处理分析json文件
     */
    public static void handleJsonFile(){
        //解析json
        String path = DemoHandler.class.getClassLoader().getResource("biagepage.rdlx-json").getPath();
        String s = readJsonFile(path);
        PageReportData pageReportData= JSONObject.parseObject(s,PageReportData.class);
        System.out.println(JSONObject.toJSONString(pageReportData));
        //?将json转换为excel模板
        //调url获取数据
        List<Map<String,String>> result = getDataByUrl("");
        //将数据加载到excel文档并输出
        genExcelFile(pageReportData,result);

    }

    public static void genExcelFile(PageReportData pageReportData, List<Map<String,String>> resultData){
        // 第一步，创建一个workbook，对应一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet hssfSheet = workbook.createSheet("sheet1");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = hssfSheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
        HSSFCell hssfCell = null;
        List<TableCells> tableCells = pageReportData.getFixedPage().getPages().get(0).getReportItems().get(0).getHeader().getTableRows().get(0).getTableCells();
        //表头
        for (int i=0;i<tableCells.size();i++){
            hssfCell = row.createCell(i);//列索引从0开始
            hssfCell.setCellValue(tableCells.get(i).getItem().getValue());//列名1
            hssfCell.setCellStyle(hssfCellStyle);//列居中显示
        }
        List<TableCells> tableCells1 =  pageReportData.getFixedPage().getPages().get(0).getReportItems().get(0).getDetails().getTableRows().get(0).getTableCells();
        //加载数据
        for(int i=0;i<resultData.size();i++){
            row = hssfSheet.createRow(i+1);
            for(int j=0;j<tableCells1.size();j++){
                String keyName = getKeyName(tableCells1.get(j).getItem().getValue());
                row.createCell(j).setCellValue(resultData.get(i).get(keyName));
            }
        }
        //写入文件
        try {
            workbook.write(new FileOutputStream(new File("E:\\webData\\excel.xls")));
            workbook.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static String getKeyName(String key){
        return key.replace("=Fields!","").replace(".Value","");
    }
    /**
     * 通过url查询数据
     */
    public static List<Map<String,String>> getDataByUrl(String uri){
        //调url获取数据
        try{
            OkHttpRequest okHttpRequest = new OkHttpRequest();
            String url = "http://xkit1.dataserver.cn/datakit/ims/prod/device/shift?type=shift&customerCode=0000102374&companyId=fb4d0be7-5eac-4d2b-9922-084d708852bf&timeRange=2020.08.01%2020:00:00,2020.08.31%2023:59:59";
            Response response = okHttpRequest.get(url);
            String result = response.body().string();
            QueryResultData resultData = JSONObject.parseObject(result,QueryResultData.class);
            System.out.println(JSONObject.toJSONString(resultData));
            return resultData.getValue().getQueryData().get(0);
            //将数据加载到excel输出
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


}
