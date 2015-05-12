import demo.ui.IndexView
import demo.ui.SecondView

vaadin {

//    access = "ROLE_ADMIN"

    mappings {

        "/vaadin" {
            theme = "valo"
//            access = ["ROLE_USER", "ROLE_OTHER"]

            fragments {

                "index" {
                    view = IndexView
                    access = "permitAll"
                }

                "second" {
                    view = SecondView
                    access = "ROLE_ADMIN"
                }

            }
        }

        "/vaadin2" {
            theme = "valo"
            access = ["ROLE_ADMIN"]

        }

    }

}