package cn.iocoder.springboot.lab12.mybatis.mapper;

import cn.iocoder.springboot.lab12.mybatis.dataobject.UserDO;
import cn.iocoder.springboot.lab12.mybatis.vo.UserDetailVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<UserDO> {

    default UserDO selectByUsername(@Param("username") String username) {
        return selectOne(new QueryWrapper<UserDO>().eq("username", username));
    }

    List<UserDO> selectByIds(@Param("ids") Collection<Integer> ids);

    default IPage<UserDO> selectPageByCreateTime(IPage<UserDO> page, @Param("createTime") Date createTime) {
        return selectPage(page,
                new QueryWrapper<UserDO>().gt("create_time", createTime)
//                new QueryWrapper<UserDO>().like("username", "46683d9d")
        );
    }

    List<UserDetailVO> selectListA();

    List<UserDetailVO> selectListB();

}
