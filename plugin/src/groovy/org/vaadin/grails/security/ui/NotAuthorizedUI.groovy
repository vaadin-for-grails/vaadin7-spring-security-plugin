package org.vaadin.grails.security.ui

import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI
import org.vaadin.grails.util.ApplicationContextUtils

/**
 * Simple UI wrapping {@link org.vaadin.grails.security.ui.NotAuthorizedComponent}.
 *
 * @author Stephan Grundner
 */
class NotAuthorizedUI extends UI {

    NotAuthorizedUI(NotAuthorizedComponent notAuthorizedComponent) {
        content = notAuthorizedComponent
    }

    NotAuthorizedUI() {
        this(ApplicationContextUtils.getBeanOrInstance(NotAuthorizedComponent, DefaultNotAuthorizedComponent))
    }

    @Override
    protected void init(VaadinRequest request) { }
}
