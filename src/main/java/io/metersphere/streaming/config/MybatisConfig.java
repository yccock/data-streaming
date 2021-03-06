package io.metersphere.streaming.config;

import io.metersphere.streaming.base.domain.FileContent;
import io.metersphere.streaming.commons.MybatisInterceptor;
import io.metersphere.streaming.commons.MybatisInterceptorConfig;
import io.metersphere.streaming.commons.utils.CompressUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@Configuration
@MapperScan(basePackages = "io.metersphere.streaming.base.mapper")
@EnableTransactionManagement
public class MybatisConfig {
    @Bean
    @ConditionalOnMissingBean
    public MybatisInterceptor dbInterceptor() {
        MybatisInterceptor interceptor = new MybatisInterceptor();
        List<MybatisInterceptorConfig> configList = new ArrayList<>();
        configList.add(new MybatisInterceptorConfig(FileContent.class, "file", CompressUtils.class, "zip", "unzip"));
        interceptor.setInterceptorConfigList(configList);
        return interceptor;
    }
}