package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
				features = {".//Features/"}, // to run all feature files 
				//features = ".//Features//Login.feature",
        	    //features="@target/rerun.txt", // to run failure tests
                //features = ".//Features/"  // every feature file execute
               // features = {".//Features//Login.feature", ".//Features//Customers.feature"},  // to run multiple feature files     
               
        //features="@target/rerun.txt", // to run failure tests
        glue = "stepDefinitions",
        dryRun = false,
        monochrome=true, //it will delete unnecessary info from console window
        tags="@regression", // tags plugin need to be installed-
        // tags="@Sanity","@Regression", // sanity AND regression
        //tags="@Sanity,@Regression", // either sanity or regression
        // tags ="@End2End","~@Sanity","~@Regression",  // exclude certain tags use
            //tags="@End2EnD",
        plugin = {"pretty",
                "html:test-output",
                "json:json_output",
                "junit:junit_xml/cucumber.xml",
                //"rerun:target/rerun"
                }) //Failure feature along with line no
public class TestRun {

}
