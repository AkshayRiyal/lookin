package lookin

import com.ttn.dto.GoogleProfile
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

import org.grails.web.json.JSONObject
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

import javax.annotation.security.PermitAll

@Secured("permitAll")
class LoginController {
    
    
    def googleAuth(String code) {
        GoogleProfile googleProfile = getGoogleUserProfile(code)
        render googleProfile as JSON
    }
    
    private GoogleProfile getGoogleUserProfile(String authorization_Code) {
        try {
            MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
            form.add("code", authorization_Code);
            form.add("client_id", "697244225685-p96bulii3ubavebeuivihpdvibrc1o2a.apps.googleusercontent.com");
            form.add("client_secret", "lvYgkD9YyLSqpE1S_Dg5MV8-");
            form.add("redirect_uri", "http://localhost:8080/login/googleAuth");
            form.add("grant_type", "authorization_code");
           org.springframework.http.HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            ParameterizedTypeReference<Map<String, Object>> map = new ParameterizedTypeReference<Map<String, Object>>() {
            };
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> response = restTemplate
                    .exchange("https://www.googleapis.com/oauth2/v4/token", HttpMethod.POST, new HttpEntity<>(form, headers), map).getBody();
            if (response.containsKey("error")) {
                log.error("check_token returned error: " + response.get("error"));
                return null;
            }
            GoogleProfile googleProfile = null;
            if (response.containsKey("access_token")) {
                log.info("token received" + response.get("access_token"));
                googleProfile = userGoogleProfile((String) response.get("access_token"));
            }
            return googleProfile;
        } catch (Exception e) {
            log.error("Error in fetching user profile from Google");
            log.error("stacktrace", e);
            return null;
        }
    }
    
    
    private GoogleProfile userGoogleProfile(String accessToken) {
        String googleUrlProfileUrl = new StringBuilder("https://www.googleapis.com/userinfo/v2/me").append("?access_token=").append(accessToken).toString();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GoogleProfile> responseEntity = null;
        try {
            log.debug("### Request for UserProfile from GOOGLE : ");
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);
            HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
            responseEntity = restTemplate.exchange(googleUrlProfileUrl, HttpMethod.GET, httpEntity,
                    GoogleProfile.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                GoogleProfile responseDTO = responseEntity.getBody();
                log.debug("[X] Received User Profile FromGoogle : " + responseDTO.toString());
                return responseDTO;
            }
        } catch (Exception e) {
        }
        
    }
}
