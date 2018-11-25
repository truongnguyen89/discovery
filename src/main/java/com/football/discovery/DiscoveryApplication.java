package com.football.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;

/**
 * Created by IntelliJ IDEA.
 * User: Truong Nguyen
 * Date: 16-May-18
 * Time: 11:04 AM
 * To change this template use File | Settings | File Templates.
 */
@EnableEurekaServer
@SpringBootApplication
public class DiscoveryApplication {

    //    public static void main(String[] args) {
//        SpringApplication.run(DiscoveryApplication.class, args);
//    }
    private static final Logger LOGGER = LogManager.getLogger(DiscoveryApplication.class);

    public static void main(String[] args) {
        long id = System.currentTimeMillis();
        LOGGER.info("[B][" + id + "] >>>>>>>>>>>>>>>>>>>>>>>>>> Start DiscoveryApplication ...");
        SpringApplication app = new SpringApplication(DiscoveryApplication.class);

        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String ipServer = "localhost";
        try {
            ipServer = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            ipServer = env.getProperty("server.address") != null ? env.getProperty("server.address") : "localhost";
        }
        LOGGER.info("----------------------------------------------------------");
        LOGGER.info("   Application         : " + env.getProperty("spring.application.name"));
        LOGGER.info("   Url                 : " + protocol + "://" + ipServer + ":" + env.getProperty("server.port"));
        LOGGER.info("   Profile(s)          : " + env.getActiveProfiles()[0]);
        LOGGER.info("----------------------------------------------------------");

        LOGGER.info("[E][" + id + "][Duration = " + (System.currentTimeMillis() - id) + "] >>>>>>>>>>>>>>>>>>>>>>>>>> End Start DiscoveryApplication ...");
    }
}
