package cn.iocoder.springboot.lab12.mybatis.mapper;

import cn.iocoder.springboot.lab12.mybatis.dataobject.UserDO;
import cn.iocoder.springboot.lab12.mybatis.util.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<UserDO> { // 继承的是，我们定义的 BaseMapper 接口

    default UserDO selectByUsername(@Param("username") String username) {
        Example example = new Example(UserDO.class);
        // 创建 Criteria 对象，设置 username 查询条件
        example.createCriteria().andEqualTo("username", username);
        // 执行查询
        return selectOneByExample(example);
    }

    List<UserDO> selectByIds(@Param("ids") Collection<Integer> ids);

    default List<UserDO> selectListByCreateTime(@Param("createTime") Date createTime) {
        Example example = new Example(UserDO.class);
        // 创建 Criteria 对象，设置 create_time 查询条件
        example.createCriteria().andGreaterThan("createTime", createTime);
        return selectByExample(example);
    }

}
