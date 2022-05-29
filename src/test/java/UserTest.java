import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserTest {

    private RequestSpecification requestSpecification;

    private String STATUS;



    @BeforeClass
    public void setup() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader(Constants.AUTH_HEADER_NAME, "Bearer " + Token.getToken())
                .addHeader(Constants.APP_HEADER_NAME,"IntelliAPI")
                .addHeader(Constants.ORG_HEADER_NAME,"MaxSoft");
        requestSpecification = requestSpecBuilder.build();
    }

    @Test
    public void getAllTasks(){
        Response response = given().spec(requestSpecification).get("/tasks").then().extract().response();

    }

    //validate the name of the task
    @Test
    public void validateTaskName(){
        Response response = given().spec(requestSpecification).get("/tasks").then().extract().response();

        assertThat(response.path("status"),equals("Completed"));
    }
}
