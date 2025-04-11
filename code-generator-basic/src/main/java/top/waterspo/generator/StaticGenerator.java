package top.waterspo.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;

public class StaticGenerator {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        String inputPath = projectPath + File.separator + "yuzi-generator-demo-projects" + File.separator
                + "acm-template";
        String outputPath = projectPath;
        System.out.println(projectPath);
        // copyFileByHutool(inputPath, outputPath);
    }

    public static void copyFileByHutool(String inputPath, String outputPath) {
        // 使用Hutool工具类复制文件
        FileUtil.copy(inputPath, outputPath, false);

    }
}
