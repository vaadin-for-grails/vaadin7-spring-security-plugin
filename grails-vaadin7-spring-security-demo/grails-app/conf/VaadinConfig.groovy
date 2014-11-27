vaadin {

    mappings {

        "/vaadin" {
            ui = "default"
            theme = "valo"
            access = ["ROLE_ADMIN"]

            fragments {

                "index" {
                    view = "index"
                    access = ["ROLE_ADMIN"]
                }

            }
        }

    }

}