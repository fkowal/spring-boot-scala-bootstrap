package com.github.fkowal.infrastructure.swagger

import com.fasterxml.classmate.TypeResolver
import com.github.fkowal.ScalaDemoApplication
import org.springframework.context.annotation.{Bean, Configuration}
import springfox.documentation.builders.{PathSelectors, RequestHandlerSelectors}
import springfox.documentation.schema.{AlternateTypeRule, WildcardType}
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

  @Bean
  def swagger(typeResolver: TypeResolver): Docket = {

    val seqRule = new AlternateTypeRule(
      typeResolver.resolve(classOf[scala.collection.Seq[_]], classOf[WildcardType]), // From type
      typeResolver.resolve(classOf[java.util.List[_]], classOf[WildcardType]))

    val listRule = new AlternateTypeRule(
      typeResolver.resolve(classOf[scala.collection.immutable.List[_]], classOf[WildcardType]), // From type
      typeResolver.resolve(classOf[java.util.List[_]], classOf[WildcardType]))

    val mapRule = new AlternateTypeRule(
      typeResolver.resolve(classOf[scala.collection.Map[_, _]], classOf[WildcardType], classOf[WildcardType]), // From type
      typeResolver.resolve(classOf[java.util.Map[_, _]], classOf[WildcardType], classOf[WildcardType])) // To type

    val immMapRule = new AlternateTypeRule(
      typeResolver.resolve(classOf[scala.collection.immutable.Map[_, _]], classOf[WildcardType], classOf[WildcardType]), // From type
      typeResolver.resolve(classOf[java.util.Map[_, _]], classOf[WildcardType], classOf[WildcardType])) // To type

    val immSeqRule = new AlternateTypeRule(
      typeResolver.resolve(classOf[scala.collection.immutable.Seq[_]], classOf[WildcardType]), // From type
      typeResolver.resolve(classOf[java.util.List[_]], classOf[WildcardType]))

    val rules = Array(seqRule, listRule, mapRule, immMapRule, immSeqRule)

    new Docket(DocumentationType.SWAGGER_2)
      .alternateTypeRules(rules: _*)
      .select()
      .apis(RequestHandlerSelectors.basePackage(classOf[ScalaDemoApplication].getPackage.getName))
      .paths(PathSelectors.any())
      .build()
  }

}
