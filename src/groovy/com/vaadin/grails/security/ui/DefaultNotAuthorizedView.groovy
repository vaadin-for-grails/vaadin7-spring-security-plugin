package com.vaadin.grails.security.ui

import com.vaadin.grails.Grails
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.Component
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope

import javax.annotation.PostConstruct

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@org.springframework.stereotype.Component("notAuthorizedView")
class DefaultNotAuthorizedView extends CustomComponent implements NotAuthorizedView {

    @PostConstruct
    void init() {

        compositionRoot = buildCompositionRoot()
    }

    protected Component buildCompositionRoot() {
        def layout = new VerticalLayout()
        layout.setMargin(true)
        def title = new Label(Grails.i18n("notAuthorizedView.title"))
        title.setStyleName("h1")
        layout.addComponent(title)
        def description = new Label(Grails.i18n("notAuthorizedView.description"))
        layout.addComponent(description)
        layout
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent event) { }
}
