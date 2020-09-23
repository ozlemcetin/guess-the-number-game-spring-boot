package academy.learnprogramming.config;

import academy.learnprogramming.interceptor.RequestHandlerInterceptorImpl;
import academy.learnprogramming.util.MappingNames;
import academy.learnprogramming.util.ViewNames;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    // ==bean methods ==
    @Bean
    public LocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(MappingNames.HOME_MAPPING).setViewName(ViewNames.HOME_VIEW);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //RequestHandlerInterceptorImpl
        registry.addInterceptor(new RequestHandlerInterceptorImpl());

        /*
            LocaleResolver is an interface that
            allows locale resolution based on request, cookies etc.

            LocaleChangeInterceptor is a built-in interceptor that
            allows for changing the current locale every request
         */
        {

            /*
                the default parameter name is locale,
                we customize it by calling setParamName
             */
            LocaleChangeInterceptor interceptor
                    = new LocaleChangeInterceptor();

            //lang
            // interceptor.setParamName("lang");
            // registry.addInterceptor(interceptor);

            //locale
            registry.addInterceptor(interceptor);
        }


    }
}
