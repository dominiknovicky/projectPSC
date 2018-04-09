package akademiasovy.PSC.resources;


import akademiasovy.PSC.db.MySQL;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@javax.ws.rs.Path("/posta")
public class Path {

    @GET
    @javax.ws.rs.Path("/city/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPSCfromCity(@PathParam("city")String city){

        List<String> listPSC = new MySQL().getZip(city);

        boolean b = false;
        String result = "[";
        for (String temp : listPSC) {
            if (b == true) {
                result += ',';
            } else
                b = true;
            result += "\"" + temp + "\"";
        }
        result += "]";
        return  result;
    }

    @GET
    @javax.ws.rs.Path("/psc/{psc}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCityFromPSC(@PathParam("psc")String zip){
        List<String> listCity = new MySQL().getCity(zip);

        boolean b = false;
        String result = "[";
        for (String temp : listCity) {
            if (b == true) {
                result += ',';
            } else
                b = true;
            result += "\"" + temp + "\"";
        }
        result += "]";
        return  result;
    }

}
