package top.waterspo.generator;

import freemarker.template.TemplateException;
import top.waterspo.model.MainTemplateConfig;

import java.io.File;
import java.io.IOException;

import static java.lang.System.getProperty;

public class MainGenerator {

    public static void main(String[] args) throws TemplateException, IOException {
        // 1. 静态文件生成
        String projectPath = getProperty("user.dir");
        File parentFile = new File(projectPath).getParentFile();
        String inputPath = new File(parentFile, "yuzi-generator-demo-projects/acm-template").getAbsolutePath();
        String outputPath = projectPath;
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);

        // 2. 动态文件生成
        String dynamicInputPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String dynamicOutputPath = outputPath + File.separator + "acm-template/src/com/yupi/acm/MainTemplate.java";

        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("mfjip");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");
        DynamicGenerator.doGenerate(dynamicInputPath, dynamicOutputPath, mainTemplateConfig);
    }

}
