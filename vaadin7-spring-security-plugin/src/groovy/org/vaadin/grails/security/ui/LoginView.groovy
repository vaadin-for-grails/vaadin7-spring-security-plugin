package org.vaadin.grails.security.ui

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
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
    }

    LoginView() {
        this(ApplicationContextUtils.getBeanOrInstance(LoginComponent, DefaultLoginComponent))
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent event) { }
}
