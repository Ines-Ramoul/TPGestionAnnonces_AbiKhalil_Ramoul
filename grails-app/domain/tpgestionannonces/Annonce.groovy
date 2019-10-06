package tpgestionannonces
class Annonce {

    static hasMany = [illustrations : Illustration]

    static belongsTo = [author : User]

    static constraints = {
        title blank:false, nullable:false
        description blank:false, nullable:false
        validTill nullable:false
        illustrations nullable : true
    }

    String title
    String description
    Date dateCreated
    Date validTill
    Boolean state = Boolean.FALSE

    @Override
    String toString(){
        return title
    }
}
