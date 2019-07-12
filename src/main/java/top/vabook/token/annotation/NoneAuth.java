package top.vabook.token.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author: vabook
 * @Date: 2019/7/11 15:55
 *
 * 使用本注解不会进行登录验证
 */

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface NoneAuth {
}
