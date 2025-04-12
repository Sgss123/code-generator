package ${basePackage}.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static java.lang.System.getProperty;

public class StaticGenerator {

    public static void copyFileByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);

    }
}
