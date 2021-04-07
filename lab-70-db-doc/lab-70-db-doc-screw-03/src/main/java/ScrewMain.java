import cn.smallbun.screw.core.process.ProcessConfig;
import cn.smallbun.screw.extension.pojo.PojoConfiguration;
import cn.smallbun.screw.extension.pojo.execute.PojoExecute;
import cn.smallbun.screw.extension.pojo.strategy.HumpNameStrategy;
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
    private static final String JAVA_CLASS_PACKAGE = "cn.iocoder.dataobject";

    public static void main(String[] args) {
        // 创建 screw 的配置
        PojoConfiguration config = PojoConfiguration.builder()
                .path(FILE_OUTPUT_DIR) // 生成 POJO 相关的目录
                .packageName(JAVA_CLASS_PACKAGE) // 包名
                .nameStrategy(new HumpNameStrategy()) // 包名策略
                .useLombok(false) // 是否使用 Lombok
                .dataSource(buildDataSource()) // 数据源
                .processConfig(buildProcessConfig()) // 处理配置
                .build();

        // 执行 screw，生成 POJO 实体类
        new PojoExecute(config).execute();
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
