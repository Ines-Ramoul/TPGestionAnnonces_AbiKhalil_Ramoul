package tpgestionannonces

class Annonce {

    static hasMany = [illustrations : Illustration]

    static constraints = {
        title blank:false, nullable:false
        description blank:false, nullable:false
        validFill nullable:false
        illustrations nullable : true
    }

    String title
    String description
    Date dateCreated
    Date validFill
    Boolean state = Boolean.FALSE

}
