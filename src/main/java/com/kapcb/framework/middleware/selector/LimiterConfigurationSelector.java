package com.kapcb.framework.middleware.selector;

import com.kapcb.framework.middleware.annotation.EnableLimiter;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;

/**
 * <a>Title: LimiterConfigurationSelector </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterConfigurationSelector <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/11/29 22:22
 * @since 1.0
 */
public class LimiterConfigurationSelector extends AdviceModeImportSelector<EnableLimiter> {

    @Override
    protected String[] selectImports(AdviceMode adviceMode) {
        return new String[0];
    }


}
