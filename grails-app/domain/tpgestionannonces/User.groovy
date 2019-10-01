package tpgestionannonces

class User {

    String username
    String password
    //ajout du Validator sur le champ password sur les contraintes, sinon verif au niveau Metier
    Date dateCreated
    Date lastUpdated
    Illustration thumbnail

    static hasMany = [annonces : Annonce]

    static constraints = {
        username nullable : false, blank: false, size : 5..20
        password password : true, nullable : false, blank : false, size: 8..30
        thumbnail nullable : false
        annonces nullable : true
    }



}
