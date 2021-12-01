package com.kapcb.framework.middleware.configuration;

import com.kapcb.framework.middleware.operation.LimiterOperationSource;
import org.springframework.aop.ClassFilter;
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

    private LimiterOperationSource limiterOperationSource;

    private final LimiterOperationSourcePointcut limiterOperationSourcePointcut = new LimiterOperationSourcePointcut() {
        @Override
        protected LimiterOperationSource getLimiterOperationSource() {
            return BeanFactoryLimiterOperationSourceAdvisor.this.limiterOperationSource;
        }
    };

    public BeanFactoryLimiterOperationSourceAdvisor() {
    }

    public void setLimiterOperationSource(LimiterOperationSource limiterOperationSource) {
        this.limiterOperationSource = limiterOperationSource;
    }

    public void setClassFilter(ClassFilter classFilter) {
        this.limiterOperationSourcePointcut.setClassFilter(classFilter);
    }

    @Override
    public Pointcut getPointcut() {
        return this.limiterOperationSourcePointcut;
    }
}
