package resources;



import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class PayloadCreation {

        public static AddPlace addPlacePayload(String name, String language, String address)
        {
            List<String> types = new ArrayList<String>();
            types.add("shoe park");
            types.add("shop");
            Location loc = new Location();
            loc.setLat(-38.383494);
            loc.setLng(33.427362);

            AddPlace p = new AddPlace();
            p.setAccuracy("50");
            p.setAddress(address);
            p.setLanguage(language);
            p.setLocation(loc);
            p.setName(name);
            p.setPhone_number("(+91) 983 893 3937");
            p.setTypes(types);
            p.setWebsite("http://google.com");
            return p;

        }

        public static String getDeletePlacePayload(String place_id)
        {
            return "{\n" +
                    "\n" +
                    "    \"place_id\":\""+place_id+"\"\n" +
                    "}";
        }
}
