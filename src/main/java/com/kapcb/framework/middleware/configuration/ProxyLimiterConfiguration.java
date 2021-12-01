package com.kapcb.framework.middleware.configuration;

import com.kapcb.framework.middleware.operation.LimiterOperationSource;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * <a>Title: ProxyLimiterConfiguration </a>
 * <a>Author: Kapcb <a>
 * <a>Description: ProxyLimiterConfiguration <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/29 22:25
 * @since 1.0
 */
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ProxyLimiterConfiguration extends AbstractLimiterConfiguration {

    @Bean(name = "11111")
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public BeanFactoryLimiterOperationSourceAdvisor beanFactoryLimiterOperationSourceAdvisor() {
        BeanFactoryLimiterOperationSourceAdvisor sourceAdvisor = new BeanFactoryLimiterOperationSourceAdvisor();
        sourceAdvisor.setLimiterOperationSource(limiterOperationSource());

    }

    public LimiterOperationSource limiterOperationSource() {
        String[] modules = this.enableLimiter.getStringArray("modules");
        if (modules == null || modules.length == 0) {

        }
    }

}
