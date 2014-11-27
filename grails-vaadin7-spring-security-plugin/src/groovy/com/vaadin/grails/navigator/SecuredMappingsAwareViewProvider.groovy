package com.vaadin.grails.navigator

import com.vaadin.grails.Vaadin
import com.vaadin.grails.security.LoginView
import com.vaadin.grails.security.NotAuthorizedView
import com.vaadin.grails.server.SecurityMappingsProvider
import com.vaadin.navigator.View
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils

/**
 * @author Stephan Grundner
 */
class SecuredMappingsAwareViewProvider extends MappingsAwareViewProvider {

    SecuredMappingsAwareViewProvider(String path) {
        super(path)
    }

    Class<? extends View> getLoginViewClass() {
        LoginView
    }

    Class<? extends View> getNotAuthorizedViewClass() {
        NotAuthorizedView
    }

    String[] getRoles(String fragment) {
        mappingsProvider.getFragmentProperty(path, fragment, SecurityMappingsProvider.ACCESS_FRAGMENT_PROPERTY)
    }

    @Override
    String getViewName(String fragmentAndParams) {
        return super.getViewName(fragmentAndParams)
    }

    @Override
    View getView(String fragment) {
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
