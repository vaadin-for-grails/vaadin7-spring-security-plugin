package demo.ui

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.Label
import org.vaadin.grails.navigator.Navigation
import org.vaadin.grails.ui.builders.ComponentBuilder

class IndexView extends CustomComponent implements View {

    IndexView() {
        compositionRoot = ComponentBuilder.build {
            verticalLayout(margin: true) {
                button(caption: "Next View", styleName: "primary", clickListener: {
                    Navigation.navigateTo(fragment: "second", params: [foo: 'bar'])
                })
            }
        }
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
