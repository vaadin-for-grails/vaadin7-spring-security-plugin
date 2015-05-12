package org.vaadin.grails.security.ui

import com.vaadin.ui.Window
import org.vaadin.grails.util.ApplicationContextUtils

/**
 * Simple Window wrapping {@link org.vaadin.grails.security.ui.LoginComponent}.
 *
 * @author Stephan Grundner
 * @since 2.0
 */
class LoginWindow extends Window {

    final LoginComponent loginComponent

    LoginWindow(String caption) {
        super(caption)
        loginComponent = ApplicationContextUtils.getBeanOrInstance(
                LoginComponent, DefaultLoginComponent)
        content = loginComponent
    }

    LoginWindow() {
        this(ApplicationContextUtils.getMessage('security.login.title'))
    }

    void open() {
        def ui = getUI() ?: com.vaadin.ui.UI.current
        if (!ui.windows.find { it == this }) {
            ui.addWindow(this)
        }
        center()
    }
}
