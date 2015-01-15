package com.vaadin.grails.server

/**
 *
 * @author Stephan Grundner
 */
class SecurityAwareUriMappingsHolder extends DefaultUriMappingsHolder {

    static final ACCESS_PATH_PROPERTY = "access"
    static final ACCESS_FRAGMENT_PROPERTY = "access"

    @Override
    protected void handlePathConfig(String path, ConfigObject pathConfig) {
        super.handlePathConfig(path, pathConfig)
        setPathProperty(path, ACCESS_PATH_PROPERTY, pathConfig.get(ACCESS_PATH_PROPERTY))
    }

    @Override
    protected void handleFragmentConfig(String path, String fragment, ConfigObject fragmentConfig) {
        super.handleFragmentConfig(path, fragment, fragmentConfig)
        setFragmentProperty(path, fragment, ACCESS_FRAGMENT_PROPERTY, fragmentConfig.get(ACCESS_FRAGMENT_PROPERTY))
    }
}
