package top.waterspo.cli.command;


import cn.hutool.core.bean.BeanUtil;
import freemarker.template.TemplateException;
import lombok.Data;
import picocli.CommandLine;
import top.waterspo.generator.MainGenerator;
import top.waterspo.model.MainTemplateConfig;

import java.io.IOException;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "generate", description = "生成模版文件", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable{

    @CommandLine.Option(names = {"-l", "--loop"}, description = "是否循环", arity = "0..1", interactive = true, echo = true)
    private boolean loop;

    @CommandLine.Option(names = {"-a", "--author"}, description = "作者", arity = "0..1", interactive = true, echo = true)
    private String author = "mfjip";

    @CommandLine.Option(names = {"-o", "--outputText"}, description = "输出文本", arity = "0..1", interactive = true, echo = true)
    private String outputText = "sum = ";

    @Override
    public Integer call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        MainGenerator.doGenerate(mainTemplateConfig);
        return 0;
    }
}
