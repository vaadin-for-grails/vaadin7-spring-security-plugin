package demo.ui

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.CustomComponent
import org.vaadin.grails.navigator.Navigation
import org.vaadin.grails.ui.builders.ComponentBuilder

class SecondView extends CustomComponent implements View {

    SecondView() {
        compositionRoot = ComponentBuilder.build {
            verticalLayout(margin: true) {
                button(caption: "Previous View", styleName: "friendly", clickListener: {
                    Navigation.navigateTo(fragment: "index")
                })
            }
        }
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
