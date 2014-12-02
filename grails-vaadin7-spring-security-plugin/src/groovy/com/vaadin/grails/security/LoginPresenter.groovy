package com.vaadin.grails.security

import com.vaadin.grails.Vaadin
import com.vaadin.grails.security.ui.LoginComponent
import com.vaadin.server.Page
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

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
        def context = Vaadin.applicationContext
        def manager = context.getBean(AuthenticationManager)
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

    void failed() { }
}