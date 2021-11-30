package com.kapcb.framework.middleware.configuration;

import com.kapcb.framework.middleware.annotation.EnableLimiter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Objects;

/**
 * <a>Title: AbstractLimiterConfiguration </a>
 * <a>Author: Kapcb <a>
 * <a>Description: AbstractLimiterConfiguration <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/29 22:25
 * @since 1.0
 */
@Configuration
public abstract class AbstractLimiterConfiguration implements ImportAware {

    protected AnnotationAttributes enableLimiter;

    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        this.enableLimiter = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(EnableLimiter.class.getName(), false));
        if (Objects.isNull(this.enableLimiter)) {
            throw new IllegalArgumentException("@EnableLimiter is not present on importing class " + annotationMetadata.getClassName());
        }
    }
}
