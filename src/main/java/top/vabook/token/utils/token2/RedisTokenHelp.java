package top.vabook.token.utils.token2;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.vabook.token.utils.RedisClient;

import java.util.UUID;

/**
 * @Author: vabook
 * @Date: 2019/7/11 16:05
 */

@Component
public class RedisTokenHelp implements TokenHelper{

    @Autowired
    private RedisClient redisClient;


    /**
     * tokenModel 生成的同时,存放到redis中
     * @param id
     * @return
     */
    @Override
    public TokenModel create(Integer id) {

        //token 随机生成
        String token = UUID.randomUUID().toString().replace("-","");
        TokenModel model = new TokenModel(id,token);
        redisClient.set(id == null ? null : String.valueOf(id),token,RedisClient.TOKEN_EXPIRES_SECOND);
        return model;
    }

    /**
     *  解析tokenModel,从redis中查询是否存在,并设置存活时间
     * @param tokenModel
     * @return
     */
    @Override
    public boolean check(TokenModel tokenModel) {
        boolean result = false;
        if (tokenModel != null){
            String userId = tokenModel.getUserId().toString();
            String token = tokenModel.getToken();
            //从redis拿token
            String authenticatedToken = redisClient.get(userId);

            //如果存在且等于
            if (authenticatedToken != null && authenticatedToken.equals(token)){

                // 设置存活时间
                redisClient.expire(userId,RedisClient.TOKEN_EXPIRES_SECOND);
                result = true;
            }
        }
        return result;


    }

    /**
     * 从str中解析出tokenModel
     * @param authStr
     * @return
     */
    @Override
    public TokenModel get(String authStr) {

        TokenModel model = null;
        if (StringUtils.isNotEmpty(authStr)){
            String[] modelArr = authStr.split("_");
            if (modelArr.length == 2){
                int userId = Integer.parseInt(modelArr[0]);
                String token = modelArr[1];
                model = new TokenModel(userId,token);
            }
        }

        return model;
    }


    /**
     * 从redis中删除
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return redisClient.remove(id == null ? null : String.valueOf(id));
    }
}
