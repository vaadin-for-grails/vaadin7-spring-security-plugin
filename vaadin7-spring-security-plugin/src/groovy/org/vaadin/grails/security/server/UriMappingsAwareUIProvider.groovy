package org.vaadin.grails.security.server

import com.vaadin.navigator.ViewProvider
import com.vaadin.server.UIClassSelectionEvent
import com.vaadin.ui.UI
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils
import org.vaadin.grails.security.navigator.UriMappingsAwareViewProvider
import org.vaadin.grails.security.server.SecurityAwareUriMappings
import org.vaadin.grails.security.ui.LoginUI
import org.vaadin.grails.security.ui.NotAuthorizedUI
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
class UriMappingsAwareUIProvider extends org.vaadin.grails.server.UriMappingsAwareUIProvider {

    Class<? extends UI> getLoginUIClass() {
        LoginUI
    }

    Class<? extends UI> getNotAuthorizedUIClass() {
        NotAuthorizedUI
    }

    protected Collection<String> getRequiredRoles(String path) {
        def accessProperties = SecurityUtils.getAccessPropertiesForPath(path)
        accessProperties.removeAll { it == 'permitAll' }
        accessProperties
    }

    protected boolean accessGranted(Collection<String> roles) {
        SpringSecurityUtils.ifAnyGranted(roles.join(","))
    }

    @Override
    Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
        def path = pathHelper.getPathWithinApplication(event.request)
        def requiredRoles = getRequiredRoles(path)
        if (requiredRoles?.size() > 0) {
            def securityService = ApplicationContextUtils.applicationContext.getBean(SpringSecurityService)
            if (!securityService.isLoggedIn()) {
                return loginUIClass
            } else if (!accessGranted(requiredRoles)) {
                return notAuthorizedUIClass
            } else {
//                granted!
            }
        }
        super.getUIClass(event)
    }

    @Override
    protected ViewProvider createViewProvider() {
        new UriMappingsAwareViewProvider()
    }
}
