package cn.iocoder.springboot.lab14.jdbctemplate.dao;

import cn.iocoder.springboot.lab14.jdbctemplate.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {

    /**
     * 声明 INSERT 操作的 PreparedStatementCreatorFactory 对象
     */
    private static final PreparedStatementCreatorFactory INSERT_PREPARED_STATEMENT_CREATOR_FACTORY
            = new PreparedStatementCreatorFactory("INSERT INTO users(username, password, create_time) VALUES(?, ?, ?)");

    static {
        // 设置返回主键
        INSERT_PREPARED_STATEMENT_CREATOR_FACTORY.setReturnGeneratedKeys(true);
        INSERT_PREPARED_STATEMENT_CREATOR_FACTORY.setGeneratedKeysColumnNames("id");
        // 设置每个占位符的类型
        INSERT_PREPARED_STATEMENT_CREATOR_FACTORY.addParameter(new SqlParameter(Types.VARCHAR));
        INSERT_PREPARED_STATEMENT_CREATOR_FACTORY.addParameter(new SqlParameter(Types.VARCHAR));
        INSERT_PREPARED_STATEMENT_CREATOR_FACTORY.addParameter(new SqlParameter(Types.TIMESTAMP));
    }

    @Autowired
    private JdbcTemplate template;

    /**
     * 使用 PreparedStatementCreator 实现插入数据
     *
     * @param entity 实体
     * @return 影响行数
     */
    public int insert(UserDO entity) {
        // 创建 KeyHolder 对象，设置返回的主键 ID
        KeyHolder keyHolder = new GeneratedKeyHolder();
        // 执行插入操作
        int updateCounts = template.update(INSERT_PREPARED_STATEMENT_CREATOR_FACTORY.newPreparedStatementCreator(
                Arrays.asList(entity.getUsername(), entity.getPassword(), entity.getCreateTime())
        ), keyHolder);
        // 设置 ID 主键到 entity 实体中
        if (keyHolder.getKey() != null) {
            entity.setId(keyHolder.getKey().intValue());
        }
        // 返回影响行数
        return updateCounts;
    }

    /**
     * 使用 SimpleJdbcInsert 实现插入数据
     *
     * @param entity 实体
     * @return 影响行数
     */
    public int insert0(UserDO entity) {
        // 创建 SimpleJdbcInsert 对象
        SimpleJdbcInsert insertOp = new SimpleJdbcInsert(template);
        insertOp.setTableName("users");
        insertOp.setColumnNames(Arrays.asList("username", "password", "create_time"));
        insertOp.setGeneratedKeyName("id");
        // 拼接参数
        Map<String, Object> params = new HashMap<>();
        params.put("username", entity.getUsername());
        params.put("password", entity.getPassword());
        params.put("create_time", entity.getCreateTime());
        // 执行插入操作
        Number id = insertOp.executeAndReturnKey(params);
        // 设置 ID 主键到 entity 实体中
        entity.setId(id.intValue());
        // 返回影响行数
        return 1;
    }

    public int updateById(UserDO entity) {
        // JdbcTemplate 生成更新的动态 SQL 不是很方便，需要自己二次封装。类似 SimpleJdbcInsert 对象
        return template.update("UPDATE users SET password = ? WHERE id = ?", entity.getPassword(),
                entity.getId());
    }

    public int deleteById(Integer id) {
        return template.update("DELETE FROM users WHERE id = ?", id);
    }

    public UserDO selectById(Integer id) {
        return template.queryForObject("SELECT id, username, password, create_time FROM users WHERE id = ?",
                new BeanPropertyRowMapper<>(UserDO.class), // 结果转换成对应的对象
                id);
    }

    public UserDO selectByUsername(String username) {
        return template.queryForObject("SELECT id, username, password, create_time FROM users WHERE username = ? LIMIT 1",
                new BeanPropertyRowMapper<>(UserDO.class), // 结果转换成对应的对象
                username);
    }

    public List<UserDO> selectByIds(List<Integer> ids) {
        // 创建 NamedParameterJdbcTemplate 对象
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(template);
        // 拼接参数
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        // 执行查询
        return namedParameterJdbcTemplate.query(
                "SELECT id, username, password, create_time FROM users WHERE id IN (:ids)", // 使用 :ids 作为占位服务
                params,
                new BeanPropertyRowMapper<>(UserDO.class) // 结果转换成对应的对象
        );
    }

}
