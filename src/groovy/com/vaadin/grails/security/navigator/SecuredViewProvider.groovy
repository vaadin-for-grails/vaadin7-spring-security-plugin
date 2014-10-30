package com.vaadin.grails.security.navigator

import com.vaadin.grails.navigator.DefaultViewProvider
import com.vaadin.grails.security.ui.LoginView
import com.vaadin.grails.security.ui.NotAuthorizedView
import com.vaadin.navigator.View
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured

/**
 * A secured view provider.
 *
 * @author Stephan Grundner
 */
class SecuredViewProvider extends DefaultViewProvider {

    @Autowired
    SpringSecurityService securityService

    @Override
    View getView(String path) {
        def beanName = getBeanName(path)
        if (beanName) {
            def secured = applicationContext.findAnnotationOnBean(beanName, Secured)
            if (secured) {
                if (!securityService.loggedIn) {
                    return applicationContext.getBean(LoginView)
                }

                def roles = secured.value()
                if (!SpringSecurityUtils.ifAllGranted(roles.join(","))) {
                    return applicationContext.getBean(NotAuthorizedView)
//                    throw new AccessDeniedException("Not authorized")
                }
            }
        }
        super.getView(path)
    }
}
