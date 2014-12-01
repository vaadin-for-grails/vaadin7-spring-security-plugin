package com.vaadin.grails.security

import com.vaadin.grails.security.ui.DefaultLoginComponent
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.Component
import com.vaadin.ui.CustomComponent

/**
 * Simple View wrapping {@link com.vaadin.grails.security.ui.LoginComponent}.
 *
 * @author Stephan Grundner
 */
class LoginView extends CustomComponent implements View {

    static namespace = "security"

    Component buildCompositionRoot() {
        new DefaultLoginComponent()
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent event) {
        compositionRoot = buildCompositionRoot()
    }
}
