package org.vaadin.grails.security.navigator

import com.vaadin.navigator.View
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils
import org.vaadin.grails.navigator.Navigation
import org.vaadin.grails.security.ui.LoginView
import org.vaadin.grails.security.ui.NotAuthorizedView
import org.vaadin.grails.security.util.SecurityUtils
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
class UriMappingsAwareViewProvider extends org.vaadin.grails.navigator.UriMappingsAwareViewProvider  {

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
        def path = Navigation.current.path

        if (fragment == "") {
            fragment = getDefaultFragment(path)
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
}
