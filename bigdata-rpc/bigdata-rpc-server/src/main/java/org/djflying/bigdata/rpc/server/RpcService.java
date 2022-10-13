package org.djflying.bigdata.rpc.server;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RPC请求注解（标注在服务实现类上）
 *
 * @author dj4817
 * @version $Id: RpcService.java, v 0.1 2018/3/26 18:05 dj4817 Exp $$
 */
// 注解用在接口上
@Target({ ElementType.TYPE })
// JVM将在运行期也保留注释，因此可以通过反射机制读取注解的信息
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcService {

    Class<?> value();
}
