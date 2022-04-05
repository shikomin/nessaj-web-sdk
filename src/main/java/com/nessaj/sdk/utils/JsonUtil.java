package com.nessaj.sdk.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author keming
 * @Date 2022/04/01 22:13
 */
public class JsonUtil {

    private static final Logger LOGGER = Logger.getLogger(JsonUtil.class);

    public static String readJsonFile(String filePath) {
        BufferedReader reader = null;
        String readJson = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                readJson += tempString;
            }
            inputStreamReader.close();
            fileInputStream.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
        return readJson;
    }

    public static JSONObject str2json(String str) {
        return JSON.parseObject(str);
    }

    public static void main(String[] args) {
        String jsonStr = JsonUtil.readJsonFile("E:/Nessaj/local-workspace/portal/packages/module.json");
        System.out.println(jsonStr);
        System.out.println(str2json(str2json(jsonStr).getString("module")).getString("mname"));
    }

}
