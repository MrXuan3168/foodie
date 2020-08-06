package com.foodie.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.foodie.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 应用模块名称：程序版本
 *
 * @author jamie
 * @since 2020/2/12 18:55
 */
@RestController
@Slf4j
@Api(tags = {"代码版本api"})
public class VersionController {

    @ApiOperation(value = "获取代码版本", notes = "获取代码版本", httpMethod = "GET")
    @GetMapping("/version")
    public R<Object> versionInformation() {
        return readGitProperties();
    }

    /**
     * 读取文件
     */
    private R<Object> readGitProperties() {
        ClassPathResource classPathResource = new ClassPathResource("git.properties");
        try(InputStream inputStream = classPathResource.getInputStream()){
            String s = readFromInputStream(inputStream);
            // 虽然写入文件的时，就是json格式，但是会带一些换行符，空字符等信息，用fastJson处理一下。
            Object parse = JSONObject.parse(s);
            return R.ok(parse);
        }catch(IOException e){
            log.error("获取文件异常", e);
            throw new RuntimeException("获取版本号,获取文件异常");
        }
    }

    /**
     * 读取文件里面的值
     *
     * @param inputStream 文件输入流
     */
    private String readFromInputStream(InputStream inputStream) {
        JSONObject gitJson = new JSONObject();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            while((line = br.readLine()) != null){
                boolean b = line.contains("git.");
                if(b){
                    line = line.replaceAll("\\\\", "");
                    String[] split = line.split("=");
                    gitJson.put(split[0], split[1]);
                }
            }
            return gitJson.toString();
        }catch(IOException e){
            log.error("读取文件失败", e);
            throw new RuntimeException("获取版本号,读取文件失败异常");
        }
    }

    // public static void main(String[] args) {
    // VersionController versionController = new VersionController();
    // R<Object> objectR = versionController.readGitProperties();
    // System.out.println(objectR);
    // }
}
