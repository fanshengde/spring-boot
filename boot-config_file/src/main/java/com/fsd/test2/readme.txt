@Profile("dev")表明只有Spring定义的Profile为dev时才会实例化DevEmailService这个类。
在配置文件中指定

 

在application.properties中加入：

spring.profiles.active=dev



在Spring Boot中多环境配置文件名需要满足application-{profile}.properties的格式，其中{profile}对应你的环境标识，比如：
   application-dev.properties：开发环境

   application-test.properties：测试环境

   application-prod.properties：生产环境