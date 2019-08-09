package spock.demo

import grails.testing.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * This test case will get one chuck-norris-jokes per execution
 * ./gradlew integrationTest -DintegrationTest.single='*demo/Application'
 */

@Integration
@Rollback
class ApplicationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "chuck-norris-jokes"(){
        when:"passing the target url with key name and key value"
            Client client = ClientBuilder.newClient();
            WebTarget resourceTarget = client.target("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/random");
            String response = resourceTarget
                                    .request("text/plain")
                                    .header("X-RapidAPI-Key", "7sulgp32YKmshxPdpl3s17Yk8u7fp1l1cnDjsnsKtvco2PBM62")
                                    .get(String.class);
        then:"receive chuck norris joke"
            assert response != null
            assert response != ""
            println "Joke:- "+response
    }

}