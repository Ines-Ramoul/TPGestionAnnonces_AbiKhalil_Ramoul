package tpgestionannonces

import grails.converters.JSON
import grails.converters.XML

import javax.management.relation.Role
import javax.servlet.http.HttpServletRequest
import java.text.SimpleDateFormat

class ApiController {
    AnnonceService annonceService
    String pattern = "yyyy-MM-dd"


    def annonces(){

        switch(request.getMethod()){
            case 'POST':
                def newAnnonce = new Annonce(title: request.JSON.title, description: request.JSON.description, validTill: new SimpleDateFormat(pattern).parse(request.JSON.validTill))
                def user = User.findById(request.JSON.author.id)
                user.addToAnnonces(newAnnonce).save(flush:true)

                return response.status = 200
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
                if(!params.id)
                    return response.status = 400
                def annonceInstance = Annonce.get(params.id)
                if(!annonceInstance)
                    return response.status= 404
                if(!request.JSON.title || !request.JSON.description  || !request.JSON.validTill || !request.JSON.state)
                    return response.status = 404
                annonceInstance.setTitle(request.JSON.title)
                annonceInstance.setDescription(request.JSON.description)
                //annonceInstance.setDateCreated(new SimpleDateFormat(pattern).parse(params.dataCreated))
                annonceInstance.setValidTill(new SimpleDateFormat(pattern).parse(request.JSON.validTill))
                //annonceInstance.setValidTill(params.validTill)
                annonceInstance.setState(new Boolean(request.JSON.state))
                annonceService.save(annonceInstance)
                return response.status = 200
                break
            case "PATCH":
                if(!params.id)
                    return response.status = 400
                def annonceInstance = Annonce.get(params.id)
                if(!annonceInstance)
                    return response.status= 404
                if(request.JSON.title)
                    annonceInstance.setTitle(request.JSON.title)
                if(request.JSON.description)
                    annonceInstance.setDescription(request.JSON.description)
                if(request.JSON.validTill)
                    annonceInstance.setValidTill(new SimpleDateFormat(pattern).parse(request.JSON.validTill))
                //annonceInstance.setDateCreated(new SimpleDateFormat(pattern).parse(params.dataCreated))
                //annonceInstance.setValidTill(new SimpleDateFormat(pattern).parse(params.validTill))
                //annonceInstance.setValidTill(params.validTill)
                if(request.JSON.state)
                    annonceInstance.setState(new Boolean(request.JSON.state))
                annonceService.save(annonceInstance)
                return response.status = 200
                break
            case "DELETE":
                def annonceInstance = Annonce.get(params.id)
                if(!params.id){
                    return response.status = 400
                }
                if (!annonceInstance){
                    return response.status = 404
                }
                annonceService.delete(annonceInstance.id)
                if(!annonceInstance){
                    return response.status = 202
                }

                break
            default:
                return response.status = 405
            break

        }
        return response.status = 200
    }




}
