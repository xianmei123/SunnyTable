package com.evigel.sunnytable.tool;

import com.evigel.sunnytable.config.ExcelColumn;
import com.evigel.sunnytable.dto.DataSetDto;
import com.evigel.sunnytable.dto.LineDataDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.reflections.vfs.Vfs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tool {
//    public static void exportCSVFile(String uid, int type, DataSetDto data, String charset) throws IOException {
//        //File file = new File("C:\\Users\\15861\\IdeaProjects\\sunnytable", "test.csv");
//        String t = "";
//        if (type == 1) {
//            t = "csv";
//        }
//        else {
//            t = "txt";
//        }
//        //File file = new File("C:\\Users\\15861\\IdeaProjects\\sunnytable", uid + "." + t);
//
//        File file = new File("/root/res", uid + "." + t);
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        OutputStream out = new FileOutputStream(file);
//        try {
//            // 写入bom, 防止中文乱码
//            byte[] bytes = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
//            out.write(bytes);
//
//            OutputStreamWriter osw = new OutputStreamWriter(out, charset);
//            List<LineDataDto> lineDataDtos = data.getDataArray();
//            List<String> headerList = new ArrayList<>();
//            for (LineDataDto line : lineDataDtos) {
//                headerList.add(line.getName());
//            }
//            String[] header = headerList.toArray(new String[headerList.size()]);
//            CSVFormat csvFormat = CSVFormat.EXCEL.withHeader(header);
//
//            CSVPrinter csvPrinter = new CSVPrinter(osw, csvFormat);
//            int len = lineDataDtos.get(0).getLineData().size();
//            for (int i = 0; i < len; i++) {
//                for (LineDataDto line : lineDataDtos) {
//                    try {
//                        if (type == 1) {
//                            csvPrinter.print("\t" +line.getLineData().get(i) +"\t" );
//                        }
//                        else {
//                            csvPrinter.print(line.getLineData().get(i));
//                        }
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                csvPrinter.println();
//            }
////            while (iterator.hasNext()) {
////                Collection list = (Collection)iterator.next();
////                // 开始写一行数据
////                list.forEach(c->{
////                    try {
////                        csvPrinter.print("\t" +c.toString() +"\t" );
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                });
////                // 写完一行，需要换行
////                csvPrinter.println();
////            }
//            csvPrinter.flush();
//            csvPrinter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private final static Logger log = LoggerFactory.getLogger(Tool.class);

    private final static String EXCEL2003 = "xls";
    private final static String EXCEL2007 = "xlsx";

    public static ResponseEntity<byte[]> getFile(String uid, int type) throws Exception {
        String path = System.getProperty("user.dir");
        String t = "";
        if (type == 1) {
            t = "csv";
        }
        else {
            t = "txt";
        }
        //File file = new File("C:\\Users\\15861\\IdeaProjects\\sunnytable", uid + ".xls");
        //File file = new File("/root/res", uid + ".xls");
        File file = new File(path, uid + ".xls");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int n;
        while ((n = fis.read(b)) != -1)
        {
            bos.write(b, 0, n);
        }
        fis.close();
        bos.close();
        file.delete();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        String fileName = new String(("data.xls").getBytes("UTF-8"), "iso-8859-1");
//        httpHeaders.setContentDispositionFormData("attachment", fileName);
//        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        HttpHeaders httpHeaders = new HttpHeaders();
        String fileName = new String(("data.xls").getBytes("UTF-8"), "iso-8859-1");
        httpHeaders.setContentDispositionFormData("attachment", fileName);
        httpHeaders.setContentType(MediaType.valueOf("application/vnd.ms-excel"));
        ResponseEntity<byte[]> filebyte = new ResponseEntity<byte[]>(bos.toByteArray(), httpHeaders, HttpStatus.CREATED);
        return filebyte;
    }


    public static void exportXlsFile(String uid, int type, DataSetDto data, String charset) {
        String path = System.getProperty("user.dir");
        // 第一步，创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 第二步，在workbook中创建一个sheet对应excel中的sheet，这是第一列，不加也可以
        HSSFSheet sheet = workbook.createSheet("统计表");
        // 第三步，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，设置表头，不加也可以
        List<LineDataDto> lineDataDtos = data.getDataArray();
        List<String> headerList = new ArrayList<>();
        for (LineDataDto line : lineDataDtos) {
            headerList.add(line.getName());
        }
        String[] header = headerList.toArray(new String[headerList.size()]);

        //String header[] = { "码", "批次号", "组号"};
        // HSSFCell cell = null;
        for (int i = 0; i < header.length; i++) {
            row.createCell(i).setCellValue(header[i]);
        }
        // 第五步，写入实体数据，实际应用中这些数据从数据库得到,对象封装数据，集合包对象。对象的属性值对应表的每行的值
        int len = lineDataDtos.get(0).getLineData().size();
        for (int i = 0; i < len; i++) {
            //一行插入所有值
            HSSFRow row1 = sheet.createRow(i + 1);
            for (int j = 0; j < lineDataDtos.size(); j++) {
                // 创建单元格设值
                row1.createCell(j).setCellValue(lineDataDtos.get(j).getLineData().get(i));
            }
        }

        String titleA = uid + "xls"; //xls的文件名


        //File file = new File("C:\\Users\\15861\\IdeaProjects\\sunnytable", uid + ".xls");
        //File file = new File("/root/res", uid + ".xls");
        File file = new File(path, uid + ".xls");
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 将文件保存到指定的位置
        try {

            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回的是服务器地址


    }


    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                return HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
            } else {
                return BigDecimal.valueOf(cell.getNumericCellValue()).toString();
            }
        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return StringUtils.trimToEmpty(cell.getStringCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            return StringUtils.trimToEmpty(cell.getCellFormula());
        } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
            return "ERROR";
        } else {
            return cell.toString().trim();
        }

    }

    public static DataSetDto readExcel(MultipartFile file){
        DataSetDto dataSetDto = new DataSetDto();
        String fileName = file.getOriginalFilename();
        dataSetDto.setName(fileName);
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            log.error("上传文件格式不正确");
        }

        Workbook workbook = null;
        try {
            InputStream is = file.getInputStream();
            if (fileName.endsWith(EXCEL2007)) {
//                FileInputStream is = new FileInputStream(new File(path));
                workbook = new XSSFWorkbook(is);
            }
            if (fileName.endsWith(EXCEL2003)) {
//                FileInputStream is = new FileInputStream(new File(path));
                workbook = new HSSFWorkbook(is);
            }
            if (workbook != null) {
                Sheet sheet = workbook.getSheetAt(0);
                Row row0 = sheet.getRow(sheet.getFirstRowNum());
                List<LineDataDto> dataArray = new ArrayList<>();
                for (int i = row0.getFirstCellNum(); i <= row0.getLastCellNum(); i++) {
                    Cell cell = row0.getCell(i);
                    String cellValue = getCellValue(cell);
                    if (cellValue.equals("")) {
                        continue;
                    }
                    LineDataDto lineDataDto = new LineDataDto();

                    lineDataDto.setName(cellValue);
                    List<String> lineData = new ArrayList<>();
                    for (int j = sheet.getFirstRowNum() + 1; j <= sheet.getLastRowNum(); j++) {
                        Row row = sheet.getRow(j);
                        if (row == null) {
                            continue;
                        }
                        Cell cell1 = row.getCell(i);
                        String cellValue1 = getCellValue(cell1);
                        lineData.add(cellValue1);
                    }
                    lineDataDto.setLineData(lineData);
                    dataArray.add(lineDataDto);
                }
                dataSetDto.setDataArray(dataArray);

            }
        } catch (Exception e) {
            log.error("parse excel exception!", e);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    log.error("parse excel exception!", e);
                }
            }
        }
        return dataSetDto;
    }


}
