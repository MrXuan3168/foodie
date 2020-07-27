package com.foodie.common.utils;

import java.io.*;

import org.springframework.util.ResourceUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 版本号读取工具类
 */
@Slf4j
public class VersionUtil {

    private static final String VERSION_FILE_NAME = "classpath:version.txt";

    /**
     * @return 返回版本号
     */
    public static String getVersionText() {
        StringBuilder result = new StringBuilder();
        try {
            File versionFile = ResourceUtils.getFile(VERSION_FILE_NAME);
            if (versionFile.exists()) {
                System.out.println(versionFile.getAbsolutePath());
                try (BufferedReader br = new BufferedReader(new FileReader(versionFile))) {
                    String s;
                    while ((s = br.readLine()) != null) {
                        result.append(System.lineSeparator()).append(s);
                    }
                } catch (IOException e) {
                    log.error("io异常");
                }

            }
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException异常");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println("version:" + getVersionText());
    }

}
