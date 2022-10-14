package com.metaxiii.fr.goodapi.config;

import com.metaxiii.fr.goodapi.transformer.EmployeeTransformerPlugin;
import org.springframework.context.annotation.Configuration;
import org.springframework.plugin.core.config.EnablePluginRegistries;

@Configuration(proxyBeanMethods = false)
@EnablePluginRegistries({
        EmployeeTransformerPlugin.class
})
public class GoodAPIConfiguration {
}
