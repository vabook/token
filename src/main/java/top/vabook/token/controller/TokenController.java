package top.vabook.token.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.vabook.token.annotation.NoneAuth;
import top.vabook.token.constant.MessageConstant;
import top.vabook.token.constant.NormalConstant;
import top.vabook.token.entity.JsonData;
import top.vabook.token.entity.User;
import top.vabook.token.enums.HttpStatusEnum;
import top.vabook.token.mapper.UserMapper;
import top.vabook.token.utils.token2.TokenHelper;
import top.vabook.token.utils.token2.TokenModel;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: vabook
 * @Date: 2019/7/11 16:06
 */

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenHelper tokenHelper;


    @NoneAuth
    @GetMapping
    public Object login(String username,String password){
        User user = userMapper.findByName(username);
        if (user == null || !user.getPassword().equals(password)){
            return JsonData.buildError(HttpStatusEnum.NOT_FOUND.getCode(), MessageConstant.USERNAME_OR_PASSWORD_ERROR);
        }
        System.out.println("=================================================进入login===========================");

        TokenModel model = tokenHelper.create(user.getId());
        return JsonData.buildSuccess(model);

    }

    @DeleteMapping
    public Object logout(HttpServletRequest request){

        System.out.println("=================================================进入logout===========================");
        Integer userId = (Integer) request.getAttribute(NormalConstant.CURRENT_USER_ID);
        if (userId != null)
            tokenHelper.delete(userId);
        return JsonData.buildSuccess();
    }
}
