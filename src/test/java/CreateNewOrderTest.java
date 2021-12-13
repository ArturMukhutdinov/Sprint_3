import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(Parameterized.class)
public class CreateNewOrderTest {
      ScooterColor[] color;
      int expectedStatus;

    public CreateNewOrderTest(ScooterColor[] color, int expectedStatus){
        this.color = color;
        this.expectedStatus = expectedStatus;
    }
@Parameterized.Parameters
public static Object[][] getTestData(){
        return new Object[][]{
                {new ScooterColor[]{ScooterColor.BLACK, ScooterColor.GREY}, 201},
                {new ScooterColor[]{ScooterColor.GREY}, 201},
                {new ScooterColor[]{ScooterColor.BLACK}, 201},
                {null,201}
        };
}


    @DisplayName("Check new order create request") // имя теста
    @Description("Test for api/v1/orders/ endpoint") // описание
    @Test
    public void createNewOrderTest(){
        OrdersClient ordersClient = new OrdersClient();
        Order order = Order.getFaker().setColor(color);

        ValidatableResponse response = ordersClient.createNewOrder(order);

        int statusCode = response.extract().statusCode();
        int trackNumber = response.extract().path("track");

        assertThat("Status code is incorrect", statusCode, equalTo(201));
        assertThat("Track is incorrect", trackNumber, is(CoreMatchers.not(0)));



    }
}
