/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumirendpoint;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 *
 * @author carlos
 */
public class ConsumirEndPoint {
    
    private static String baseuri = "http://auditoria00-181115.appspot.com/_ah/api/echo/v1/log";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        if(args.length != 2){
            System.out.println("Invocación incorrecta");
            System.exit(1);
        }
        
        Client client = Client.create();
        WebResource resource = client.resource(baseuri);
        StringBuilder input = new StringBuilder();
        input.append("{\"usuario\": \"").append(args[0]).append("\",\"contenido\": \"").append(args[1]).append("\"}");
        System.out.println("Entrada: ");
        System.out.println(input.toString());
        ClientResponse response = resource.accept("application/json").type("application/json").post(ClientResponse.class, input.toString());
        // check response status code
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }

        // display response
        String output = response.getEntity(String.class);
        System.out.println("Output from Server .... ");
        System.out.println(output + "\n");
    }

}
