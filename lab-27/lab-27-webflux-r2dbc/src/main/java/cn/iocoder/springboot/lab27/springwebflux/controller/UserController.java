package cn.iocoder.springboot.lab27.springwebflux.controller;

import cn.iocoder.springboot.lab27.springwebflux.dao.UserRepository;
import cn.iocoder.springboot.lab27.springwebflux.dataobject.UserDO;
import cn.iocoder.springboot.lab27.springwebflux.dto.UserAddDTO;
import cn.iocoder.springboot.lab27.springwebflux.dto.UserUpdateDTO;
import cn.iocoder.springboot.lab27.springwebflux.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

/**
 * 用户 Controller
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private static final UserDO USER_NULL = new UserDO();

    @Autowired
    private UserRepository userRepository;

    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    @GetMapping("/list")
    public Flux<UserVO> list() {
        // 返回列表
        return userRepository.findAll()
                .map(userDO -> new UserVO().setId(userDO.getId()).setUsername(userDO.getUsername()));
    }

    /**
     * 获得指定用户编号的用户
     *
     * @param id 用户编号
     * @return 用户
     */
    @GetMapping("/get")
    public Mono<UserVO> get(@RequestParam("id") Integer id) {
        // 返回
        return userRepository.findById(id)
                .map(userDO -> new UserVO().setId(userDO.getId()).setUsername(userDO.getUsername()));
    }

    /**
     * 添加用户
     *
     * @param addDTO 添加用户信息 DTO
     * @return 添加成功的用户编号
     */
    @PostMapping("add")
    @Transactional
    public Mono<Integer> add(UserAddDTO addDTO) {
        // 查询用户
        Mono<UserDO> user = userRepository.findByUsername(addDTO.getUsername());

        // 执行插入
        return user.defaultIfEmpty(USER_NULL) // 设置 USER_NULL 作为 null 的情况，否则 flatMap 不会往下走
                .flatMap(new Function<UserDO, Mono<Integer>>() {

                    @Override
                    public Mono<Integer> apply(UserDO userDO) {
                        if (userDO != USER_NULL) {
                            // 返回 -1 表示插入失败。
                            // 实际上，一般是抛出 ServiceException 异常。因为这个示例项目里暂时没做全局异常的定义，所以暂时返回 -1 啦
                            return Mono.just(-1);
                        }
                        // 将 addDTO 转成 UserDO
                        userDO = new UserDO()
                                .setUsername(addDTO.getUsername())
                                .setPassword(addDTO.getPassword())
                                .setCreateTime(new Date());
                        // 插入数据库
                        return userRepository.save(userDO).flatMap(new Function<UserDO, Mono<Integer>>() {
                            @Override
                            public Mono<Integer> apply(UserDO userDO) {
                                // 如果编号为偶数，抛出异常。
                                if (userDO.getId() % 2 == 0) {
                                    throw new RuntimeException("我就是故意抛出一个异常，测试下事务回滚");
                                }

                                // 返回编号
                                return Mono.just(userDO.getId());
                            }
                        });
                    }

                });
    }

    /**
     * 更新指定用户编号的用户
     *
     * @param updateDTO 更新用户信息 DTO
     * @return 是否修改成功
     */
    @PostMapping("/update")
    public Mono<Boolean> update(UserUpdateDTO updateDTO) {
        // 查询用户
        Mono<UserDO> user = userRepository.findById(updateDTO.getId());

        // 执行更新
        return user.defaultIfEmpty(USER_NULL) // 设置 USER_NULL 作为 null 的情况，否则 flatMap 不会往下走
                .flatMap(new Function<UserDO, Mono<Boolean>>() {

                    @Override
                    public Mono<Boolean> apply(UserDO userDO) {
                        // 如果不存在该用户，则直接返回 false 失败
                        if (userDO == USER_NULL) {
                            return Mono.just(false);
                        }
                        // 查询用户是否存在
                        return userRepository.findByUsername(updateDTO.getUsername())
                                .defaultIfEmpty(USER_NULL) // 设置 USER_NULL 作为 null 的情况，否则 flatMap 不会往下走
                                .flatMap(new Function<UserDO, Mono<? extends Boolean>>() {

                                    @Override
                                    public Mono<? extends Boolean> apply(UserDO usernameUserDO) {
                                        // 如果用户名已经使用（该用户名对应的 id 不是自己，说明就已经被使用了）
                                        if (usernameUserDO != USER_NULL && !Objects.equals(updateDTO.getId(), usernameUserDO.getId())) {
                                            return Mono.just(false);
                                        }
                                        // 执行更新
                                        userDO.setUsername(updateDTO.getUsername());
                                        userDO.setPassword(updateDTO.getPassword());
                                        return userRepository.save(userDO).map(userDO -> true); // 返回 true 成功
                                    }

                                });
                    }

                });
    }

    /**
     * 删除指定用户编号的用户
     *
     * @param id 用户编号
     * @return 是否删除成功
     */
    @PostMapping("/delete") // URL 修改成 /delete ，RequestMethod 改成 DELETE
    public Mono<Boolean> delete(@RequestParam("id") Integer id) {
        // 查询用户
        Mono<UserDO> user = userRepository.findById(id);

        // 执行删除。这里仅仅是示例，项目中不要物理删除，而是标记删除
        return user.defaultIfEmpty(USER_NULL) // 设置 USER_NULL 作为 null 的情况，否则 flatMap 不会往下走
                .flatMap(new Function<UserDO, Mono<Boolean>>() {

                    @Override
                    public Mono<Boolean> apply(UserDO userDO) {
                        // 如果不存在该用户，则直接返回 false 失败
                        if (userDO == USER_NULL) {
                            return Mono.just(false);
                        }
                        // 执行删除
                        return userRepository.deleteById(id).map(aVoid -> true); // 返回 true 成功
                    }

                });
    }

}
