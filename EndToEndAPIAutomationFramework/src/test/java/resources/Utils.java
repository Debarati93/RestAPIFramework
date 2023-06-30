package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {


    public static RequestSpecification req;
    ResponseSpecification response;




    public RequestSpecification requestSpecification() throws IOException {

        if(req==null)
        {
            PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalProperties("baseURI")).setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
        }
        return req;
    }

    public ResponseSpecification responseSpecification()
    {
        response = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
        return response;
    }
    public static String getGlobalProperties(String key) throws IOException {
        Properties prop=new Properties();
        prop.load(new FileInputStream("C:\\Users\\ddey058\\Documents\\EndToEndAPIAutomationFramework\\src\\test\\java\\resources\\global.properties"));
        return prop.getProperty(key);

    }

    public static String getJsonPathValue(Response response, String key)
    {
        String res = response.asString();
        JsonPath js = new JsonPath(res);
        String value=js.get(key).toString();
        return value;

    }
}
