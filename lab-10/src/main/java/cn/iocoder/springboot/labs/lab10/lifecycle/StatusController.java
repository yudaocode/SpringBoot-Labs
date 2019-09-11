package cn.iocoder.springboot.labs.lab10.lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // TODO rest 没生效，得排查下。
@RequestMapping("/") // TODO 可配置
public class StatusController {

    @Autowired
    private ServerLifeCycleHealthIndicator serverLifeCycleHealthIndicator;

    @RequestMapping("/status")
    public ResponseEntity<String> status() {
        Status status = serverLifeCycleHealthIndicator.status();
        // 成功
        if (Status.UP == status) {
            return new ResponseEntity<>(status.getDescription(), HttpStatus.OK);
        }
        // 失败
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(status.getDescription());
    }

}
