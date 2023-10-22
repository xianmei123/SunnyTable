package com.evigel.sunnytable.controller;

import com.baidu.speech.restapi.common.DemoException;
import com.evigel.sunnytable.dto.AsrResult;
import com.evigel.sunnytable.exception.MyException;
import com.evigel.sunnytable.exception.ResultEnum;
import com.evigel.sunnytable.tool.AsrUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/asr")
public class ASRController {

    public static final String APP_ID = "24228050";
    public static final String API_KEY = "QiGobIfn14o0hNxmmQdo731x";
    public static final String SECRET_KEY = "IzXqdyTDji5nMBzfX3TRPCqke4TRXwsA";

    @RequestMapping("/result/{uid}")
    @ResponseBody
    public ResponseEntity<AsrResult> aiAsrTest(@PathVariable String uid, HttpServletRequest request) throws IOException, DemoException, InterruptedException {
        String name = uid;
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
        String curPath = System.getProperty("user.dir");
        if (file != null) {
            file.transferTo(new File(curPath + name));
        }
        //String path = curPath + "\\16kkk.pcm";
        //File file2 = new File(path);
        File file1 = new File(curPath + name);
        File file2 = new File(curPath + name.substring(0, name.length() - 4) + ".pcm");

        if (!file2.exists()) {
            file2.createNewFile();
        }
        Runtime runtime = Runtime.getRuntime();
        String cutCmd = "ffmpeg -y  -i " + file1 + "  -acodec pcm_s16le -f s16le -ac 1 -ar 16000 " + file2;
        Process proce = runtime.exec(cutCmd);
        proce.waitFor();

        byte[] fileByteArray = FileUtils.readFileToByteArray(file2);
        file2.delete();
        file1.delete();
        String result = AsrUtil.obtainAsrResult(fileByteArray);
        int index = result.indexOf("result");
        String resultStr = result.substring(index + 10);
        index = resultStr.indexOf("\"");
        resultStr = resultStr.substring(0, index);
        AsrResult asrResult = parseResult(resultStr);
        return ResponseEntity.ok().body(asrResult);
    }

    public AsrResult parseResult(String result) {
        String split1 = "花费";
        String split2 = "花了";
        String split3 = "收入";
        String split4 = "赚了";
        result = result.replaceAll("，|。", "");
        if (result.endsWith("元") || result.endsWith("块")) {
            result = result.substring(0, result.length() - 1);
        }
        else if (result.endsWith("块钱") || result.endsWith("元钱")) {
            result = result.substring(0, result.length() - 2);
        }
        String[] results = new String[0];
        int flag = 0;
        if (result.contains(split1)) {
            results = result.split(split1);
            flag = 2;
        }
        else if (result.contains(split2)) {
            results = result.split(split2);
            flag = 2;
        }
        else if (result.contains(split3)) {
            results = result.split(split3);
            flag = 1;
        }
        else if (result.contains(split4)) {
            results = result.split(split4);
            flag = 1;
        }
        if (results.length <= 1 || flag == 0) {
            throw new MyException(ResultEnum.DATA_ERROR, "未按模板输入");
        }
        if (results[1].equals("一")) {
            results[1] = "1";
        }
        else if (results[1].equals("二")) {
            results[1] = "2";
        }
        else if (results[1].equals("三")) {
            results[1] = "3";
        }
        else if (results[1].equals("四")) {
            results[1] = "4";
        }
        else if (results[1].equals("五")) {
            results[1] = "5";
        }
        else if (results[1].equals("六")) {
            results[1] = "6";
        }
        else if (results[1].equals("七")) {
            results[1] = "7";
        }
        else if (results[1].equals("八")) {
            results[1] = "8";
        }
        else if (results[1].equals("九")) {
            results[1] = "9";
        }
        else if (results[1].equals("十")) {
            results[1] = "10";
        }
        AsrResult asrResult = new AsrResult();
        asrResult.setDetail(results[0]);
        asrResult.setCost(results[1]);
        asrResult.setIo(flag - 1);
        return asrResult;
    }
}
