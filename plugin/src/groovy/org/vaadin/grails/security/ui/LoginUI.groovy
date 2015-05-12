package org.vaadin.grails.security.ui

import com.vaadin.server.Page
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI
import org.vaadin.grails.util.ApplicationContextUtils

/**
 * Simple UI wrapping {@link org.vaadin.grails.security.ui.LoginComponent}.
 *
 * @author Stephan Grundner
 */
class LoginUI extends UI {

    LoginUI(LoginComponent loginComponent) {
        content = loginComponent
        loginComponent.addLoginListener(new LoginComponent.LoginListener() {
            @Override
            void loginSucceeded(LoginComponent.LoginEvent event) {
                Page.current.reload()
            }

            @Override
            void loginFailed(LoginComponent.LoginEvent event) { }
        })
    }

    LoginUI() {
        this(ApplicationContextUtils.getBeanOrInstance(LoginComponent, DefaultLoginComponent))
    }

    @Override
    protected void init(VaadinRequest request) { }
}
