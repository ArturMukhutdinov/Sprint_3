import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrdersClient extends RestAssuredClient{

    private static final String ORDER_PATH = "api/v1/orders/";

    @Step("Get all orders")
    public ValidatableResponse getAll(){
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDER_PATH)
                .then();
    }

    @Step("Create new order")
    public ValidatableResponse createNewOrder(Order order){
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();

    }
}

