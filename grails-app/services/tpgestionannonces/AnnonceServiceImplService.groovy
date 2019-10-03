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


    def serviceMethod() {

    }
}
