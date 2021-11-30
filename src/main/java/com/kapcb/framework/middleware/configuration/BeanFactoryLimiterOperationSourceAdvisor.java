package com.kapcb.framework.middleware.configuration;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 * <a>Title: BeanFactoryLimiterOperationSourceAdvisor </a>
 * <a>Author: Kapcb <a>
 * <a>Description: BeanFactoryLimiterOperationSourceAdvisor <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/29 22:31
 * @since 1.0
 */
public class BeanFactoryLimiterOperationSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    private

    @Override
    public Pointcut getPointcut() {
        return null;
    }
}
