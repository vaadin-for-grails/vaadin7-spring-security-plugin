package com.vaadin.grails.server

import com.vaadin.grails.Vaadin
import com.vaadin.grails.security.ui.LoginUI
import com.vaadin.grails.security.ui.NotAuthorizedUI
import com.vaadin.server.UIClassSelectionEvent
import com.vaadin.ui.UI
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils

/**
 * Checks <code>UriMappings</code> for <code>access</code> properties.
 * <p>
 *     If there is an <code>access</code> property and the current user is not logged in,
 *     a {@link com.vaadin.grails.security.ui.LoginComponent} is shown.
 * </p>
 * <p>
 *     If there is an <code>access</code> property and the current user is not granted,
 *     a {@link com.vaadin.grails.security.ui.NotAuthorizedComponent} is shown.
 * </p>
 *
 * @author Stephan Grundner
 */
class SecuredUriMappingsAwareUIProvider extends UriMappingsAwareUIProvider {

    Class<? extends UI> getLoginUIClass() {
        LoginUI
    }

    Class<? extends UI> getNotAuthorizedUIClass() {
        NotAuthorizedUI
    }

    String[] getRoles(String path) {
        uriMappings.getPathProperty(path, SecurityAwareUriMappingsHolder.ACCESS_PATH_PROPERTY)
    }

    @Override
    Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
        def path = pathHelper.getPathWithinApplication(event.request)
        def roles = getRoles(path)
        if (roles?.length > 0) {
            def securityService = Vaadin.getInstance(SpringSecurityService)
            if (!securityService.isLoggedIn()) {
                return loginUIClass
            } else if (!SpringSecurityUtils.ifAnyGranted(roles.join(","))) {
                return notAuthorizedUIClass
            } else {
//                granted!
            }
        }
        super.getUIClass(event)
    }
}
