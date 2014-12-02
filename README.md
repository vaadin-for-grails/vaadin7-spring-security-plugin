grails-vaadin-spring-security-plugin
====================================

Plugin for integration of Spring Security and Vaadin in Grails.

This plugin provides a simple but extensible solution for integrating Spring Security with Vaadin into Grails.

## Setup
Add the follwing lines to your BuildConfig.groovy.
```
compile ":vaadin-core:2.1"
compile ":vaadin-spring-security:2.1"
```

## Usage
Extend your mappings in the ```VaadinConfig.groovy``` file, by adding the ```access``` property to UI and View mappings.
```
mappins {
  "/vaadin" {
    ui = "default"
    access = ["ROLE_ADMIN"]
  }
}
```

Additionally, if you're using the pessimistic lockdown method (default), then you need to permit access to your Vaadin UI by adding ```"/vaadin/**": ["permitAll"]``` to ```Config.groovy```.


