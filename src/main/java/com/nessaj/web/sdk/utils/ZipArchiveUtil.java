package com.nessaj.web.sdk.utils;

import com.nessaj.web.sdk.common.exception.UnzipFileException;
import org.apache.log4j.Logger;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author keming
 * @Date 2022/04/03 22:05
 */
public class ZipArchiveUtil {

    private static Logger logger = Logger.getLogger(ZipArchiveUtil.class);
    private static final int BUFFER_SIZE = 1024;

    public static boolean unZip(String path, String dest) throws UnzipFileException {
        if (!path.endsWith(".zip")) {
            throw new UnzipFileException("fail to unzip, it is not a zip-file, pls check the file format");
        }
        File file = null;
        InputStream inputStream = null;
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(path, Charset.forName("GBK"));
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String fileName = entry.getName();
                if (fileName.endsWith("/")) {
                    (new File(dest + fileName)).mkdir();
                    continue;
                }
                file = new File(dest + fileName);
                inputStream = zipFile.getInputStream(entry);
                createFile(inputStream, file);
            }
        } catch (IOException e) {
            logger.error("fail to unzip file: " + path);
        } finally {
            try {
                if (zipFile != null) {
                    zipFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private static void createFile(InputStream inputStream, File dest) {
        FileOutputStream fileOut = null;
        BufferedOutputStream bufferedOut = null;
        byte buf[] = new byte[BUFFER_SIZE];
        int count = -1;
        try {
            dest.createNewFile();
            fileOut = new FileOutputStream(dest);
            bufferedOut = new BufferedOutputStream(fileOut, BUFFER_SIZE);
            while ((count = inputStream.read(buf)) > -1) {
                bufferedOut.write(buf, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (bufferedOut != null) {
                    bufferedOut.flush();
                    bufferedOut.close();
                }
                if (fileOut != null) {
                    fileOut.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            ZipArchiveUtil.unZip("E:/Nessaj/local-workspace/manager/packages/module1.zip", "E:/Nessaj/local-workspace/manager/modules/module1/");
        } catch (UnzipFileException e) {
            e.printStackTrace();
        }
    }

}
