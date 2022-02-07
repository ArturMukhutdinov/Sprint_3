import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends RestAssuredClient{

    private static final String COURIER_PATH = "api/v1/courier/";

    @Step("Create new courier")
    public ValidatableResponse create(Courier courier){
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then();
    }

    @Step("Authorisation of courier")
    public ValidatableResponse login(CourierCredentials credentials){
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(COURIER_PATH + "login/")
                .then();

    }

    @Step("Delete exist courier")
    public boolean delete(int courierId){
        return given()
                .spec(getBaseSpec())
                .when()
                .delete(COURIER_PATH + courierId)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("ok");
    }

}
