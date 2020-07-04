package lab2.authorization.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private ConsumerTokenServices tokenServices;
    @Autowired(required = false)
    private TokenStore tokenStore;

    @RequestMapping(method = RequestMethod.POST, value = "api/access_token/revoke")
    public String revokeToken(@RequestParam("token") String token) {
        tokenServices.revokeToken(token);
        return token;
    }

    @RequestMapping(method = RequestMethod.POST, value = "api/refresh_token/revoke")
    public String revokeRefreshToken(@RequestParam("token") String token) {
        tokenStore.removeRefreshToken(new DefaultOAuth2RefreshToken(token));
        return token;
    }

}