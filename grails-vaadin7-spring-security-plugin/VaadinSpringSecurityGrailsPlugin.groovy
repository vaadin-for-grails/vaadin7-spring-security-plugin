class VaadinSpringSecurityGrailsPlugin {
    def version = "2.0"
    def grailsVersion = "2.4 > *"
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]
    def loadAfter = ["vaadin-core"]

    def title = "Grails Vaadin7 Spring Security Plugin Plugin" // Headline display name of the plugin
    def author = "Your name"
    def authorEmail = ""
    def description = '''\
Brief summary/description of the plugin.
'''
    def documentation = "http://grails.org/plugin/grails-vaadin7-spring-security-plugin"

    def doWithSpring = {
        "uiProvider"(com.vaadin.grails.server.SecuredMappingsAwareUIProvider) { bean ->
            bean.scope = "prototype"
            bean.autowire = "byName"
        }
        "mappingsProvider" (com.vaadin.grails.server.SecurityMappingsProvider)
    }
}
