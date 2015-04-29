package org.vaadin.grails.security.util

import grails.util.Holders
import org.vaadin.grails.security.server.SecurityAwareUriMappings
import org.vaadin.grails.server.UriMappings
import org.vaadin.grails.util.ApplicationContextUtils

final class SecurityUtils {

    static Collection<String> getAccessPropertiesForPath(UriMappings uriMappings, String path) {
        def accessProperties = new HashSet()
        def accessProperty = uriMappings.getPathProperty(path,
                SecurityAwareUriMappings.ACCESS_PROPERTY)
        if (accessProperty) {
            accessProperties += accessProperty
        } else {
            def config = Holders.config.vaadin
            accessProperty = config.access
            if (accessProperty) {
                accessProperties += accessProperty
            }
        }
        accessProperties
    }

    static Collection<String> getAccessPropertiesForPath(String path) {
        def uriMappings = ApplicationContextUtils.getSingletonBean(UriMappings)
        getAccessPropertiesForPath(uriMappings, path)
    }

    static Collection<String> getAccessPropertiesForFragment(UriMappings uriMappings, String path, String fragment) {
        def accessProperties = new HashSet()
        def accessProperty = uriMappings.getFragmentProperty(path, fragment,
                SecurityAwareUriMappings.ACCESS_PROPERTY)
        if (accessProperty) {
            accessProperties += accessProperty
        } else {
            accessProperty = getAccessPropertiesForPath(uriMappings, path)
            if (accessProperty) {
                accessProperties += accessProperty
            }
        }
        accessProperties
    }

    static Collection<String> getAccessPropertiesForFragment(String path, String fragment) {
        def uriMappings = ApplicationContextUtils.getSingletonBean(UriMappings)
        getAccessPropertiesForFragment(uriMappings, path, fragment)
    }

    private SecurityUtils() { }
}
