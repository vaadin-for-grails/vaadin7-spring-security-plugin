import com.vaadin.grails.ui.DefaultUI
import demo.IndexView

vaadin {

    mappings {

        "/vaadin" {
            ui = DefaultUI
            theme = "valo"
//            access = ["ROLE_ADMIN"]

            fragments {

                "index" {
                    view = IndexView
                    access = ["ROLE_ADMIN"]
                }

            }
        }

    }

}