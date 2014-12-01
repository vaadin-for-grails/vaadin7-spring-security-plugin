package com.vaadin.grails.security

import com.vaadin.grails.security.ui.DefaultNotAuthorizedComponent
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.Component
import com.vaadin.ui.UI

/**
 * Simple UI wrapping {@link com.vaadin.grails.security.ui.NotAuthorizedComponent}.
 *
 * @author Stephan Grundner
 */
class NotAuthorizedUI extends UI {

    static namespace = "security"

    protected Component buildContent() {
        new DefaultNotAuthorizedComponent()
    }

    @Override
    protected void init(VaadinRequest request) {
        content = buildContent()
    }
}
