package lock

class HomeController {

    def index() {
        render (view:'hello')
    }
    def hello(){
        render (view: 'hello')
    }
}
