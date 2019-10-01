package tpgestionannonces

class BootStrap {
//init nous sert à repeupler notre base à chaque démarrage du serveur d'application
    def init = { servletContext ->

        def userInstance = new User(username: "username",
                password: "password",
                thumbnail: new Illustration(filename: "thumb.png")
        ).save(flush: true, failOnError: true)

        (1..5).each {
            def annonceInstance = new Annonce(
                    title: "title",
                    description: "description",
                    validTill: new Date(),
                    state: Boolean.TRUE
                    )
                    .addToIllustrations(new Illustration(filename: "filename"))
                    .addToIllustrations(new Illustration(filename: "filename_2"))
                    .addToIllustrations(new Illustration(filename: "filename_3"))
            userInstance.addToAnnonces(annonceInstance)

        }
        userInstance.save(flush: true, failOnError: true)
    }


    def destroy = {
    }

}
