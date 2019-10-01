package tpgestionannonces

class BootStrap {
//init nous sert à repeupler notre base à chaque démarrage du serveur d'application
    def init = { servletContext ->
        new User(username : "username",
                password : "password",
                thumbnail: new Illustration(filename: "thumb.png"))
                .save(flush:true,failOnError:true)
    }
    def destroy = {
    }
}
