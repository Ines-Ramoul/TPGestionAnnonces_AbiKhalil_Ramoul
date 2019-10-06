package tpgestionannonces

import grails.converters.JSON
import grails.validation.ValidationException
import org.springframework.web.servlet.ModelAndView

import static org.springframework.http.HttpStatus.*

class AnnonceController {

    AnnonceService annonceService
    AnnonceServiceImplService annonceServiceImplService

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond annonceService.list(params), model:[annonceCount: annonceService.count()]
    }

    def show(Long id) {
        respond annonceService.get(id)
    }


    def create() {
        respond new Annonce(params)
    }

    def save(Annonce annonce) {

        String filename = "myFile"

        if (annonce == null) {
            notFound()
            return
        }
        try {
            annonceServiceImplService.uploadFile(annonce,request, filename)
        } catch (ValidationException e) {
            respond annonce.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'annonce.label', default: 'Annonce'), annonce.id])
                redirect annonce
            }
            '*' { respond annonce, [status: CREATED] }
        }

    }

    def edit(Long id) {
        respond annonceService.get(id)
    }

    def update(Annonce annonce) {
        String filename = "myFile"
        if (annonce == null) {
            notFound()
            return
        }

        try {
            annonceServiceImplService.uploadFile(annonce,request, filename)
        } catch (ValidationException e) {
            respond annonce.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'annonce.label', default: 'Annonce'), annonce.id])
                redirect annonce
            }
            '*'{ respond annonce, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        annonceService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'annonce.label', default: 'Annonce'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'annonce.label', default: 'Annonce'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def deleteIllustration(){
        def illustrationId = params.illustrationId
        def annonceId=params.annonceId
        def annonceInstance = Annonce.get(annonceId)
        def illustrationInstance = Illustration.get(illustrationId)
        if (illustrationInstance==null) {
            println "pas d'instance illustration"
            respond annonceInstance, [status: OK]
        }else {annonceInstance.removeFromIllustrations(illustrationInstance)}

        annonceInstance.save(flush:true)
        //pour delete l'ILLUSTRATION du folder
        println illustrationId
        annonceServiceImplService.deleteFileFromFolder(Long.valueOf(params.illustrationId))
        println annonceInstance.illustrations.empty
        /*if (annonceInstance.illustrations.empty){
            annonceInstance.delete(flush:true)
            render
        }
        render status: NO_CONTENT*/
        render(view: "/annonce/index")
    }
}
