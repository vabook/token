package top.vabook.token.utils.token2;

/**
 * @Author: vabook
 * @Date: 2019/7/11 16:05
 * 接口,负责token的创建,检查删除
 */
public interface TokenHelper {

    // 由id生成token
    TokenModel create(Integer id);

    //检查token是否存在
    boolean check(TokenModel tokenModel);

    // 从前端的校验字符串中获取token
    TokenModel get(String authStr);


    //根据用户id删除token
    boolean delete(Integer id);




}
