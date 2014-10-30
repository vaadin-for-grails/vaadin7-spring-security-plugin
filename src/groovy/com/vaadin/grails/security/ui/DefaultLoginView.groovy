package com.vaadin.grails.security.ui

import com.vaadin.event.ShortcutAction
import com.vaadin.grails.Grails
import com.vaadin.grails.navigator.Views
import com.vaadin.grails.security.LoginPresenter
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.*
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope

import javax.annotation.PostConstruct

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@org.springframework.stereotype.Component("loginView")
class DefaultLoginView extends CustomComponent implements LoginView {

    def params

    TextField usernameField
    PasswordField passwordField
    Button loginButton

    LoginPresenter presenter

    @PostConstruct
    void init() {
        presenter = Grails.getUniqueBean(LoginPresenter, this)

        compositionRoot = buildCompositionRoot()
    }

    protected Component buildCompositionRoot() {
        def layout = new VerticalLayout()
        def form = new FormLayout()
        form.setWidthUndefined()
        def title = new Label(Grails.i18n("loginView.title"))
        title.setStyleName("h1")
        form.addComponent(title)

        form.addComponent(usernameField = new TextField(Grails.i18n("loginView.username")))
        usernameField.setImmediate(true)
        usernameField.focus()
        form.addComponent(passwordField = new PasswordField(Grails.i18n("loginView.password")))
        form.addComponent(loginButton = new Button(Grails.i18n("loginView.login"), new Button.ClickListener() {
            @Override
            void buttonClick(Button.ClickEvent clickEvent) {
//                presenter.login()
            }
        }))
        loginButton.setStyleName("primary")
        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER)

        layout.addComponent(form)
        layout.setComponentAlignment(form, Alignment.MIDDLE_CENTER)
        layout
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent event) {
        params = Views.decodeParams(event.parameters)
    }
}
