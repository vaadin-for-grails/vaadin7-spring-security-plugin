package com.vaadin.grails.security

import com.vaadin.grails.security.ui.DefaultLoginPanel
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.Component
import com.vaadin.ui.CustomComponent

/**
 * @author Stephan Grundner
 */
class LoginView extends CustomComponent implements View {

    Component buildCompositionRoot() {
        new DefaultLoginPanel()
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent event) {
        compositionRoot = buildCompositionRoot()
    }
}
