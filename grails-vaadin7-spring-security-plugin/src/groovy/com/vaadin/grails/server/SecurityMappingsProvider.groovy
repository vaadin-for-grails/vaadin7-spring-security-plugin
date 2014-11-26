package com.vaadin.grails.server

import com.vaadin.grails.VaadinUIClass
import com.vaadin.grails.VaadinViewClass

/**
 * @author Stephan Grundner
 */
class SecurityMappingsProvider extends DefaultMappingsProvider {

//    static final def log = Logger.getLogger(SecurityMappingsProvider)

    Map<VaadinUIClass, String[]> accessRestrictionByUIClass = new HashMap()
    Map<VaadinViewClass, String[]> accessRestrictionMapByViewClass = new HashMap()

    @Override
    protected VaadinUIClass pathToUIClass(String path, ConfigObject pathConfig) {
        def uiClass = super.pathToUIClass(path, pathConfig)
        accessRestrictionByUIClass.put(uiClass, pathConfig.access as String[])
        uiClass
    }

    @Override
    protected VaadinViewClass fragmentToViewClass(String path, String fragment, ConfigObject fragmentConfig) {
        def viewClass = super.fragmentToViewClass(path, fragment, fragmentConfig)
        accessRestrictionMapByViewClass.put(viewClass, fragmentConfig.access as String[])
        viewClass
    }

    String[] getAccessRestriction(VaadinUIClass uiClass) {
        accessRestrictionByUIClass.get(uiClass)
    }

    String[] getAccessRestriction(VaadinViewClass viewClass) {
        accessRestrictionMapByViewClass.get(viewClass)
    }
}
