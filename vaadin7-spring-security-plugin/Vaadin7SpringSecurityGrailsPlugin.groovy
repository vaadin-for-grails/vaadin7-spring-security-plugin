import grails.util.Holders
import org.vaadin.grails.security.navigator.UriMappingsAwareViewProvider
import org.vaadin.grails.security.server.SecurityAwareUriMappings
import org.vaadin.grails.security.server.UriMappingsAwareUIProvider
import org.vaadin.grails.security.ui.DefaultNotAuthorizedComponent
import org.vaadin.grails.server.UriMappings

/**
 * Vaadin 7 Spring Security Plugin.
 *
 * @author Stephan Grundner
 */
class Vaadin7SpringSecurityGrailsPlugin {

    def version = "2.0-SNAPSHOT"
    def grailsVersion = "2.4 > *"

    def group = "com.github.vaadin-for-grails"
    def dependsOn = ["vaadin7": "2.0-SNAPSHOT"]
    def loadAfter = ["vaadin7"]

    def title = "Vaadin 7 Spring Security Plugin"
    def author = "Stephan Grundner"
    def authorEmail = "stephan.grundner@gmail.com"
    def description = '''\
Plugin for integrating Spring Security with Vaadin 7 into Grails.
'''
    def documentation = "https://github.com/vaadin-for-grails/vaadin7-spring-security-plugin"

    def license = "APACHE"
    def organization = [ name: "Vaadin for Grails", url: "https://github.com/vaadin-for-grails" ]
    def developers = [ [ name: "Stephan Grundner", email: "stephan.grundner@gmail.com" ]]

    def scm = [ url: "https://github.com/vaadin-for-grails/vaadin7-spring-security-plugin" ]

    def doWithSpring = {
        "uiProvider"(UriMappingsAwareUIProvider) { bean ->
            bean.scope = "prototype"
            bean.autowire = "byName"
        }
        "viewProvider"(UriMappingsAwareViewProvider) { bean ->
            bean.scope = "prototype"
            bean.autowire = "byName"
        }
    }

    def doWithApplicationContext = { ctx ->
        def uriMappings = ctx.getBean(UriMappings)

        def mappingsConfig = Holders.config.vaadin.mappings
        mappingsConfig.each { String path, ConfigObject pathConfig ->

            uriMappings.putPathProperty(path,
                    SecurityAwareUriMappings.ACCESS_PROPERTY,
                    pathConfig.get(SecurityAwareUriMappings.ACCESS_PROPERTY))

            def fragments = pathConfig.fragments
            fragments.each { String fragment, ConfigObject fragmentConfig ->

                uriMappings.putFragmentProperty(path, fragment,
                        SecurityAwareUriMappings.ACCESS_PROPERTY,
                        fragmentConfig.get(SecurityAwareUriMappings.ACCESS_PROPERTY))

            }
        }
    }
}
