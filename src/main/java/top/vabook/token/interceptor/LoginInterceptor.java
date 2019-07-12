package top.vabook.token.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.vabook.token.annotation.NoneAuth;
import top.vabook.token.constant.NormalConstant;
import top.vabook.token.entity.JsonData;
import top.vabook.token.utils.JsonUtils;
import top.vabook.token.utils.token2.TokenHelper;
import top.vabook.token.utils.token2.TokenModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: vabook
 * @Date: 2019/7/11 16:06
 */

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenHelper tokenHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //不是映射方法直接通过
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        //如果被@NoneAuth注解不需要验证登录
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.getAnnotation(NoneAuth.class) != null)
            return true;

        //获取tokenmodel,包含了userId,和token
        String authStr = request.getHeader(NormalConstant.AUTHORIZATION);
        TokenModel model = tokenHelper.get(authStr);

        //验证通过
        if (tokenHelper.check(model)){
            request.setAttribute(NormalConstant.CURRENT_USER_ID,model.getUserId());
            return true;
        }

        //未通过
        response.setCharacterEncoding("UTF-8");

        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JsonUtils.obj2String(JsonData.buildError(401,"权限未认证")));

        return false;
    }
}
