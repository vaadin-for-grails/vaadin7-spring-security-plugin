package com.vaadin.grails.security

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.Label

/**
 * @author Stephan Grundner
 */
class NotAuthorizedView extends CustomComponent implements View {

    static namespace = "security"

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        compositionRoot = new Label("Not authorized.")
    }
}
