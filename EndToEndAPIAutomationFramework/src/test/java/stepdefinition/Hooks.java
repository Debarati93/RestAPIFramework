package stepdefinition;

import io.cucumber.core.backend.StepDefinition;
import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {


    @Before("@DeletePlaceAPI")
    public void runBefore() throws IOException {
        AddPlaceApiStepDefinition sd = new AddPlaceApiStepDefinition() ;
        sd.addPlacePayloadWith("Debarati","Bengali","CongressPara,Balurghat");
        sd.userCallsAddplaceApiWithHttpPostRequest("AddPlaceApi","POST");
        sd.verifyPlace_idCreatedMapsToUsing("Debarati","GetPLaceApi");


    }
}
