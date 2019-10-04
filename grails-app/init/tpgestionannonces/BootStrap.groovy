package tpgestionannonces

class BootStrap {
//init nous sert à repeupler notre base à chaque démarrage du serveur d'application
    def init = { servletContext ->

        def userInstance = new User(username: "Macron",
                password: "password",
                thumbnail: new Illustration(filename: "thumb.png")
        ).save(flush: true, failOnError: true)

        (1..1).each {
            def annonceInstance = new Annonce(
                    title: "2 Joueurs de foot à vendre (coach inclus)",
                    description: "Umtiti qui a cassé la demarche, Benzema l'algérien et Didier le BG",
                    validTill: new Date(),
                    state: Boolean.TRUE
                    )
                    .addToIllustrations(new Illustration(filename: "umtiti.jpg"))
                    .addToIllustrations(new Illustration(filename: "Didier.jpg"))
                    .addToIllustrations(new Illustration(filename: "benzema.jpg"))
            userInstance.addToAnnonces(annonceInstance)

        }
        userInstance.save(flush: true, failOnError: true)
    }


    def destroy = {
    }

}
