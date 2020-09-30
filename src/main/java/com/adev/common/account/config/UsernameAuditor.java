package com.adev.common.account.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Configuration
public class UsernameAuditor implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        String username=servletRequestAttributes.getRequest().getHeader("username");
        if(StringUtils.isNotBlank(username)){
            return Optional.of(username);
        }
        return Optional.empty();
    }
}
