Vaadin Spring Security Plugin for Grails
====================================

Plugin for integration of Spring Security and Vaadin in Grails.

This plugin provides a simple but extensible solution for integrating Spring Security with Vaadin into Grails.

## Setup
Add the follwing lines to your BuildConfig.groovy.

    repositories {
        mavenRepo "https://oss.sonatype.org/content/groups/public"
    }
    
    plugins {
        compile "com.github.vaadin-for-grails:vaadin-spring-security:2.1-SNAPSHOT"
    }


## Usage
Extend your mappings in the `VaadinConfig.groovy` file, by adding the `access` property to UI and View mappings.

    mappins {
      "/vaadin" {
        ui = "default"
        access = ["ROLE_ADMIN"]
      }
    }


Additionally, if you're using the pessimistic lockdown method (default), then you need to permit access to your Vaadin UI by adding `"/vaadin/**": ["permitAll"]` to `Config.groovy`.


