package org.vaadin.grails.security.ui

import com.vaadin.event.ShortcutAction
import com.vaadin.ui.*
import org.vaadin.grails.security.navigator.LoginPresenter
import org.vaadin.grails.util.ApplicationContextUtils

/**
 * Default implementation for {@link LoginComponent}.
 *
 * @author Stephan Grundner
 */
class DefaultLoginComponent extends CustomComponent implements LoginComponent {

    final LoginPresenter presenter
    final Set<LoginComponent.LoginListener> loginListeners = new HashSet()

    Label titleLabel
    TextField usernameField
    PasswordField passwordField
    Button loginButton

    DefaultLoginComponent() {
        presenter = createPresenter()
        compositionRoot = buildCompositionRoot()
        presenter.init()
    }

    protected Component buildCompositionRoot() {
        def content = new VerticalLayout()
        content.setMargin(true)
        def form = new FormLayout()
        form.addComponent(titleLabel = new Label(value: ApplicationContextUtils.getMessage("security.login.title"), styleName: "h1"))
        form.addComponent(usernameField = new TextField(ApplicationContextUtils.getMessage("security.username.caption")))
        usernameField.focus()
        form.addComponent(passwordField = new PasswordField(ApplicationContextUtils.getMessage("security.password.caption")))
        form.addComponent(loginButton = new Button(ApplicationContextUtils.getMessage("security.login.button")))
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

    @Override
    boolean addLoginListener(LoginComponent.LoginListener listener) {
        loginListeners.add(listener)
    }

    @Override
    boolean removeLoginListener(LoginComponent.LoginListener listener) {
        loginListeners.remove(listener)
    }

    @Override
    void fireLoginSucceededEvent() {
        def event = new LoginComponent.LoginEvent(this)
        loginListeners.each { loginListener ->
            loginListener.loginSucceeded(event)
        }
    }

    @Override
    void fireLoginFailedEvent() {
        println "failed: ${loginListeners}"
        def event = new LoginComponent.LoginEvent(this)
        loginListeners.each { loginListener ->
            loginListener.loginFailed(event)
        }
    }
}
