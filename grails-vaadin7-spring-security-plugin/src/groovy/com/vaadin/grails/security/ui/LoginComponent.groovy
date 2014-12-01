package com.vaadin.grails.security.ui

import com.vaadin.ui.*

/**
 * A basic login component.
 *
 * @author Stephan Grundner
 */
public interface LoginComponent extends Component {

    Label getTitleLabel()
    TextField getUsernameField()
    PasswordField getPasswordField()
    Button getLoginButton()
}