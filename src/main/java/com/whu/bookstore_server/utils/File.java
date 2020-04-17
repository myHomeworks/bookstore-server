package com.whu.bookstore_server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;

public class File {

    private static final Logger log = LoggerFactory.getLogger(File.class);

    public static void writeFile(byte[] file, String filePath, String fileName) throws Exception {
        java.io.File targetFile = new java.io.File(filePath);
        if (!targetFile.exists()) {
            if (!targetFile.mkdir()) {
                log.error("Failed mkdir when preparing to write a received file!\nPath:" + filePath);
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream(filePath + fileName);
        fileOutputStream.write(file);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public static String getFileTypeByName(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

}
