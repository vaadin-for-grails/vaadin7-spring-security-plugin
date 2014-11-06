class VaadinSpringSecurityGrailsPlugin {

    def version = "0.9"
    def dependsOn = [vaadin: "* > 7.3.0"]
    def loadAfter = ['vaadin']
    def grailsVersion = "2.4 > *"
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def title = "Spring Security for Vaadin 7 Plugin"
    def author = "Stephan Grundner"
    def authorEmail = "stephan.grundner@gmail.com"
    def description = '''\
Grails plugin providing spring security features for Vaadin 7 plugin.
'''

    def documentation = "http://grails.org/plugin/grails-vaadin-spring-security-plugin"
    def license = "APACHE"
    def organization = [ name: "Stephan Grundner", url: "https://www.facebook.com/stephangrundner" ]
    def developers = [ [ name: "Stephan Grundner", email: "stephan.grundner@gmail.com" ]]
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]
    def scm = [ url: "https://github.com/stephangrundner/grails-vaadin-spring-security-plugin" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {

        viewProvider(com.vaadin.grails.security.navigator.SecuredViewProvider) { bean ->
            bean.scope = "prototype"
        }
    }
}
