package top.waterspo.maker.cli.command;

import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import picocli.CommandLine;
import top.waterspo.maker.generator.file.FileGenerator;
import top.waterspo.maker.model.DataModel;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "generate", description = "生成模版文件", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"-l",
            "--loop"}, description = "是否循环", arity = "0..1", interactive = true, echo = true)
    private boolean loop;

    @CommandLine.Option(names = {"-a",
            "--author"}, description = "作者", arity = "0..1", interactive = true, echo = true)
    private String author = "mfjip";

    @CommandLine.Option(names = {"-o",
            "--outputText"}, description = "输出文本", arity = "0..1", interactive = true, echo = true)
    private String outputText = "sum = ";

    @Override
    public Integer call() throws Exception {
        DataModel dataModel = new DataModel();
        BeanUtil.copyProperties(this, dataModel);
        FileGenerator.doGenerate(dataModel);
        return 0;
    }
}
