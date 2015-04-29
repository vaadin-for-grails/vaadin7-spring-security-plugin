import demo.ui.CustomLoginComponent

// Place your Spring DSL code here
beans = {

    loginComponent(CustomLoginComponent) { bean ->
        bean.scope = "prototype"
    }
}
