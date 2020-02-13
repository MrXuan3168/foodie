package com.so.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 应用模块名称：程序版本
 * 
 * @author show
 * @since 2020/2/12 18:55
 */
@RestController
@Slf4j
public class VersionController {

    @GetMapping("/version")
    public String versionInformation() {
        return readGitProperties();
    }

    /**
     * 读取文件
     * 
     * @author xuanweiyao
     * @date 2020/2/12 19:42
     * @return java.lang.String
     */
    private String readGitProperties() {
        ClassPathResource classPathResource = new ClassPathResource("git.properties");
        try (InputStream inputStream = classPathResource.getInputStream()) {
            String s = readFromInputStream(inputStream);
            // 虽然写入文件的时，就是json格式，但是会带一些换行符，空字符等信息，用fastJson处理一下。
            Object parse = JSONObject.parse(s);
            return parse.toString();
        } catch (IOException e) {
            log.error("获取文件异常", e);
            return "{\"errMessage\":\"获取文件异常\"}";
        }
    }

    /**
     * 读取文件里面的值
     * 
     * @author xuanweiyao
     * @date 2020/2/12 19:41
     * @param inputStream
     *            文件输入流
     * @return java.lang.String
     */
    private String readFromInputStream(InputStream inputStream) {
        JSONObject gitJson = new JSONObject();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                boolean b = line.contains("git.");
                if (b) {
                    line = line.replaceAll("\\\\", "");
                    String[] split = line.split("=");
                    gitJson.put(split[0], split[1]);
                }
            }
            return gitJson.toString();
        } catch (IOException e) {
            log.error("读取文件失败", e);
            return "{\"errMessage\":\"读取文件异常\"}";
        }
    }

    // public static void main(String[] args) {
    // VersionController versionController = new VersionController();
    // String s = versionController.readGitProperties();
    // System.out.println(s);
    // }
}
