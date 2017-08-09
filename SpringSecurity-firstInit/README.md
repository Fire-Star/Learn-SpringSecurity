# Learn-SpringSecurity
_学习SpringSecurity时，写的小案例。_

### 已达目标：

现在配置了SpringSecurity的基本东西，通过命名空间的方式。
现在开始SpringSecurity教学。

## Maven导包
通过Maven方式导入SpringSecurity的包。


```

<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    
    <!-- SpringSecurity版本号 -->
    <springSecurity.version>4.2.3.RELEASE</springSecurity.version>
  </properties>
<dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-core</artifactId>
      <version>${springSecurity.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-web</artifactId>
      <version>${springSecurity.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-config</artifactId>
      <version>${springSecurity.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-taglibs</artifactId>
      <version>${springSecurity.version}</version>
    </dependency>
<dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-web</artifactId>
      <version>${springSecurity.version}</version>
</dependency>
```
## 在web.xml中引入SpringSecurity

```
    <!-- SpringSecurity 核心过滤器配置 -->
    <filter>
        <filter-name>springSecurityFilterChain</filter-name>
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>springSecurityFilterChain</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```

但是在之前，你必须还要在web.xml中配置Spring

```
    <!-- Spring的配置文件 -->
    <context-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:Spring/application*.xml</param-value>
    </context-param>
    <!-- Spring的web整合方式 -->
    <listener>
      <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
```

SpringSecurity的命名空间配置可以写入Spring的配置文件中，所以请看：在applicationContext.xml中配置SpringSecurity。

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:security="http://www.springframework.org/schema/security"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/security
       http://www.springframework.org/schema/security/spring-security.xsd">

    <!-- 配置不过滤的资源（静态资源及登录相关） -->
    <security:http pattern="/**/*.css" security="none"></security:http>
    <security:http pattern="/**/*.jpg" security="none"></security:http>
    <security:http pattern="/**/*.jpeg" security="none"></security:http>
    <security:http pattern="/**/*.gif" security="none"></security:http>
    <security:http pattern="/**/*.png" security="none"></security:http>
    <security:http pattern="/**/*.js" security="none"></security:http>

    <security:http pattern="/login.jsp" security="none"></security:http>
    <security:http pattern="/index.jsp" security="none"></security:http>
    <security:http pattern="/getCode" security="none" /><!-- 不过滤验证码 -->
    <security:http pattern="/test/**" security="none"></security:http><!-- 不过滤测试内容 -->

    <!-- 配置资源权限信息 -->
    <security:http auto-config="true" use-expressions="false">
        <security:intercept-url pattern="/admin.jsp" access="ROLE_MoonFollow_Admin"/>
        <security:intercept-url pattern="/**" access="ROLE_MoonFollow,ROLE_MoonFollow_Admin"/>

        <!-- 配置登出 -->
        <!-- 有时候，你会发现，就算重启了 Tomcat ，session 也不会过期，那么你需要配置退出时，session 过期。 -->
        <security:logout logout-url="/logoutSecurity" invalidate-session="true" delete-cookies="JSESSIONID"/>
        <!-- 在配置登出时，如果不把 csrf 设置为 true 的话，那么登出时的链接将会发生 404 错误。 -->
        <security:csrf disabled="true"/>
    </security:http>

    <!-- 配置用户角色信息 -->
    <security:authentication-manager>
        <security:authentication-provider>
            <security:user-service >
                <security:user name="MoonFollow" password="123" authorities="ROLE_MoonFollow"/>
                <security:user name="Admin" password="123" authorities="ROLE_MoonFollow_Admin"/>
            </security:user-service>
        </security:authentication-provider>
    </security:authentication-manager>
</beans>
```

解释：

```
<security:http pattern="" security="none"></security:http>
```
配置SpringSecurity的不过滤规则，对xxx文件不过滤。

```
<security:intercept-url pattern="/admin.jsp" access="ROLE_MoonFollow_Admin"/>
```
配置资源对应访问需要的权限信息

```
<security:user name="MoonFollow" password="123" authorities="ROLE_MoonFollow"/>
```
配置用户 对应 网站系统中的角色或者说是用户对应的权限。


注意：

```
<security:intercept-url pattern="/admin.jsp" access="ROLE_MoonFollow_Admin"/>
<security:intercept-url pattern="/**" access="ROLE_MoonFollow,ROLE_MoonFollow_Admin"/>
```

下面这个/**的配置要放在最后面，不然如果放在最前面会让其它的配置如: /admin.jsp失效的。
```
<security:intercept-url pattern="/**" access="ROLE_MoonFollow,ROLE_MoonFollow_Admin"/>
```

<<<<<<< HEAD
=======

>>>>>>> 5498c3413dc1f60acc7188480f33ddb96afb3fb6
最后你只需要运行我的GitHub项目就能够看到效果了。

>>>>>>> b5a9e378bcb99c81e4eb3702158b01f24107eaec
>>>>>>> fa9024e0962e09d57d0415c44a469163cb079805
=======
>>>>>>> 5f071671f8cbb230bd26cdc88b9ab3c64b981127
