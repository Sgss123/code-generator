package top.waterspo.maker.generator;

import java.io.*;

public class JarGenerator {

    public static void doGenerate(String projectDir) throws IOException, InterruptedException {
        // 调用 Process 执行 mvn 命令
        String winMavenCommand = "mvn.cmd clean package -DskipTests=true -Dfile.encoding=UTF-8";
        String otherMavenCommand = "mvn clean package -DskipTests=true -Dfile.encoding=UTF-8";
        String mavenCommand = winMavenCommand;

        ProcessBuilder pb = new ProcessBuilder(mavenCommand.split(" "));
        pb.directory(new File(projectDir));

        Process process = pb.start();

        // 读取输出流
        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("进程已结束，退出代码为 " + exitCode);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerate("Z:\\IdeaProjects\\code-generator\\code-generator-maker-generated");
    }
}
