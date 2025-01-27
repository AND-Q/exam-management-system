package com.example.back.controller;

import cn.hutool.core.io.FileUtil;
import com.example.back.common.AuthAccess;
import com.example.back.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 功能：文件的上传和下载（不使用这个）
 */

@RestController
@RequestMapping("/file")
public class FileController {

    // 获取iP,防止ip没定义报错，给默认值localhost
    @Value("${ip:localhost}")
    String ip;

    @Value("${server.port}")
    String port;

    // 定义一个全局的父级目录
    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "files";


    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        // 文件的原始名称
        String originalFilename = file.getOriginalFilename();
        // aaa.png
        String mainName = FileUtil.mainName(originalFilename);
        String extName = FileUtil.extName(originalFilename);

        // 当前文件的路径：C:\bendicangku\employee_management\back
//        String projectPath = System.getProperty("user.dir");
        // 新建的文件存储目录    C:\bendicangku\employee_management\back\files
//        String filesPath = projectPath + "\\files";

//        File parentFile = new File(ROOT_PATH);
        // saveFile.getParentFile().exists()   getParentFile()表示获取父级 文件存储的父级存在   加 !  => 不存在，创建文件
        // FileUtil.exist       加 !  => 不存在，创建文件
        if (!FileUtil.exist(ROOT_PATH)) {
            // 创建文件 files
            FileUtil.mkdir(ROOT_PATH);
//            parentFile.mkdirs();
        }
        // 拼接文件路径   C:\bendicangku\employee_management\back\files\aaa.png
        // 通过 saveFile 变量来存储文件路径
//        File saveFile = new File(ROOT_PATH + File.separator +  originalFilename);
        // 进行判断存储的文件夹里有没有同名文件   saveFile.exists()表示当前上传的文件已经存在
        if (FileUtil.exist(ROOT_PATH + File.separator +  originalFilename)) {
            // 重命名  System.currentTimeMillis()获取当前时间的毫秒数 + 名字 + . + 后缀
            originalFilename = System.currentTimeMillis() + "_" + mainName + "." + extName;
        }
        File saveFile = new File(ROOT_PATH + File.separator + originalFilename);
        // 存储文件到本地磁盘
        file.transferTo(saveFile);
        String url = "http://" + ip + ":" + port + "/file/download/" + originalFilename;

        // 返回文件的链接，这个链接就是文件的下载地址，这个下载地址就是我的后台提供出来的
        return Result.success(url);
    }


    @AuthAccess
    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        // 允许向后端请求下载      附件下载  强制设置下载预览
//        response.addHeader("Content-Disposition","attachment;filename" + URLEncoder.encode(fileName,"UTF-8"));
        // 预览 一般不用这个，浏览器自带预览
//        response.addHeader("Content-Disposition","inline;filename" + URLEncoder.encode(fileName,"UTF-8"));

        String filePath = ROOT_PATH + File.separator + fileName;
        if (!FileUtil.exist(filePath)) {
            return;
        }
        byte[] bytes = FileUtil.readBytes(filePath);
        ServletOutputStream outputStream = response.getOutputStream();
        // 数组是一个字节数组，也就是文件的字节流数组
        outputStream.write(bytes);
        // 刷新关闭
        outputStream.flush();
        outputStream.close();
    }
}
