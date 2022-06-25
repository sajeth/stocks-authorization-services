module stocks.authorization.services {
    requires transitive stocks.commons;
    requires spring.security.config;
    requires spring.web;
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    requires spring.security.web;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires jjwt;
    requires spring.security.core;
    requires java.validation;
    requires org.apache.tomcat.embed.core;
    exports com.saji.stocks.authorization.pojo;
    opens com.saji.stocks.authorization to spring.core,spring.beans,spring.context;
    opens com.saji.stocks.authorization.config to spring.core;
    exports com.saji.stocks.authorization.config to spring.beans, spring.context,spring.boot;
    opens com.saji.stocks.authorization.controller to spring.core;
    exports com.saji.stocks.authorization.controller to spring.beans, spring.context,spring.web;
}