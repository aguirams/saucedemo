package cucumberFramework;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Feature",
		glue={"stepDefinition"},
		plugin= {"pretty","html:target/cucumber-reports",
				"junit:target/cucumber-reports-xml/Cucumber.xml"},
		monochrome=true
		//tags= ("@PaymentTest")
		)

public class TestRunner {

}