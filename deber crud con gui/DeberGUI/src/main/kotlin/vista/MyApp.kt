package vista

import tornadofx.*

class MyApp: App(MyView::class, InternalWindow.Styles::class) {
    fun main(args: Array<String>){
        launch<MyApp>(args)
    }
}