package com.vaadin.grails.security.ui

import com.vaadin.event.ShortcutAction
import com.vaadin.grails.Vaadin
import com.vaadin.grails.security.LoginPresenter
import com.vaadin.ui.*

/**
 * Default implementation for {@link LoginComponent}.
 *
 * @author Stephan Grundner
 */
class DefaultLoginComponent extends CustomComponent implements LoginComponent {

    final LoginPresenter presenter

    Label titleLabel
    TextField usernameField
    PasswordField passwordField
    Button loginButton

    DefaultLoginComponent() {
        presenter = createPresenter()
        compositionRoot = buildCompositionRoot()
        presenter.init()
        setStyleName("borderless")
    }

    protected Component buildCompositionRoot() {
        def content = new VerticalLayout()
        content.setMargin(true)
        def form = new FormLayout()
        form.addComponent(titleLabel = new Label(value: Vaadin.i18n("security.login.title"), styleName: "h1"))
        form.addComponent(usernameField = new TextField(Vaadin.i18n("security.username.caption")))
        form.addComponent(passwordField = new PasswordField(Vaadin.i18n("security.password.caption")))
        form.addComponent(loginButton = new Button(Vaadin.i18n("security.login.button")))
        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER)
        loginButton.addStyleName("default")
        loginButton.addStyleName("primary")
        form.setSizeUndefined()
        content.addComponent(form)
        content.setComponentAlignment(form, Alignment.MIDDLE_CENTER)
        content.setSizeFull()
        content
    }

    LoginPresenter createPresenter() {
        new LoginPresenter(this)
    }
}
