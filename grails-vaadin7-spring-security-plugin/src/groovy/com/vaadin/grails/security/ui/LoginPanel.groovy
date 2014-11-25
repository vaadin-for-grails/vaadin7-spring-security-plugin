package com.vaadin.grails.security.ui

import com.vaadin.ui.*

/**
 * @author Stephan Grundner
 */
public interface LoginPanel extends Component {

    Label getTitleLabel()
    TextField getUsernameField()
    PasswordField getPasswordField()
    Button getLoginButton()
}