package mx.com.backend.asesores;

import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.EndpointLinksResolver;
import org.springframework.boot.actuate.endpoint.web.EndpointMapping;
import org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes;
import org.springframework.boot.actuate.endpoint.web.ExposableWebEndpoint;
import org.springframework.boot.actuate.endpoint.web.WebEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Configuration class which enables Swagger h.
 */
@EnableSwagger2
@Component
@Configuration
public class SwaggerConfiguration implements WebMvcConfigurer {

  /**
   * Adds the resource handlers.
   *
   * @param registry the registry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {

    registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  /**
   * Api.
   *
   * @return the docket
   */
  @Bean
  Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any()).build().apiInfo(apiInfo());
  }

  /**
   * Api info.
   *
   * @return the api info
   */
  private ApiInfo apiInfo() {
    ApiInfo apiInfo = new ApiInfo("Application REST API", "Application manager REST API documentation.", "API 1.0",
        "Terms of service based into company terms of use", new Contact("yourCompany", null, "test@test.com"),
        "License of API for YourCompany use only", null, Collections.emptyList());
    return apiInfo;
  }

  /**
   * Web endpoint servlet handler mapping.
   *
   * @param webEndpointsSupplier        the web endpoints supplier
   * @param servletEndpointsSupplier    the servlet endpoints supplier
   * @param controllerEndpointsSupplier the controller endpoints supplier
   * @param endpointMediaTypes          the endpoint media types
   * @param corsProperties              the cors properties
   * @param webEndpointProperties       the web endpoint properties
   * @param environment                 the environment
   * @return the web mvc endpoint handler mapping
   */
  @Bean
  WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier,
      ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier,
      EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties,
      WebEndpointProperties webEndpointProperties, Environment environment) {
    List<ExposableEndpoint<?>> allEndpoints = new ArrayList<>();
    Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
    allEndpoints.addAll(webEndpoints);
    allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
    allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
    String basePath = webEndpointProperties.getBasePath();
    EndpointMapping endpointMapping = new EndpointMapping(basePath);
    boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
    return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes,
        corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath),
        shouldRegisterLinksMapping, null);
  }

  /**
   * Should register links mapping.
   *
   * @param webEndpointProperties the web endpoint properties
   * @param environment           the environment
   * @param basePath              the base path
   * @return true, if successful
   */
  private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment,
      String basePath) {
    return webEndpointProperties.getDiscovery().isEnabled()
        && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
  }

}
