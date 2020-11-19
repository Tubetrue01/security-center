# auth-starter

**提供给需要安全校验的项目使用**

## 使用方法：

1、 导入依赖
```xml
  <dependency>
     <groupId>org.tubetrue01</groupId>
     <artifactId>auth-starter</artifactId>
  </dependency>
```
2、 开启 Redis（需要根据认证服务端进行配置）
```yaml
引入 common-dev 或者 common-local 即可；
```
3、是否开启一级缓存（默认为false）
```yaml
auth:
  client:
    cache: false
```
4、白名单配置  
目前白名单支持四种种方式的配置：
- k8s （该白名单适用于内网接口的访问，如：监控端点；该白名单的域名无需登录、无需 client-id 即可访问）
- login-free （该白名单下的域名虽然可以无 token 检测，但是需要携带 client-id，防止第三方刷接口，支持 Ant 风格通配符 ）
- third-part （开放给第三方服务【非系统内部调用】的接口白名单，此白名单可以无限制访问，注意使用场景,支持 Ant 风格通配符）
- inner-invoke（内网调用白名单，限制只能内网访问,支持 Ant 风格通配符）

注意：目前 login-free、third-part、third-part 开发给使用用者定义，k8s 由配置文件引入自动生效，且无法修改；

使用方式：

```yaml

auth:
  white-list:
      login-free:
        - ${server.servlet.context-path}/sms/code
        - ${server.servlet.context-path}/wechat/operator/phone/verify
        - ${server.servlet.context-path}/wechat/operator/password
      third-path:
        - ${server.servlet.context-path}/xxx
      inner-invoke:
        - ${server.servlet.context-path}/xxx

```


**注意：当导入依赖之后，安全校验便会自动生效，控制层的所有调用都将进行拦截，此时系统必须进行登录方可访问；同时相关的接口信息必须经过【用户中心】进行配置。**

