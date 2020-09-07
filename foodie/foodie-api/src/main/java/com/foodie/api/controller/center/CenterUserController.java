package com.foodie.api.controller.center;

import com.foodie.api.controller.BaseController;
import com.foodie.api.resource.FileUpload;
import com.foodie.common.utils.CookieUtils;
import com.foodie.common.utils.JacksonUtils;
import com.foodie.common.utils.JamieDateUtils;
import com.foodie.common.utils.R;
import com.foodie.pojo.Users;
import com.foodie.pojo.bo.center.CenterUserBO;
import com.foodie.pojo.vo.UserVO;
import com.foodie.service.center.CenterUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 用户信息接口
 * @author jamie
 * @date 2020/8/13 10:33
 */
@Api(tags = "用户信息相关接口")
@RestController
@RequestMapping("userInfo")
public class CenterUserController extends BaseController {

    @Autowired
    private CenterUserService centerUserService;

    @Autowired
    private FileUpload fileUpload;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @ApiOperation(value = "用户头像修改", notes = "用户头像修改", httpMethod = "POST")
    @PostMapping("uploadFace")
    public R<String> uploadFace(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId, @ApiParam(name = "file", value = "用户头像", required = true) MultipartFile file) {
        // .sh .php
        // 定义头像保存的地址
        //        String fileSpace = IMAGE_USER_FACE_LOCATION;
        String fileSpace = fileUpload.getImageUserFaceLocation();
        // 在路径上为每一个用户增加一个userid，用于区分不同用户上传
        String uploadPathPrefix = File.separator + userId;
        // 开始文件上传
        if(file == null){
            return R.errorMsg("文件不能为空！");
        }
        FileOutputStream fileOutputStream = null;
        try{
            // 获得文件上传的文件名称
            String fileName = file.getOriginalFilename();
            if(StringUtils.isNotBlank(fileName)){
                // 文件重命名  imooc-face.png -> ["imooc-face", "png"]
                String[] fileNameArr = fileName.split("\\.");

                // 获取文件的后缀名
                String suffix = fileNameArr[fileNameArr.length - 1];
                if(!super.checkImgSuffix(suffix)){
                    return R.errorMsg("图片格式不正确！");
                }
                // face-{userid}.png
                // 文件名称重组 覆盖式上传，增量式：额外拼接当前时间
                String newFileName = "face-" + userId + "." + suffix;

                // 上传的头像最终保存的位置
                String finalFacePath = fileSpace + uploadPathPrefix + File.separator + newFileName;
                // 用于提供给web服务访问的地址
                uploadPathPrefix += ("/" + newFileName);

                File outFile = new File(finalFacePath);
                if(outFile.getParentFile() != null){
                    // 创建文件夹
                    outFile.getParentFile().mkdirs();
                }

                // 文件输出保存到目录
                fileOutputStream = new FileOutputStream(outFile);
                InputStream inputStream = file.getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(fileOutputStream != null){
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        // 获取图片服务地址
        String imageServerUrl = fileUpload.getImageServerUrl();

        // 由于浏览器可能存在缓存的情况，所以在这里，我们需要加上时间戳来保证更新后的图片可以及时刷新
        String finalUserFaceUrl = imageServerUrl + uploadPathPrefix + "?t=" + JamieDateUtils.getLocalDateTimeString();

        // 更新用户头像到数据库
        Users users = centerUserService.updateUserFace(userId, finalUserFaceUrl);

        // 增加令牌token，会整合进redis，分布式会话
        UserVO userVO = super.conventUserVo(users);
        CookieUtils.setCookie(request, response, "user", JacksonUtils.objectToJson(userVO), true);

        return R.ok();
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "POST")
    @PostMapping("update")
    public R<String> update(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId,
                            @RequestBody @Valid CenterUserBO centerUserBO) {
        System.out.println(centerUserBO);
        Users users = centerUserService.updateUserInfo(userId, centerUserBO);
        // 增加令牌token，会整合进redis，分布式会话
        UserVO userVO = super.conventUserVo(users);
        CookieUtils.setCookie(request, response, "user", JacksonUtils.objectToJson(userVO), true);
        return R.ok();
    }

    /**
     * 把字段设置为空
     * @param userResult 返回对象
     */
    private void setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
    }

}
