package com.vaadin.grails.security.ui

import com.vaadin.event.ShortcutAction
import com.vaadin.grails.Vaadin
import com.vaadin.grails.security.LoginPresenter
import com.vaadin.server.Sizeable
import com.vaadin.ui.*

class DefaultLoginPanel extends Panel implements LoginPanel {

    final LoginPresenter presenter

    Label titleLabel
    TextField usernameField
    PasswordField passwordField
    Button loginButton

    DefaultLoginPanel() {
        presenter = createPresenter()
        content = buildContent()
        presenter.init()
        setStyleName("borderless")
    }

    protected Component buildContent() {
        def content = new VerticalLayout()
        content.setMargin(true)
        def form = new FormLayout()
        form.setWidth(480, Sizeable.Unit.PIXELS)
        form.addComponent(titleLabel = new Label(value: Vaadin.i18n("security.login.title"), styleName: "h1"))
        form.addComponent(usernameField = new TextField(Vaadin.i18n("security.username.caption")))
        form.addComponent(passwordField = new PasswordField(Vaadin.i18n("security.password.caption")))
        form.addComponent(loginButton = new Button(Vaadin.i18n("security.login.button")))
        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER)
        loginButton.addStyleName("default")
        loginButton.addStyleName("primary")
        content.addComponent(form)
        content.setComponentAlignment(form, Alignment.MIDDLE_CENTER)
        content.setSizeFull()
        content
    }

    LoginPresenter createPresenter() {
        new LoginPresenter(this)
    }
}
