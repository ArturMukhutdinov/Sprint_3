import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class GetAllOrdersListTest {

    @DisplayName("Check GET all orders request") // имя теста
    @Description("Test for api/v1/orders/ endpoint") // описание
    @Test
    public void getOrdersReturnsTest(){
        OrdersClient ordersClient = new OrdersClient();

        ValidatableResponse response = ordersClient.getAll();

        List<Object> orders = response.extract().jsonPath().getList("orders");

        assertFalse(orders.isEmpty());

    }
}
