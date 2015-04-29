package org.vaadin.grails.security.navigator

import com.vaadin.server.Page
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

    final LoginComponent panel

    LoginPresenter(LoginComponent panel) {
        this.panel = panel
    }

    void init() {
        panel.loginButton.addClickListener({ e ->
            login()
        })
    }

    void login() {
        def applicationContext = ApplicationContextUtils.applicationContext
        def manager = applicationContext.getBean(AuthenticationManager)
        def authentication = new UsernamePasswordAuthenticationToken(
                panel.usernameField.value,
                panel.passwordField.value)
        try {
            def result = manager.authenticate(authentication)
            SecurityContextHolder.context.authentication = result
            succeeded()
        } catch (BadCredentialsException e) {
            failed()
        }
    }

    void succeeded() {
        Page.current.reload()
    }

    void failed() {
        def message = ApplicationContextUtils.getMessage("security.login.failed.message")
        def error = new UserError(message)
        panel.usernameField.setComponentError(error)
        panel.passwordField.setComponentError(error)
    }
}
