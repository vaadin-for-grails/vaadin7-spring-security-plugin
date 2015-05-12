package org.vaadin.grails.security.ui

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.CustomComponent
import org.vaadin.grails.util.ApplicationContextUtils

/**
 * Simple View wrapping {@link org.vaadin.grails.security.ui.NotAuthorizedComponent}.
 *
 * @author Stephan Grundner
 */
class NotAuthorizedView extends CustomComponent implements View {

    NotAuthorizedView(NotAuthorizedComponent notAuthorizedComponent) {
        compositionRoot = notAuthorizedComponent
    }

    NotAuthorizedView() {
        this(ApplicationContextUtils.getBeanOrInstance(NotAuthorizedComponent, DefaultNotAuthorizedComponent))
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) { }
}
