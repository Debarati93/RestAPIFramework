package cucumber.Options;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features/AddPLaceApi.feature",
        plugin="json:target/jsonReports/cucumber-report.json",
        glue= {"stepdefinition"},tags = "")
public class TestRunner {


}
