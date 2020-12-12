import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

public class ScrewMain {

    private static final String DB_URL = "jdbc:mysql://400-infra.server.iocoder.cn:3306";
    private static final String DB_NAME = "mall_system";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "3WLiVUBEwTbvAfsh";

    private static final String FILE_OUTPUT_DIR = "/Users/yunai/screw_test";
    private static final EngineFileType FILE_OUTPUT_TYPE = EngineFileType.HTML; // 可以设置 Word 或者 Markdown 格式
    private static final String DOC_FILE_NAME = "数据库文档";
    private static final String DOC_VERSION = "1.0.0";
    private static final String DOC_DESCRIPTION = "文档描述";

    public static void main(String[] args) {
        // 创建 screw 的配置
        Configuration config = Configuration.builder()
                .version(DOC_VERSION)  // 版本
                .description(DOC_DESCRIPTION) // 描述
                .dataSource(buildDataSource()) // 数据源
                .engineConfig(buildEngineConfig()) // 引擎配置
                .produceConfig(buildProcessConfig()) // 处理配置
                .build();

        // 执行 screw，生成数据库文档
        new DocumentationExecute(config).execute();
    }

    /**
     * 创建数据源
     */
    private static DataSource buildDataSource() {
        // 创建 HikariConfig 配置类
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl(DB_URL + "/" + DB_NAME);
        hikariConfig.setUsername(DB_USERNAME);
        hikariConfig.setPassword(DB_PASSWORD);
        hikariConfig.addDataSourceProperty("useInformationSchema", "true"); // 设置可以获取 tables remarks 信息
        // 创建数据源
        return new HikariDataSource(hikariConfig);
    }

    /**
     * 创建 screw 的引擎配置
     */
    private static EngineConfig buildEngineConfig() {
        return EngineConfig.builder()
                .fileOutputDir(FILE_OUTPUT_DIR) // 生成文件路径
                .openOutputDir(false) // 打开目录
                .fileType(FILE_OUTPUT_TYPE) // 文件类型
                .produceType(EngineTemplateType.freemarker) // 文件类型
                .fileName(DOC_FILE_NAME) // 自定义文件名称
                .build();
    }

    /**
     * 创建 screw 的处理配置，一般可忽略
     * 指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
     */
    private static ProcessConfig buildProcessConfig() {
        return ProcessConfig.builder()
                .designatedTableName(Collections.<String>emptyList())  // 根据名称指定表生成
                .designatedTablePrefix(Collections.<String>emptyList()) //根据表前缀生成
                .designatedTableSuffix(Collections.<String>emptyList()) // 根据表后缀生成
                .ignoreTableName(Arrays.asList("test_user", "test_group")) // 忽略表名
                .ignoreTablePrefix(Collections.singletonList("test_")) // 忽略表前缀
                .ignoreTableSuffix(Collections.singletonList("_test")) // 忽略表后缀
                .build();
    }

}
