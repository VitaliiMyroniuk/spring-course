package beans.configuration;

import beans.converters.impl.LocalDateTimeToStringConverter;
import beans.converters.impl.LocalDateToStringConverter;
import beans.converters.impl.StringToLocalDateConverter;
import beans.converters.impl.StringToLocalDateTimeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class AppConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ConversionService conversionService() {
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new LocalDateToStringConverter());
        service.addConverter(new StringToLocalDateConverter());
        service.addConverter(new LocalDateTimeToStringConverter());
        service.addConverter(new StringToLocalDateTimeConverter());
        return service;
    }

}
