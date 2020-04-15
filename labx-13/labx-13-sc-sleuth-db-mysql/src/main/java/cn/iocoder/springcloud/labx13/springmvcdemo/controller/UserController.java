package cn.iocoder.springcloud.labx13.springmvcdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JdbcTemplate template;

    @GetMapping("/get")
    public String get(@RequestParam("id") Integer id) {
        this.selectById(1);
        return "success";
    }

    public Object selectById(Integer id) {
        return template.queryForObject("SELECT id, username, password FROM t_user WHERE id = ?",
                new BeanPropertyRowMapper<>(Object.class), // 结果转换成对应的对象。Object 理论来说是 UserDO.class ，这里偷懒了。
                id);
    }

}
