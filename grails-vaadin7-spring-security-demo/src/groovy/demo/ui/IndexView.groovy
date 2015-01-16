package demo.ui

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.Label

class IndexView extends CustomComponent implements View {

    IndexView() {

    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        compositionRoot = new Label("Index View.")
    }
}
