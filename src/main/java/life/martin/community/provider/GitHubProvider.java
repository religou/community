package life.martin.community.provider;

import com.alibaba.fastjson.JSON;
import life.martin.community.dto.AccessTokenDTO;
import life.martin.community.dto.GithubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @createTime 2022-09-02 23:03:00
 */

@Component
public class GitHubProvider {

    /*
     * 获取AccessToken
     * */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String str = response.body().string();
            String accessToken = str.split("&")[0].split("=")[1];
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /*
     * 根据Token获取对应的用户
     * */
    public GithubUserDTO getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            String str = response.body().string();
            GithubUserDTO githubUserDTO = JSON.parseObject(str, GithubUserDTO.class);
            return githubUserDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
