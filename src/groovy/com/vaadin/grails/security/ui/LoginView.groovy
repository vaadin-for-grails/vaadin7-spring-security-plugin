package com.vaadin.grails.security.ui

import com.vaadin.navigator.View
import com.vaadin.ui.Button
import com.vaadin.ui.PasswordField
import com.vaadin.ui.TextField

public interface LoginView extends View {

    TextField getUsernameField()
    PasswordField getPasswordField()
    Button getLoginButton()
}