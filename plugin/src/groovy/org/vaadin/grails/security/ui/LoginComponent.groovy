package org.vaadin.grails.security.ui

import com.vaadin.ui.*

/**
 * A basic login component.
 *
 * @author Stephan Grundner
 */
public interface LoginComponent extends Component {

    static class LoginEvent extends EventObject {

        LoginEvent(LoginComponent loginComponent) {
            super(loginComponent)
        }
    }

    static interface LoginListener {

        void loginSucceeded(LoginEvent event)
        void loginFailed(LoginEvent event)
    }

    boolean addLoginListener(LoginListener listener)
    boolean removeLoginListener(LoginListener listener)
    void fireLoginSucceededEvent()
    void fireLoginFailedEvent()

    Label getTitleLabel()
    TextField getUsernameField()
    PasswordField getPasswordField()
    Button getLoginButton()
}