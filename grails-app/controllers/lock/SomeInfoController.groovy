package lock



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import lock.SomeInfo

@Transactional(readOnly = true)
class SomeInfoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SomeInfo.list(params), model:[someInfoInstanceCount: SomeInfo.count()]
    }

    def show(SomeInfo someInfoInstance) {
        respond someInfoInstance
    }

    def create() {
        respond new SomeInfo(params)
    }

    @Transactional
    def save(SomeInfo someInfoInstance) {
        if (someInfoInstance == null) {
            notFound()
            return
        }

        if (someInfoInstance.hasErrors()) {
            respond someInfoInstance.errors, view:'create'
            return
        }

        someInfoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'someInfo.label', default: 'SomeInfo'), someInfoInstance.id])
                redirect someInfoInstance
            }
            '*' { respond someInfoInstance, [status: CREATED] }
        }
    }

    def edit(SomeInfo someInfoInstance) {
        respond someInfoInstance
    }

    @Transactional
    def update(SomeInfo someInfoInstance) {
        if (someInfoInstance == null) {
            notFound()
            return
        }

        if (someInfoInstance.hasErrors()) {
            respond someInfoInstance.errors, view:'edit'
            return
        }

        someInfoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'someInfo.label', default: 'SomeInfo'), someInfoInstance.id])
                redirect someInfoInstance
            }
            '*'{ respond someInfoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(SomeInfo someInfoInstance) {

        if (someInfoInstance == null) {
            notFound()
            return
        }

        someInfoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'someInfo.label', default: 'SomeInfo'), someInfoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'someInfo.label', default: 'SomeInfo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
