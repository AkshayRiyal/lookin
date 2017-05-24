package lookin

import grails.plugin.springsecurity.annotation.Secured
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import org.apache.http.HttpHeaders
import org.grails.web.json.JSONObject
import org.springframework.http.HttpEntity
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

import javax.annotation.security.PermitAll

@Secured("permitAll")
class LoginController {
    
    
    def googleAuth(String code) {
        String googleaccesstoken = ""
        JSONObject googleJsonResponseForAccessToken
        def http = new HTTPBuilder('http://localhost:8080/')
        http.request(Method.POST) {
            uri.path = "https://accounts.google.com/o/oauth2/token"
            requestContentType = "application/x-www-form-urlencoded"
            body = 1
            
            response.success = { resp, json ->
                println "POST response status: ${resp.statusLine}"
                googleJsonResponseForAccessToken = json
                googleaccesstoken = json.access_token
            }
        }
        
        
    }
    
}
