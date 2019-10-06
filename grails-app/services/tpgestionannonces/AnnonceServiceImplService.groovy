package tpgestionannonces

import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional

import javax.servlet.http.HttpServletRequest

@Transactional
class AnnonceServiceImplService {
    AnnonceService annonceService
    GrailsApplication grailsApplication

    def uploadFile(Annonce annonce, HttpServletRequest request, String filename){

        def f = request.getFile(filename)
        if (f.empty) {
            flash.message = 'file cannot be empty'
            render(view: 'uploadForm')
            return
        }

        String fileName = System.currentTimeMillis() + f.getOriginalFilename()
        f.transferTo(new File(grailsApplication.config.maconfig.assets_path + fileName))


        annonce.addToIllustrations(new Illustration(filename:fileName))
        annonceService.save(annonce)
    }

    def deleteFileFromFolder(Long illustrationId){
        def illustration = Illustration.get(illustrationId)
        def filename = "C:/Users/Inès Ramoul/Documents/M2 MBDS/Grails/TPGestionAnnonces/grails-app/assets/images/" + illustration.filename
        def fileDeleted = new File(filename).delete()
        if (fileDeleted){
            illustration.delete(flush:true)
        }
        else {println "error suppr file"}

    }




    def serviceMethod() {

    }
}
