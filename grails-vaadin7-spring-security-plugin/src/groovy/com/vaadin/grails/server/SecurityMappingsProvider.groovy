package com.vaadin.grails.server

import com.vaadin.grails.VaadinUIClass
import com.vaadin.grails.VaadinViewClass

/**
 * @author Stephan Grundner
 */
class SecurityMappingsProvider extends DefaultMappingsProvider {

//    static final def log = Logger.getLogger(SecurityMappingsProvider)

    Map<VaadinUIClass, String[]> accessMapByUIClass = new HashMap()
    Map<VaadinViewClass, String[]> accessMapByViewClass = new HashMap()

    @Override
    protected VaadinUIClass pathToUIClass(String path, ConfigObject pathConfig) {
        def uiClass = super.pathToUIClass(path, pathConfig)
        accessMapByUIClass.put(uiClass, pathConfig.access as String[])
        uiClass
    }

    @Override
    protected VaadinViewClass fragmentToViewClass(String path, String fragment, ConfigObject fragmentConfig) {
        def viewClass = super.fragmentToViewClass(path, fragment, fragmentConfig)
        accessMapByViewClass.put(viewClass, fragmentConfig.access as String[])
        viewClass
    }

    String[] getAccess(VaadinUIClass uiClass) {
        accessMapByUIClass.get(uiClass)
    }

    String[] getAccess(VaadinViewClass viewClass) {
        accessMapByViewClass.get(viewClass)
    }
}
