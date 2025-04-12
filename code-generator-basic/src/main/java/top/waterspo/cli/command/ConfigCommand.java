package top.waterspo.cli.command;

import cn.hutool.core.util.ReflectUtil;
import picocli.CommandLine;
import top.waterspo.model.MainTemplateConfig;

import java.lang.reflect.Field;


@CommandLine.Command(name = "config",description = "查看配置", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{

    @Override
    public void run() {
        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);
        for (Field field : fields) {
            System.out.println("字段类型："+field.getType());
            System.out.println("字段名称："+field.getName());
        }
    }
}
