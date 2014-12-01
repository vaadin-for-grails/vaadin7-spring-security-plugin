package com.vaadin.grails.navigator

import com.vaadin.grails.Vaadin
import com.vaadin.grails.security.LoginView
import com.vaadin.grails.security.NotAuthorizedView
import com.vaadin.grails.server.SecurityAwareUriMappingsHolder
import com.vaadin.navigator.View
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
class SecuredUriMappingsAwareViewProvider extends UriMappingsAwareViewProvider {

    SecuredUriMappingsAwareViewProvider(String path) {
        super(path)
    }

    Class<? extends View> getLoginViewClass() {
        LoginView
    }

    Class<? extends View> getNotAuthorizedViewClass() {
        NotAuthorizedView
    }

    String[] getRoles(String fragment) {
        uriMappings.getFragmentProperty(path, fragment, SecurityAwareUriMappingsHolder.ACCESS_FRAGMENT_PROPERTY)
    }

    @Override
    String getViewName(String fragmentAndParams) {
        return super.getViewName(fragmentAndParams)
    }

    @Override
    View getView(String fragment) {
        if (fragment == "") {
            fragment = defaultFragment
        }

        def roles = getRoles(fragment)
        if (roles?.length > 0) {
            def securityService = Vaadin.applicationContext.getBean(SpringSecurityService)
            if (!securityService.isLoggedIn()) {
                def viewClass = Vaadin.utils.getVaadinViewClass(loginViewClass)
                return Vaadin.utils.instantiateVaadinComponentClass(viewClass)
            } else if (!SpringSecurityUtils.ifAllGranted(roles.join(","))) {
                def viewClass = Vaadin.utils.getVaadinViewClass(notAuthorizedViewClass)
                return Vaadin.utils.instantiateVaadinComponentClass(viewClass)
            } else {
//                granted!
            }
        }

        return super.getView(fragment)
    }
}
