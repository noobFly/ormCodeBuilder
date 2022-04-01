<#include "/macro.include"/>
<#include "/java_copyright.include">

package ${basepackage}.domain;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 数字值全部设置为0，字符串设置为空字符串，"id"属性不会初始化，用于实体类的值初始化
 * @author fengjie
 * @date 2018年8月29日 上午10:31:14
 */
public class Initializable {
    /**
     * 数字值全部设置为0，字符串设置为空字符串，"id"属性不会初始化
     * @author fengjie
     * @date 2018年8月29日 上午10:33:39
     */
	protected void init() {
	    Field[] fields = this.getClass().getDeclaredFields();
        for(Field field : fields) {
            try {
                if(Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                String fieldName = field.getName();
                if("id".equals(fieldName)) {
                    continue;
                }
                Class<?> declaringClass = field.getType();
                if(Number.class.isAssignableFrom(declaringClass)) {
                    BeanUtils.setProperty(this, fieldName, 0);
                    continue;
                }
                if(String.class.isAssignableFrom(declaringClass)) {
                    BeanUtils.setProperty(this, fieldName, "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
}
