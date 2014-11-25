package com.vaadin.grails.server

import com.vaadin.grails.VaadinUIClass
import com.vaadin.grails.VaadinViewClass
import org.apache.log4j.Logger

/**
 * @author Stephan Grundner
 */
class SecurityMappingsProvider extends DefaultMappingsProvider {

    static final def log = Logger.getLogger(SecurityMappingsProvider)

    Map<VaadinUIClass, String[]> accessByUIClass = new HashMap()
    Map<VaadinViewClass, String[]> accessByViewClass = new HashMap()

    @Override
    protected VaadinUIClass pathToUIClass(String path, ConfigObject pathConfig) {
        def uiClass = super.pathToUIClass(path, pathConfig)
        accessByUIClass.put(uiClass, pathConfig.access as String[])
        uiClass
    }

    @Override
    protected VaadinViewClass fragmentToViewClass(String path, String fragment, ConfigObject fragmentConfig) {
        def viewClass = super.fragmentToViewClass(path, fragment, fragmentConfig)
        accessByViewClass.put(viewClass, fragmentConfig.access as String[])
        viewClass
    }

    String[] getAccess(VaadinUIClass uiClass) {
        accessByUIClass.get(uiClass)
    }

    String[] getAccess(VaadinViewClass viewClass) {
        accessByViewClass.get(viewClass)
    }
}
