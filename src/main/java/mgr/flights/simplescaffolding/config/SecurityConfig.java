package mgr.flights.simplescaffolding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
//@EnableResourceServer
public class SecurityConfig extends WebSecurityConfigurerAdapter {//extends ResourceServerConfigurerAdapter {
//    @Value("${security.oauth2.resource.id}")
//    private String resourceId;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .authorizeRequests()
//                .mvcMatchers("/api/passengers").authenticated()
                .anyRequest().permitAll();
    }

//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.resourceId(resourceId);
//    }
}
