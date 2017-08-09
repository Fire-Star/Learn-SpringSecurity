# Learn-SpringSecurity
_学习SpringSecurity时，写的小案例。_

### 已达目标：

现在配置了SpringSecurity的基本东西，通过命名空间的方式。
现在开始SpringSecurity教学。

## Maven导包
通过Maven方式导入SpringSecurity的包。

代码：

`
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

`
