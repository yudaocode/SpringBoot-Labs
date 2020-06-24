package cn.iocoder.springboot.lab68.resourceserverdemo.controller;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

//    @Autowired
//    private OAuth2ProtectedResourceDetails details;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername(username);
        resourceDetails.setPassword(password);
        resourceDetails.setClientId("clientapp");
        resourceDetails.setClientSecret("112233");
        resourceDetails.setAccessTokenUri("http://127.0.0.1:8080/oauth/token");
//        resourceDetails.setAccessTokenUri("http://127.0.0.1:8080/oauth/authorize");
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);
//        resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.form); // fetch access_token by sending authentication data in HTTP Body
//        resourceDetails.setAuthenticationScheme(AuthenticationScheme.header); // send access_token via HTTP Header 'Bearer' field when accessing actual service
//
//        DefaultAccessTokenRequest defaultAccessTokenRequest = new DefaultAccessTokenRequest();
        restTemplate.setAccessTokenProvider(new ResourceOwnerPasswordAccessTokenProvider());
        OAuth2AccessToken accessToken = restTemplate.getAccessToken();

        String result  = restTemplate.postForObject("http://127.0.0.1:8080/oauth/authorize", resourceDetails,
                String.class);
        return "";
    }

//    public String login() {
//        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
//        resourceDetails.setUsername("roy");
//        resourceDetails.setPassword("spring");
//        resourceDetails.setAccessTokenUri(format("http://localhost:%d/oauth/token", port));
//        resourceDetails.setClientId("clientapp");
//        resourceDetails.setClientSecret("123456");
//        resourceDetails.setGrantType("password");
//        resourceDetails.setScope(asList("read", "write"));
//
//    }

}
