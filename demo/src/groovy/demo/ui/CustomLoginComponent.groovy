package demo.ui

import org.vaadin.grails.security.ui.DefaultLoginComponent
import com.vaadin.ui.Component

class CustomLoginComponent extends DefaultLoginComponent {

    @Override
    protected Component buildCompositionRoot() {
        def root = super.buildCompositionRoot()
        titleLabel.value = "Custom Login"
        root
    }
}
