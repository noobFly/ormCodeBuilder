package ${basepackage};

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import cn.utrust.fintech.framework.RestAppConfig;
import cn.utrust.fintech.framework.config.DefaultRedisConfiguration;
import cn.utrust.fintech.framework.rest.AutoWrapperResponseBodyConfiguration;
import cn.utrust.fintech.framework.security.authz.interceptor.AuthzInterceptor;
import cn.utrust.fintech.framework.util.AppUtil;
import cn.utrust.fintech.mybatis.assist.MybatisExtConfiguration;

@SpringBootApplication
@ComponentScan(value={"cn.utrust.fintech"})
@EntityScan(basePackages={"${basepackage}.domain"})
@Import({AutoWrapperResponseBodyConfiguration.class, DefaultRedisConfiguration.class, MybatisExtConfiguration.class})
@EnableCaching
public class App extends RestAppConfig{
    private static Logger logger = LoggerFactory.getLogger(App.class);
	@Autowired
	private AuthzInterceptor authzInterceptor; //权限拦截器
    public static void main( String[] args )
    {
    	ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
    	AppUtil.setApplicationContext(context);
    }
    
    @PostConstruct
    public void init(){
    	initInterceptor();
    }
    
    private void initInterceptor() {
        authzInterceptor.addIncludePaths("/**");
        addInterceptor(authzInterceptor);  //如果不需要做用户登录/权限控制，可以把此拦截器去掉
    }
}
