package com.vaadin.grails.security

import com.vaadin.grails.security.ui.DefaultLoginPanel
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.Component
import com.vaadin.ui.UI

/**
 * @author Stephan Grundner
 */
class LoginUI extends UI {

    protected Component buildContent() {
        new DefaultLoginPanel()
    }

    @Override
    protected void init(VaadinRequest request) {
        content = buildContent()
    }
}
