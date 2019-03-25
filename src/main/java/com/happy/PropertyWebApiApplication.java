package com.happy;

import com.happy.utils.HttpClientUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wangjun
 * @Title: PropertyWebApiApplication
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/17 18:12
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableSwagger2
@ComponentScan({"com.wj", "com.happy"})
public class PropertyWebApiApplication {
    public static void main(String args[]) {
        SpringApplication.run(PropertyWebApiApplication.class, args);
        //HttpClientUtil.httpGetRequest("http://localhost:9820/test/hello?name=12w");
    }
}
