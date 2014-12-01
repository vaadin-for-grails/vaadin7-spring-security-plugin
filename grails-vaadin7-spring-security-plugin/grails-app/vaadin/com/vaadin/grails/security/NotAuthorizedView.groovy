package com.vaadin.grails.security

import com.vaadin.grails.security.ui.DefaultNotAuthorizedComponent
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.Component
import com.vaadin.ui.CustomComponent

/**
 * Simple View wrapping {@link com.vaadin.grails.security.ui.NotAuthorizedComponent}.
 *
 * @author Stephan Grundner
 */
class NotAuthorizedView extends CustomComponent implements View {

    static namespace = "security"

    Component buildCompositionRoot() {
        new DefaultNotAuthorizedComponent()
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        compositionRoot = buildCompositionRoot()
    }
}
