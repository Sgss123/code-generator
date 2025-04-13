package top.waterspo.maker;

import freemarker.template.TemplateException;
import top.waterspo.maker.generator.main.MainGenerator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        MainGenerator mainGenerator = new MainGenerator();
        args = new String[] {"generate", "--needGit=true"};
        mainGenerator.doGenerate(args);
    }
}
