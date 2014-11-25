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

    @Override
    String getViewName(String fragmentAndParams) {
        return super.getViewName(fragmentAndParams)
    }

    @Override
    View getView(String fragment) {

        def mappingsProvider = getMappingsProvider() as SecurityMappingsProvider
        def viewClass = mappingsProvider.getViewClass(path, fragment)
        def access = mappingsProvider.getAccess(viewClass)

        def securityService = Vaadin.applicationContext.getBean(SpringSecurityService)
        boolean secured = access && access.length > 0
        if (secured) {
            if (!securityService.isLoggedIn()) {
                return loginViewClass
            } else {

                if (!SpringSecurityUtils.ifAllGranted(access.join(","))) {
                    return notAuthorizedViewClass
                }
            }
        }

        return super.getView(fragment)
    }
}
