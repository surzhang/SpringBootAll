对于SpringBoot，有没有什么办法可以直接定义好需要导出的数据对象，然后添加几个注解，直接自动实现Excel导入导出功能？EasyPoi正是这么一款工具，如果你不太熟悉POI，想简单地实现Excel操作，用它就对了！EasyPoi的目标不是替代POI，而是让一个不懂导入导出的人也能快速使用POI完成Excel的各种操作，而不是看很多API才可以完成这样的工作。

#### 1.集成

> 在SpringBoot中集成EasyPoi非常简单，只需添加如下一个依赖即可，真正的开箱即用！

```xml
<dependency>  
<groupId>cn.afterturn</groupId>  
<artifactId>easypoi-spring-boot-starter</artifactId>   <version>4.4.0</version>
</dependency>
```
#### 2.使用
>接下来介绍下EasyPoi的使用，以会员信息和订单信息的导入导出为例，分别实现下简单的单表导出和具有关联信息的复杂导出。

[详细分享](https://shimo.im/docs/PpJKjWgVYcwGP3h6/ )

