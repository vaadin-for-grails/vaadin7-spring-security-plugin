grails-vaadin-spring-security-plugin
====================================

Plugin for integration of Spring Security and Vaadin in Grails.

# Setup
Requires installed and configured `vaadin` plugin version >= 7.3.3.1: http://grails.org/plugin/vaadin!
Install `spring-security-core` plugin as described: http://grails.org/plugin/spring-security-core.
Checkout the Grails Projekt and run `maven-install`. 

Add the following to your `BuildConfig.groovy`
```
plugins {
    compile ":vaadin-spring-security:0.9"
}
```
# Usage

Secure your views by annotating them with `@Secured` from the package `org.springframework.security.access.annotation`.

```
@Secured("ROLE_ADMIN")
@VaadinView(path = "...")
class MyView extends CustomComponent implements View {
  ...
}
```
If you're not authenticated, you will get an "Login" Screen.
If you are authenticated but not authorized, you'll get an "Not authorized" screen.

Both screens are implemented as simple Vaadin Views and available as Spring Bean components.
If you'd like to customize them, override them in the `resources.groovy`.

```
loginView(MyLoginView) { bean ->
    bean.scope = "prototype"
}
```