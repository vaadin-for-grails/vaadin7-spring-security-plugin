package org.vaadin.grails.security.navigator

import com.vaadin.server.UserError
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.vaadin.grails.security.ui.LoginComponent
import org.vaadin.grails.util.ApplicationContextUtils

/**
 * Presenter class for {@link LoginComponent}.
 *
 * @author Stephan Grundner
 */
class LoginPresenter {

    final LoginComponent component

    LoginPresenter(LoginComponent component) {
        this.component = component
    }

    void init() {
        component.loginButton.addClickListener({ e ->
            login()
        })

    }

    void login() {
        def applicationContext = ApplicationContextUtils.applicationContext
        def manager = applicationContext.getBean(AuthenticationManager)
        def authentication = new UsernamePasswordAuthenticationToken(
                component.usernameField.value,
                component.passwordField.value)
        try {
            def result = manager.authenticate(authentication)
            SecurityContextHolder.context.authentication = result
            succeeded()
        } catch (BadCredentialsException e) {
            failed()
        }
    }

    void succeeded() {
//        Page.current.reload()
//        @since 2.1
        component.fireLoginSucceededEvent()
    }

    void failed() {
        def message = ApplicationContextUtils.getMessage("security.login.failed.message")
        def error = new UserError(message)
        component.usernameField.setComponentError(error)
        component.passwordField.setComponentError(error)
        component.fireLoginFailedEvent()
    }
}
