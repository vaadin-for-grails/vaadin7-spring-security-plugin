class VaadinSpringSecurityGrailsPlugin {

    def version = "1.0-SNAPSHOT"
    def grailsVersion = "2.4 > *"

    def group = "com.github.vaadin-for-grails"
    def dependsOn = ["vaadin-core": "1.0-SNAPSHOT"]
    def loadAfter = ["vaadin-core"]

    def title = "Vaadin Spring Security Plugin"
    def author = "Stephan Grundner"
    def authorEmail = "stephan.grundner@gmail.com"
    def description = '''\
Plugin for integrating Spring Security with Vaadin into Grails.
'''
    def documentation = "https://github.com/vaadin-for-grails/grails-vaadin-spring-security-plugin"

    def license = "APACHE"
    def organization = [ name: "Vaadin for Grails", url: "https://github.com/vaadin-for-grails" ]
    def developers = [ [ name: "Stephan Grundner", email: "stephan.grundner@gmail.com" ]]

    def scm = [ url: "https://github.com/vaadin-for-grails/grails-vaadin-spring-security-plugin" ]

    def doWithSpring = {
        "uiProvider"(com.vaadin.grails.server.SecuredUriMappingsAwareUIProvider) { bean ->
            bean.scope = "prototype"
            bean.autowire = "byName"
        }
        "viewProvider"(com.vaadin.grails.navigator.SecuredUriMappingsAwareViewProvider) { bean ->
            bean.scope = "prototype"
            bean.autowire = "byName"
        }
        "uriMappingsHolder" (com.vaadin.grails.server.SecurityAwareUriMappingsHolder)
    }
}
