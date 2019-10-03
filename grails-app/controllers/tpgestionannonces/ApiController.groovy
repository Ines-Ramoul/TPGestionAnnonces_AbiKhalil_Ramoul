package tpgestionannonces

import grails.converters.JSON
import grails.converters.XML

import javax.management.relation.Role
import javax.servlet.http.HttpServletRequest

class ApiController {
    def annonces(){

        switch(request.getMethod()){
            case 'POST':
                Annonce newAnnonce
                println "BEFORE ADDING ANNONCE"
                println(request.JSON.title.get(0))
                println("HAYDAAAA HOUWE AFTER TITLE")
                println("HAYDAAAA HOUWE DESCRIPTION" , request.JSON.description)
                println("HAYDAAAA HOUWE AFTER DESCRIPTION")
                println("HAYDAAAA HOUWE VQLIDTILL " , request.JSON.validTill)
                newAnnonce = new Annonce(title: request.JSON.title, description: request.JSON.description, validTill: request.JSON.validTill).save(flush: true)
                println "AFTER ADDING ANNONCE"
                if(!newAnnonce){
                    println "AFTER ADDING ANNONCE IFFF"
                    return response.status = 400
                }
               break

            case 'GET' :
                def annonceList = Annonce.findAllByState(true)
                if (!annonceList.id)
                    return response.status= 400

                if(!annonceList)
                    return response.status = 404
                response.withFormat {
                    json {render annonceList as JSON}
                    xml {render annonceList as XML}

                }
                break
        }
    }

    def annonce() {
        switch(request.getMethod()){
            case "GET" :
                if (!params.id)
                    return response.status= 400
                def annonceInstance = Annonce.get(params.id)
                if(!annonceInstance)
                    return response.status = 404
                response.withFormat {
                    json {render annonceInstance as JSON}
                    xml {render annonceInstance as XML}

                }
                break
            case "PUT":
                break
            case "PATCH":
                break
            case "DELETE":
                break
            default:
                return response.status = 405
            break

        }
        return response.status = 406
    }




}
