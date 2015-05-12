package org.vaadin.grails.security.ui

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.server.Page
import com.vaadin.ui.CustomComponent
import org.vaadin.grails.util.ApplicationContextUtils

/**
 * Simple View wrapping {@link org.vaadin.grails.security.ui.LoginComponent}.
 *
 * @author Stephan Grundner
 */
class LoginView extends CustomComponent implements View {

    LoginView(LoginComponent loginComponent) {
        compositionRoot = loginComponent
        loginComponent.addLoginListener(new LoginComponent.LoginListener() {
            @Override
            void loginSucceeded(LoginComponent.LoginEvent event) {
                Page.current.reload()
            }

            @Override
            void loginFailed(LoginComponent.LoginEvent event) { }
        })
    }

    LoginView() {
        this(ApplicationContextUtils.getBeanOrInstance(LoginComponent, DefaultLoginComponent))
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent event) { }
}
