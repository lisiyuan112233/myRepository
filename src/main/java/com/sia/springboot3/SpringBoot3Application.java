package com.sia.springboot3;
import com.sia.springboot3.bean.User;
import com.sia.springboot3.bean.User2;
import com.sia.springboot3.beans.bean2;
import com.sia.springboot3.event.myApplicationEvent;
import com.sia.springboot3.service.userService;
import io.lettuce.core.ReadFrom;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ProblemDetail;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

//@EnableConfigurationProperties(User.class)
@ConfigurationPropertiesScan("com.sia.springboot3.bean")
@SpringBootApplication
@EnableAspectJAutoProxy
//@MapperScan("com.sia.springboot3.Mapper")
public class SpringBoot3Application {

    @SuppressWarnings("all")
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBoot3Application.class, args);
//        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
//        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
//        singletonObjects.setAccessible(true);
//        Map<String, Object> stringObjectMap = (Map<String, Object>) (singletonObjects.get(beanFactory));
//        stringObjectMap.entrySet().stream().filter(e->e.getKey().startsWith("bean")).forEach(System.out::println);
//        System.out.println(applicationContext.getMessage("hi", null, Locale.JAPANESE));
//        applicationContext.publishEvent(new myApplicationEvent(applicationContext.getResource("application.properties"), "kaishi"));
//        (new bean2()).print();
//        ContentNegotiationManager bean = applicationContext.getBean(ContentNegotiationManager.class);
//        bean.getMessageConverters().stream()
//                .map(converter -> converter.getClass().getName())
//                .collect(Collectors.toList());
//        applicationContext.close();

//        System.out.println(applicationContext.getBean(User.class));
//        applicationContext.close();
    }
    @Bean
    public LettuceClientConfigurationBuilderCustomizer configurationBuilderCustomizer(){
        return conf->conf.readFrom(ReadFrom.REPLICA_PREFERRED);
    }

}
