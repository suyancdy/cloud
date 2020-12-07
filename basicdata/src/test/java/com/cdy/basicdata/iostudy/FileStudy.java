package com.cdy.basicdata.iostudy;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @Description:
 * @Author: chendeyin
 * @Date: 2020/11/14 9:31
 */
@Slf4j
public class FileStudy {
    public static void main(String[] args) {
        File file = new File("D:\\YhsFile\\temp\\banner.txt");
        log.info("===文件的名字：{}", file.getName());
        log.info("===绝对路径名: {}", file.getPath());
        log.info("===绝对路径:{}", file.getAbsoluteFile());
        log.info("===绝对路劲名: {}", file.getAbsolutePath());
    }
}
