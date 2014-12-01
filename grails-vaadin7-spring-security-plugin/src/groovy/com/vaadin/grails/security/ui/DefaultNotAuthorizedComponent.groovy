package com.vaadin.grails.security.ui

import com.vaadin.grails.Vaadin
import com.vaadin.ui.Component
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout

/**
 * Default implementation for {@link NotAuthorizedComponent}.
 *
 * @author Stephan Grundner
 */
class DefaultNotAuthorizedComponent extends CustomComponent implements NotAuthorizedComponent {

    DefaultNotAuthorizedComponent() {
        compositionRoot = buildCompositionRoot()
    }

    protected Component buildCompositionRoot() {
        def root = new VerticalLayout()
        root.setMargin(true)
        def title = new Label(Vaadin.i18n("security.notAuthorized.title"))
        title.setStyleName("h1")
        root.addComponent(title)
        def description = new Label(Vaadin.i18n("security.notAuthorized.description"))
        description.setStyleName("colored")
        root.addComponent(description)
        root
    }
}
