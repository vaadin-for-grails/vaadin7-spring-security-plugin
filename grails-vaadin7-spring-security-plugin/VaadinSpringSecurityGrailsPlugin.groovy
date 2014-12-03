class VaadinSpringSecurityGrailsPlugin {

    def version = "2.1"
    def grailsVersion = "2.4 > *"

    def dependsOn = ["vaadin-core": "2.1-SNAPSHOT"]
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
        "uriMappingsHolder" (com.vaadin.grails.server.SecurityAwareUriMappingsHolder)
    }
}
