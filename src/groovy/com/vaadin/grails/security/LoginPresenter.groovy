package com.vaadin.grails.security

import com.vaadin.grails.Grails
import com.vaadin.grails.security.ui.LoginView
import com.vaadin.server.Page
import com.vaadin.server.UserError
import org.springframework.context.annotation.Scope
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
@Scope("prototype")
class LoginPresenter {

    final LoginView view

    LoginPresenter(LoginView view) {
        this.view = view
    }

    void login() {
        def context = Grails.applicationContext
        def manager = context.getBean(AuthenticationManager)
        def authentication = new UsernamePasswordAuthenticationToken(
                view.usernameField.value,
                view.passwordField.value)

        try {
            def result = manager.authenticate(authentication)
            SecurityContextHolder.context.authentication = result
            success()
            
            Page.current.reload()
        } catch (BadCredentialsException e) {
            error()
        }
    }

    protected void success() {

    }

    protected void error() {
        def error = new UserError(Grails.i18n("loginView.error"))
        view.usernameField.setComponentError(error)
        view.passwordField.setComponentError(error)
    }
}
