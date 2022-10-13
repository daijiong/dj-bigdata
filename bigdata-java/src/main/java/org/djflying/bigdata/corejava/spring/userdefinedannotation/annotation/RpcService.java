package org.djflying.bigdata.corejava.spring.userdefinedannotation.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 *
 * @author dj4817
 * @version $Id: RpcService.java, v 0.1 2017/12/10 20:30 dj4817 Exp $$
 */
// 注解用在接口（类）上
@Target({ ElementType.TYPE })
// JVM将在运行期也保留注释，因此可以通过反射机制读取注解的信息
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcService {

    String value();
}
