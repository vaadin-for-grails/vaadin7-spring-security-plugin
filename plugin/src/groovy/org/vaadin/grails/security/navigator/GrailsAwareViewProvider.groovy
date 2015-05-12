package org.vaadin.grails.security.navigator

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.util.Holders
import org.vaadin.grails.navigator.Navigation
import org.vaadin.grails.security.ui.LoginComponent
import org.vaadin.grails.security.ui.LoginView
import org.vaadin.grails.security.ui.LoginWindow
import org.vaadin.grails.security.ui.NotAuthorizedView
import org.vaadin.grails.security.util.SecurityUtils
import org.vaadin.grails.server.UriMappingUtils
import org.vaadin.grails.server.UriMappings
import org.vaadin.grails.util.ApplicationContextUtils

/**
 * Checks <code>UriMappings</code> for <code>access</code> properties.
 * <p>
 *     If there is an <code>access</code> property and the current user is not logged in,
 *     a {@link org.vaadin.grails.security.ui.LoginComponent} is shown.
 * </p>
 * <p>
 *     If there is an <code>access</code> property and the current user is not granted,
 *     a {@link org.vaadin.grails.security.ui.NotAuthorizedComponent} is shown.
 * </p>
 *
 * @author Stephan Grundner
 */
class GrailsAwareViewProvider extends org.vaadin.grails.navigator.GrailsAwareViewProvider implements ViewChangeListener  {

    Class<? extends View> getLoginViewClass() {
        LoginView
    }

    Class<? extends View> getNotAuthorizedViewClass() {
        NotAuthorizedView
    }

    protected Collection<String> getRequiredRoles(String path, String fragment) {
        def accessProperties = SecurityUtils.getAccessPropertiesForFragment(path, fragment)
        accessProperties.removeAll { it == 'permitAll' }
        accessProperties
    }

    protected boolean accessGranted(Collection<String> roles) {
        SpringSecurityUtils.ifAnyGranted(roles.join(","))
    }

    @Override
    View getView(String fragment) {
        def path = Navigation.currentPath

        if (fragment == "") {
            def uriMappings = UriMappingUtils.uriMappings
            fragment = uriMappings.getPathProperty(path,
                    UriMappings.DEFAULT_FRAGMENT_PATH_PROPERTY)
        }

        def requiredRoles = getRequiredRoles(path, fragment)
        if (requiredRoles.size() > 0) {
            def securityService = ApplicationContextUtils.applicationContext.getBean(SpringSecurityService)
            if (!securityService.isLoggedIn()) {
                return ApplicationContextUtils.getBeanOrInstance(loginViewClass)
            } else if (!accessGranted(requiredRoles)) {
                return ApplicationContextUtils.getBeanOrInstance(notAuthorizedViewClass)
            } else {
//                access granted!
            }
        }

        super.getView(fragment)
    }

    @Override
    boolean beforeViewChange(ViewChangeListener.ViewChangeEvent event) {
        def omitLoginWindow = Holders.config.vaadin.omitLoginWindow
        if (omitLoginWindow || event.oldView == null) {
            return true
        }

        if (loginViewClass.isAssignableFrom(event.newView?.getClass())) {

            final loginWindow = new LoginWindow()
            loginWindow.loginComponent.addLoginListener(new LoginComponent.LoginListener() {
                @Override
                void loginSucceeded(LoginComponent.LoginEvent loginEvent) {
                    loginWindow.close()
                    def params = Navigation.fromParamsString(event.parameters)
                    Navigation.navigateTo(fragment: event.viewName, params: params)
                }

                @Override
                void loginFailed(LoginComponent.LoginEvent loginEvent) { }
            })
            loginWindow.open()

            return false
        }

        true
    }

    @Override
    void afterViewChange(ViewChangeListener.ViewChangeEvent event) { }
}
