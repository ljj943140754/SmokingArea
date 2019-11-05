
package com.common.ano;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.common.enu.Role;
/**
 * 跳过登陆。有此注解的Controller类或者方法，说明无需检测登陆。
 * @author Administrator
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD} )
public @interface Permission {
	Role [] role();
}
