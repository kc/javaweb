package com.example;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.nio.file.Path;

public class Runner {

    // Deploys forumwebapp to http://localhost:8082/forum

    // Hot deploy of classes DOES NOT WORK!
    // You need to restart the runner for testing your changes.

    public static void main(String[] args) throws LifecycleException {
        String projectRoot = "forumparent/forumwebapp/";
        String pathToClasses = Path.of(projectRoot + "target/classes").toAbsolutePath().toString();
        String pathToResources = Path.of(projectRoot + "src/main/webapp/").toAbsolutePath().toString();
        String pathToLib = Path.of(projectRoot + "src/main/webapp/WEB-INF/lib").toAbsolutePath().toString();
        String contextRoot = "/";
        String webInfClasses = "/WEB-INF/classes";
        String internalPath = "/";

        String username = "bram";
        String password = "bram";
        String roleUser = "user";
        String roleAdmin = "admin";

        System.out.println("Configuring app with basedir: " + Path.of(projectRoot).toAbsolutePath());

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8082);
        tomcat.getConnector();
        // Open a port
        // Connector connector = new Connector();
        // connector.setPort(8080);
        // tomcat.getService().addConnector(connector);

        // Add some roles and users for testing the secured part of the app:
        tomcat.addRole(username, roleUser);
        tomcat.addRole(username, roleAdmin);
        tomcat.addUser(username, password);

        // Deploy the web application to Tomcat
        var webapp = tomcat.addWebapp(contextRoot, pathToResources);
        // ... and map the path to target/classes to the webapp's internal WEB-INF/classes
        var webappRoot = new StandardRoot(webapp);
        webappRoot.addJarResources(new DirResourceSet(webappRoot, webInfClasses, pathToClasses, internalPath));
        webappRoot.addJarResources(new DirResourceSet(webappRoot, webInfClasses, pathToLib, internalPath));
        webapp.setResources(webappRoot);

        // Start tomcat and wait until it is finished.
        tomcat.start();
        tomcat.getServer().await();
    }
}
