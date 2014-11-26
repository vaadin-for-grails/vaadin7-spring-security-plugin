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

    @Override
    Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
        def mappingsProvider = super.mappingsProvider as SecurityMappingsProvider
        def path = pathHelper.getPathWithinApplication(event.request)
        def uiClass = mappingsProvider.getUIClass(path)
        def access = mappingsProvider.getAccessRestriction(uiClass)

        def securityService = Vaadin.applicationContext.getBean(SpringSecurityService)
        boolean secured = access && access.length > 0
        if (secured) {
            if (!securityService.isLoggedIn()) {
                return loginUIClass
            } else {

                if (!SpringSecurityUtils.ifAllGranted(access.join(","))) {
                    return notAuthorizedUIClass
                }
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
