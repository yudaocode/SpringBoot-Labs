package cn.iocoder.springboot.lab16.springdatamongodb.repository;

import cn.iocoder.springboot.lab16.springdatamongodb.dataobject.UserDO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository03 extends MongoRepository<UserDO, Integer> {

    // 使用 username 精准匹配
    default UserDO findByUsername01(String username) {
        // 创建 Example 对象，使用 username 查询
        UserDO probe = new UserDO();
        probe.setUsername(username); // 精准匹配 username 查询
        Example<UserDO> example = Example.of(probe);
        // 执行查询
        return findOne(example)
                .orElse(null); // 如果为空，则返回 null
    }

    // 使用 username 模糊匹配
    default UserDO findByUsernameLike01(String username) {
        // 创建 Example 对象，使用 username 查询
        UserDO probe = new UserDO();
        probe.setUsername(username); // 这里还需要设置
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains()); // 模糊匹配 username 查询
        Example<UserDO> example = Example.of(probe, matcher);
        // 执行查询
        return findOne(example)
                .orElse(null); // 如果为空，则返回 null
    }

}
