package org.vaadin.grails.security.ui

import com.vaadin.ui.Component
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout
import org.vaadin.grails.util.ApplicationContextUtils

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
        def title = new Label(ApplicationContextUtils.getMessage("security.notAuthorized.title"))
        title.setStyleName("h1")
        root.addComponent(title)
        def description = new Label(ApplicationContextUtils.getMessage("security.notAuthorized.description"))
        description.setStyleName("colored")
        root.addComponent(description)
        root
    }
}
