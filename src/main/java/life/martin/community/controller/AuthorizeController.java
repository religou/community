package life.martin.community.controller;

import life.martin.community.dto.AccessTokenDTO;
import life.martin.community.dto.GithubUserDTO;
import life.martin.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @createTime 2022-09-01 21:00:00
 */

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientID;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectURL;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code
            , @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientID);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectURL);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GithubUserDTO githubUserDTO = gitHubProvider.getUser(accessToken);
        return "index";
    }


}
