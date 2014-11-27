package com.vaadin.grails.server

import com.vaadin.grails.Vaadin
import com.vaadin.grails.navigator.SecuredMappingsAwareViewProvider
import com.vaadin.grails.security.LoginUI
import com.vaadin.grails.security.NotAuthorizedUI
import com.vaadin.navigator.Navigator
import com.vaadin.server.UIClassSelectionEvent
import com.vaadin.server.UICreateEvent
import com.vaadin.ui.UI
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils

/**
 * @author Stephan Grundner
 */
class SecuredMappingsAwareUIProvider extends MappingsAwareUIProvider {

    Class<? extends UI> getLoginUIClass() {
        LoginUI
    }

    Class<? extends UI> getNotAuthorizedUIClass() {
        NotAuthorizedUI
    }

    String[] getRoles(String path) {
        mappingsProvider.getPathProperty(path, SecurityMappingsProvider.ACCESS_PATH_PROPERTY)
    }

    @Override
    Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
        def path = pathHelper.getPathWithinApplication(event.request)
        def roles = getRoles(path)
        if (roles?.length > 0) {
            def securityService = Vaadin.applicationContext.getBean(SpringSecurityService)
            if (!securityService.isLoggedIn()) {
                return loginUIClass
            } else if (!SpringSecurityUtils.ifAllGranted(roles.join(","))) {
                return notAuthorizedUIClass
            } else {
//                granted!
            }
        }

        return super.getUIClass(event)
    }

    @Override
    protected Navigator createNavigator(UICreateEvent event, UI ui) {
        def path = pathHelper.getPathWithinApplication(event.request)
        def navigator = new Navigator(ui, ui)
        navigator.addProvider(new SecuredMappingsAwareViewProvider(path))
        navigator
    }
}
