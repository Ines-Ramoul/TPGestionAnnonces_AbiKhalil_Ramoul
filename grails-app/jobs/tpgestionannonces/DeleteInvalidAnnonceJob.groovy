package tpgestionannonces

class DeleteInvalidAnnonceJob {
    static triggers = {
      simple repeatInterval: 20000l // execute job once in 5 seconds
    }


    def execute() {
        println("INSIDE")
        def annonceInstance = Annonce.find ("from Annonce as a where a.validTill < :nowDate",[nowDate: new Date()])
        annonceInstance.each {
            println("CIAOOO "+it.id)
           // it.delete(flush: true)
        }
        // execute job
    }
}
