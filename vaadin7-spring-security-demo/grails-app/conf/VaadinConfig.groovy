import demo.ui.IndexView

vaadin {

    access = "ROLE_STEPHAN"

    mappings {

        "/vaadin" {
            theme = "valo"
            access = ["ROLE_USER", "ROLE_OTHER"]

            fragments {

                "test" {
                    view = IndexView
                    access = "permitAll"
                }

            }
        }

        "/vaadin2" {
            theme = "valo"
//            access = "ROLE_ADMIN"

        }

    }

}