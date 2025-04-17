package org.wetime;

import org.wetime.authority.AuthorityUtils;
import org.wetime.authority.BaseAuthority;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableAspectJAutoProxy
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "org.wetime.mapper")
@EnableScheduling
public class wetime {

    public static void main(String[] args) {
        AuthorityUtils.setGlobalVerify(true,new BaseAuthority());
        SpringApplication.run(wetime.class, args);

    }


}
