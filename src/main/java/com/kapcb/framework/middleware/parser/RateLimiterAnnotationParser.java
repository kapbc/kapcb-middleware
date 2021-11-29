package com.kapcb.framework.middleware.parser;

import com.kapcb.framework.middleware.annotation.HRateLimiter;
import com.kapcb.framework.middleware.rate.RateLimiter;
import com.kapcb.framework.middleware.rate.RateLimiterOperation;
import com.kapcb.framework.middleware.operation.LimiterOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * <a>Title: RateLimiterAnnotationParser </a>
 * <a>Author: Kapcb <a>
 * <a>Description: RateLimiterAnnotationParser <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/29 21:42
 * @since 1.0
 */
public class RateLimiterAnnotationParser extends AbstractLimiterAnnotationParser<RateLimiter> {

    @Override
    public Collection<LimiterOperation<RateLimiter>> parserAnnotation(AnnotatedElement annotatedElement) {
        Collection<HRateLimiter> allMergedAnnotations = AnnotatedElementUtils.findAllMergedAnnotations(annotatedElement, HRateLimiter.class);

        if (CollectionUtils.isEmpty(allMergedAnnotations)) {
            return null;
        }
        Collection<LimiterOperation<RateLimiter>> limiterOperations = new ArrayList<>();
        for (HRateLimiter allMergedAnnotation : allMergedAnnotations) {
            limiterOperations.addAll(parserRateLimiterAnnotation(annotatedElement, allMergedAnnotation));
        }
        return limiterOperations;
    }

    private static Collection<LimiterOperation<RateLimiter>> parserRateLimiterAnnotation(AnnotatedElement annotatedElement, HRateLimiter hRateLimiter) {
        Collection<LimiterOperation<RateLimiter>> limiterOperations = new ArrayList<>();
        for (int i = 0; i < hRateLimiter.keys().length; i++) {
            RateLimiterOperation.Builder builder = new RateLimiterOperation.Builder();
            builder.name(annotatedElement.toString());
            builder.limiterName(hRateLimiter.limiterName());
            builder.limiterManager(hRateLimiter.limiterManager());
            builder.condition(hRateLimiter.condition());
            builder.key(hRateLimiter.keys()[i]);
            builder.keyGenerator(hRateLimiter.keyGenerator());
            String[] argInjects = hRateLimiter.argInjects();
            if (ArrayUtils.isNotEmpty(argInjects)) {
                builder.argumentInjects(new ArrayList<>(Arrays.asList(argInjects)));
            }
            builder.fallBackResolver(hRateLimiter.fallBackResolver());
            builder.setQps(hRateLimiter.qps());
            limiterOperations.add(builder.build());
        }
        return limiterOperations;
    }

}
