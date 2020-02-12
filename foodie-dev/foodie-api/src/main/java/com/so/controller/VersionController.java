package com.so.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 应用模块名称：
 * 
 * @author show
 * @since 2020/2/12 18:55
 */
@RestController
@Slf4j
public class VersionController {

    @GetMapping("/version")
    public Map<String, String> versionInformation() {
        return readGitProperties();
    }

    /**
     * 读取文件
     * 
     * @author xuanweiyao
     * @date 2020/2/12 19:42
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    private Map<String, String> readGitProperties() {
        ClassPathResource classPathResource = new ClassPathResource("git.properties");
        InputStream inputStream = null;
        try {
            inputStream = classPathResource.getInputStream();
        } catch (IOException e) {
            log.error("获取文件异常", e);
        }
        return readFromInputStream(inputStream);
    }

    /**
     * 读取文件里面的值
     * 
     * @author xuanweiyao
     * @date 2020/2/12 19:41
     * @param inputStream
     *            文件输入流
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    private Map<String, String> readFromInputStream(InputStream inputStream) {
        Map<String, String> gitDataMap = new HashMap<>(16);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                if (row > 1) {
                    String[] split = line.split("=");
                    gitDataMap.put(split[0], split[1]);
                }
                row++;
            }
        } catch (IOException e) {
            log.error("读取文件失败", e);
        }
        return gitDataMap;
    }

    // public static void main(String[] args) {
    // VersionController versionController = new VersionController();
    // Map<String, String> s = versionController.readGitProperties();
    // for (Map.Entry<String, String> stringStringEntry : s.entrySet()) {
    // System.out.println(stringStringEntry.getKey() + ":" + stringStringEntry.getValue());
    // }
    // }

}
