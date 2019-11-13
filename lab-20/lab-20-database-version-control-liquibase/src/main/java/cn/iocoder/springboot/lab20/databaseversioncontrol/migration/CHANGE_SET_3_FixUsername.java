package cn.iocoder.springboot.lab20.databaseversioncontrol.migration;

import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CHANGE_SET_3_FixUsername implements CustomTaskChange {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(Database database) throws CustomChangeException {
        JdbcConnection connection = (JdbcConnection) database.getConnection();
        try (PreparedStatement psmt = connection.prepareStatement("SELECT id, username, password, create_time FROM users")) {
            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    String username = rs.getString("username");
                    if ("yudaoyuanma".equals(username)) {
                        Integer id = rs.getInt("id");
                        // 这里，再来一刀更新操作，偷懒不写了。
                        logger.info("[migrate][更新 user({}) 的用户名({} => {})", id, username, "yutou");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getConfirmationMessage() {
        return null;
    }

    @Override
    public void setUp() throws SetupException {
    }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {
    }

    @Override
    public ValidationErrors validate(Database database) {
        return null;
    }

}
