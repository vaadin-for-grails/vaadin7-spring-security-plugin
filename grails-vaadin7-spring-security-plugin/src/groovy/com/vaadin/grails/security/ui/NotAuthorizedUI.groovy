package com.vaadin.grails.security.ui

import com.vaadin.grails.Vaadin
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.Component
import com.vaadin.ui.UI

/**
 * Simple UI wrapping {@link com.vaadin.grails.security.ui.NotAuthorizedComponent}.
 *
 * @author Stephan Grundner
 */
class NotAuthorizedUI extends UI {

    protected Component buildContent() {
        Vaadin.newInstance(NotAuthorizedComponent)
    }

    @Override
    protected void init(VaadinRequest request) {
        content = buildContent()
    }
}