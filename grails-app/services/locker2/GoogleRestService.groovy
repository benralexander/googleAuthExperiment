package locker2

import grails.plugins.rest.client.RestResponse
import grails.transaction.Transactional
import org.apache.juli.logging.LogFactory
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.web.json.JSONObject
import grails.plugins.rest.client.RestBuilder

@Transactional
class GoogleRestService {

    GrailsApplication grailsApplication
    private static final log = LogFactory.getLog(this)

    private  String MYSQL_REST_SERVER = ""

    JSONObject buildCallToRetrieveOneTimeCode(String oneTimeCode) {
        String destination =   "https://${grailsApplication.config.googleapi.baseUrl}/oauth2/v3/token"
      //  String encodedRedirectUrl=URLEncoder.encode(grailsApplication.config.oauth.providers.google.successUri, "UTF-8")
        String encodedRedirectUrl=URLEncoder.encode(grailsApplication.config.oauth.providers.google.callback, "UTF-8")
        String contents = "code=${oneTimeCode}&"+
                          "client_id=${grailsApplication.config.oauth.providers.google.key}&"+
                          "client_secret=${grailsApplication.config.oauth.providers.google.secret}&"+
                          "redirect_uri=${encodedRedirectUrl}&"+
                          "grant_type=authorization_code"
        JSONObject jsonObject = postGoogleRestCallBase (contents,destination)
        String idToken = jsonObject.id_token
        String accessToken = jsonObject.access_token
        JSONObject identityInformation =  postAuthorizedGoogleRestCall(accessToken,"https://www.googleapis.com/plus/v1/people/me")
        log.info(""+
        identityInformation.emails['value']+
        identityInformation.id+
        identityInformation.name ['familyName']+
        identityInformation.name ['givenName']+
        identityInformation.displayName+
        identityInformation.domain+
        identityInformation.gender+
        identityInformation.language+
        identityInformation.etag)
        return identityInformation

//                identityInformation.emails['value']
//                identityInformation.id
//                identityInformation.name ['familyName']
//                identityInformation.name ['givenName']
//                identityInformation.displayName
//                identityInformation.domain
//                identityInformation.gender
//                identityInformation.language
//                identityInformation.etag
    }

    private JSONObject postGoogleRestCallBase(String drivingContents, String targetUrl){
        JSONObject returnValue = null as JSONObject
        Date beforeCall  = new Date()
        Date afterCall
        RestResponse response
        RestBuilder rest = new grails.plugins.rest.client.RestBuilder()
        StringBuilder logStatus = new StringBuilder()
        try {
            response  = rest.post(targetUrl)   {
                contentType "application/x-www-form-urlencoded"
                json drivingContents
            }
            afterCall  = new Date()
        } catch ( Exception exception){
            log.error("NOTE: exception on post to backend. Target=${targetUrl}, driving Json=${drivingContents}")
            log.error(exception.toString())
            logStatus <<  "NOTE: exception on post to backend. Target=${targetUrl}, driving Json=${drivingContents}"
            afterCall  = new Date()
        }
        logStatus << """
SERVER CALL:
url=${targetUrl},
parm=${drivingContents},
time required=${(afterCall.time-beforeCall.time)/1000} seconds
""".toString()
        if (response?.responseEntity?.statusCode?.value == 200) {
            returnValue =  response.json
            logStatus << """status: ok""".toString()
        }  else {
            JSONObject tempValue =  response.json
            logStatus << """status: ${response.responseEntity.statusCode.value}""".toString()
            if  (tempValue)  {
                logStatus << """is_error: ${response.json["is_error"]}""".toString()
            }  else {
                logStatus << "no valid Json returned"
            }
        }
        log.info(logStatus)
        return returnValue
    }









    private JSONObject postAuthorizedGoogleRestCall(String authorization,String targetUrl){
        JSONObject returnValue = null as JSONObject
        Date beforeCall  = new Date()
        Date afterCall
        RestResponse response
        RestBuilder rest = new grails.plugins.rest.client.RestBuilder()
        StringBuilder logStatus = new StringBuilder()
        try {
            response  = rest.get(targetUrl)   {
                contentType "application/x-www-form-urlencoded"
                header "Authorization", "Bearer ${authorization}"
            }
            afterCall  = new Date()
        } catch ( Exception exception){
            log.error("NOTE: exception on post to backend. Target=${targetUrl}, no message body")
            log.error(exception.toString())
            logStatus <<  "NOTE: exception on post to backend. Target=${targetUrl}, no message body"
            afterCall  = new Date()
        }
        logStatus << """
SERVER CALL:
url=${targetUrl},
time required=${(afterCall.time-beforeCall.time)/1000} seconds
""".toString()
        if (response?.responseEntity?.statusCode?.value == 200) {
            returnValue =  response.json
            logStatus << """status: ok""".toString()
        }  else {
            JSONObject tempValue =  response.json
            logStatus << """status: ${response.responseEntity.statusCode.value}""".toString()
            if  (tempValue)  {
                logStatus << """is_error: ${response.json["is_error"]}""".toString()
            }  else {
                logStatus << "no valid Json returned"
            }
        }
        log.info(logStatus)
        return returnValue
    }















}
