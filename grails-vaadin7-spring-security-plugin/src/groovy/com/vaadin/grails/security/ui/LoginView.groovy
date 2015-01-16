package com.vaadin.grails.security.ui

import com.vaadin.grails.Vaadin
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

    Component buildCompositionRoot() {
        Vaadin.newInstance(LoginComponent)
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent event) {
        compositionRoot = buildCompositionRoot()
    }
}
