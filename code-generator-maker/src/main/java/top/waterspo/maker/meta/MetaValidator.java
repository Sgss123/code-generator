package top.waterspo.maker.meta;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import top.waterspo.maker.meta.enums.FileGenerateTypeEnum;
import top.waterspo.maker.meta.enums.FileTypeEnum;
import top.waterspo.maker.meta.enums.ModelTypeEnum;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class MetaValidator {

    public static void doValidAndFill(Meta meta) {
        validAndFillMetaRoot(meta);

        validAndFillFileConfig(meta);

        validAndFillModelConfig(meta);
    }

    private static void validAndFillModelConfig(Meta meta) {
        // modelConfig 校验和默认值
        Meta.ModelConfig modelConfig = meta.getModelConfig();
        if (modelConfig == null) {
            return;
        }
        List<Meta.ModelConfig.ModelInfo> modelInfoList = modelConfig.getModels();
        if (CollUtil.isEmpty(modelInfoList)) {
            return;
        }
        for (Meta.ModelConfig.ModelInfo modelInfo : modelInfoList) {
            String fieldName = modelInfo.getFieldName();
            if (StrUtil.isBlank(fieldName)) {
                throw new MetaException("未填写 fieldName");
            }
            String modelInfoType = modelInfo.getType();
            if (StrUtil.isEmpty(modelInfoType)) {
                modelInfo.setType(ModelTypeEnum.STRING.getValue());
            }
        }
    }

    private static void validAndFillFileConfig(Meta meta) {
        // fileConfig 校验和默认值
        Meta.FileConfig fileConfig = meta.getFileConfig();
        if (fileConfig == null) {
            return;
        }

        // sourceRootPath 必填
        String sourceRootPath = fileConfig.getSourceRootPath();
        if (StrUtil.isBlank(sourceRootPath)) {
            throw new MetaException("未填写 sourceRootPath");
        }

        // inputRootPath: .source + sourceRootPath 的最后一个层级路径
        String inputRootPath = fileConfig.getInputRootPath();
        String defaultInputRootPath = ".source" + File.separator + FileUtil.getLastPathEle(Paths.get(sourceRootPath))
                .getFileName()
                .toString()
                .toLowerCase();
        if (StrUtil.isEmpty(inputRootPath)) {
            fileConfig.setInputRootPath(defaultInputRootPath);
        }

        // outputRootPath: 默认为当前路径下的generated
        String outputRootPath = fileConfig.getOutputRootPath();
        String defaultOutputRootPath = "generated";
        if (StrUtil.isEmpty(outputRootPath)) {
            fileConfig.setOutputRootPath(defaultOutputRootPath);
        }

        String fileConfigType = fileConfig.getType();
        String defaultType = FileTypeEnum.DIR.getValue();
        if (StrUtil.isEmpty(fileConfigType)) {
            fileConfig.setType(defaultType);
        }

        List<Meta.FileConfig.FileInfo> fileInfoList = fileConfig.getFiles();
        if (!CollUtil.isEmpty(fileInfoList)) {
            for (Meta.FileConfig.FileInfo fileInfo : fileInfoList) {
                validAndFillFileInfo(fileInfo);
            }
        }
    }

    // 抽取文件信息验证和填充为单独的方法
    private static void validAndFillFileInfo(Meta.FileConfig.FileInfo fileInfo) {
        // inputPath: 必填
        String inputPath = fileInfo.getInputPath();
        if (StrUtil.isBlank(inputPath)) {
            throw new MetaException("未填写 inputPath");
        }

        // outputPath: 必填
        fileInfo.setOutputPath(StrUtil.emptyToDefault(fileInfo.getOutputPath(), inputPath));

        // type: 默认 inputPath 有文件后缀为 file，否则为 dir
        String type = fileInfo.getType();
        if (StrUtil.isBlank(fileInfo.getType())) {
            if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))) {
                fileInfo.setType(FileTypeEnum.DIR.getValue());
            } else {
                fileInfo.setType(FileTypeEnum.FILE.getValue());
            }

        }

        // generateType: 文件结尾为 ftl 时，generateType 为 dynamic，否则为 static
        if (StrUtil.isBlank(fileInfo.getGenerateType())) {
            fileInfo.setGenerateType(StrUtil.endWith(inputPath, ".ftl") ? FileGenerateTypeEnum.DYNAMIC.getValue() : FileGenerateTypeEnum.STATIC.getValue());
        }
    }

    private static void validAndFillMetaRoot(Meta meta) {

        // 基础信息校验和默认值
        meta.setName(StrUtil.blankToDefault(meta.getName(), "my-generator"));
        meta.setDescription(StrUtil.emptyToDefault(meta.getDescription(), "我的模版代码生成器"));
        meta.setBasePackage(StrUtil.blankToDefault(meta.getBasePackage(), "top.waterspo"));
        meta.setVersion(StrUtil.emptyToDefault(meta.getVersion(), "1.0.0"));
        meta.setAuthor(StrUtil.emptyToDefault(meta.getAuthor(), "mfjip"));
        meta.setCreateTime(StrUtil.emptyToDefault(meta.getCreateTime(), DateUtil.now()));
    }
}
