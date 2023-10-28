FROM maven:3.8.7-amazoncorretto-11 AS builder
 #  AS builder 起别名

RUN mkdir /build
# 创建临时文件

ADD src /build/src
#将 src目录复制到临时目录

ADD pom.xml /build
# 将 pom文件复制到临时目录

RUN cd /build && mvn -B -ntp package
# 打包

FROM openjdk:11-jre
# 获取jre

COPY --from=builder /build/target/fakerank-0.0.1-SNAPSHOT.jar /fakerank.jar
#从标记点 拷贝jar包 并改名

CMD ["java", "-jar", "/fakerank.jar"]

# 声明运行方式