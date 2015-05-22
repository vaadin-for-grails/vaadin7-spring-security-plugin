import grails.plugin.springsecurity.ReflectionUtils
import grails.plugin.springsecurity.SecurityConfigType
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.util.Holders
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.vaadin.grails.security.server.SecurityAwareUriMappings
import org.vaadin.grails.security.server.GrailsAwareUIProvider
import org.vaadin.grails.server.UriMappings

/**
 * Vaadin 7 Spring Security Plugin.
 *
 * @author Stephan Grundner
 */
class Vaadin7SpringSecurityGrailsPlugin {

    def version = "2.1"
    def grailsVersion = "2.4 > *"

    def group = "com.github.vaadin-for-grails"
//    def dependsOn = ["vaadin7": "2.0 > *"]
    def loadAfter = ["vaadin7", "spring-security-core"]

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

    def applyDefaultSecurityRules = { GrailsApplication application ->
        def securityConfig = SpringSecurityUtils.securityConfig
        def type = securityConfig.securityConfigType

        Map map = null
        if (type == SecurityConfigType.InterceptUrlMap) {
            map = securityConfig.interceptUrlMap
        } else if (type == SecurityConfigType.Requestmap) {
//            TODO Implement default rules for Requestmap config type
            log.warn("Unsupported value ${SecurityConfigType.Requestmap} for option [grails.plugin.springsecurity.securityConfigType]")
        } else {
            map = securityConfig.controllerAnnotations.staticRules
        }

        if (map) {
            def mappingsConfig = application.config.vaadin.mappings
            mappingsConfig.each { String path, ConfigObject pathConfig ->
                def key = "${path}/**"
                if (!map.containsKey(key)) {
                    map += [(key): ['permitAll']]
                }
            }

            if (type == SecurityConfigType.InterceptUrlMap) {
                map = securityConfig.interceptUrlMap = map
            } else {
                map = securityConfig.controllerAnnotations.staticRules = map
            }

            SpringSecurityUtils.setSecurityConfig(securityConfig)
            ReflectionUtils.setSecurityConfig(securityConfig)
            SpringSecurityUtils.reloadSecurityConfig()
        }
    }

    def doWithSpring = {
        "uiProvider"(GrailsAwareUIProvider) { bean ->
            bean.scope = "prototype"
            bean.autowire = "byName"
        }
        "uriMappings"(SecurityAwareUriMappings)
        applyDefaultSecurityRules(application)
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
