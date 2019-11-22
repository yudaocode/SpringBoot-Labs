package cn.iocoder.springboot.lab26.distributedsession.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private FindByIndexNameSessionRepository sessionRepository;

    @GetMapping("/list")
    public Map<String, ? extends Session> list(@RequestParam("username") String username) {
        return sessionRepository.findByPrincipalName(username);
    }

//    @GetMapping("/set") // 其实 PostMapping 更合适，单纯为了方便
//    public void set(HttpSession session,
//                    @RequestParam("key") String key,
//                    @RequestParam("value") String value) {
//        session.setAttribute(key, value);
//    }
//
//    @GetMapping("/get_all")
//    public Map<String, Object> getAll(HttpSession session) {
//        Map<String, Object> result = new HashMap<>();
//        // 遍历
//        for (Enumeration<String> enumeration = session.getAttributeNames();
//             enumeration.hasMoreElements();) {
//            String key = enumeration.nextElement();
//            Object value = session.getAttribute(key);
//            result.put(key, value);
//        }
//        // 返回
//        return result;
//    }

}
