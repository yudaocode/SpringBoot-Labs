package cn.iocoder.springboot.lab24;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import io.github.yedaxia.apidocs.plugin.markdown.MarkdownDocPlugin;

public class TestJApiDocs {

    public static void main(String[] args) {
        // 1. 创建生成文档的配置
        DocsConfig config = new DocsConfig();
        config.setProjectPath("/Users/yunai/Java/SpringBoot-Labs/lab-24/lab-24-apidoc-japidocs"); // 项目所在目录
        config.setDocsPath("/Users/yunai/Downloads/"); // 生成 HTML 接口文档的目标目录
        config.setAutoGenerate(true); // 是否给所有 Controller 生成接口文档
        config.setProjectName("示例项目"); // 项目名
        config.setApiVersion("V1.0"); // API 版本号
        config.addPlugin(new MarkdownDocPlugin()); // 使用 MD 插件，额外生成 MD 格式的接口文档
        // 2. 执行生成 HTML 接口文档
        Docs.buildHtmlDocs(config);
    }

}
