package com.vaadin.grails.security

import com.vaadin.grails.security.ui.DefaultLoginComponent
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.Component
import com.vaadin.ui.UI

/**
 * Simple UI wrapping {@link com.vaadin.grails.security.ui.LoginComponent}.
 *
 * @author Stephan Grundner
 */
class LoginUI extends UI {

    static namespace = "security"

    protected Component buildContent() {
        new DefaultLoginComponent()
    }

    @Override
    protected void init(VaadinRequest request) {
        content = buildContent()
    }
}
