package lab01.resource.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 示例模块 Controller
 */
@RestController
@RequestMapping("/api/example")
public class ExampleController {

//    @CrossOrigin
    @RequestMapping("/hello")
    public String hello() {
        return "world";
    }

}