package lock

class HomeController {

    def index() {
        render (view:'hello')
    }
    def hello(){
        render (view: 'hello')
    }
    def googleAuthentication(){
        render (view: 'googleAuth')
    }
    def twitterAuthentication(){

        render (view: 'twitterAuth')
    }

}
