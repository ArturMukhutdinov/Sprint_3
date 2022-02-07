import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class CourierTest {

    private Courier courier;
    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setUp(){
        courier = Courier.getRandom();
        courierClient = new CourierClient();
    }
    @Step("If courier is created, delete courier")
    @After
    public void tearDown() {
        if (courierId != 0) {
            courierClient.delete(courierId);
        }
    }
    @DisplayName("Check courier create request") // имя теста
    @Description("Test for api/v1/courier/ endpoint") // описание
    @Test
    public void checkCourierCanBeCreated(){
      ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(CourierCredentials.from(courier));

        courierId = responseLogin.extract().path("id");

        int statusCode = responseCreate.extract().statusCode();
        boolean isCourierCreated = responseCreate.extract().path("ok");

        assertTrue("Courier is not created",isCourierCreated);
        assertThat("Courier is not created", courierId, is(not(0)));
        assertThat("Status code is incorrect", statusCode, equalTo(201));

    }
    @DisplayName("Check the same courier create request") // имя теста
    @Description("Test for api/v1/courier/ endpoint") // описание
    @Test
    public void checkSameCourierCanNotBeCreated(){
        courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(CourierCredentials.from(courier));

        courierId = responseLogin.extract().path("id"); // для удаления созданного курьера

        ValidatableResponse responseCreate = courierClient.create(courier);

        int statusCode = responseCreate.extract().statusCode();
        String isCourierCreated = responseCreate.extract().path("message");

        assertThat(statusCode, equalTo(409));
        assertThat(isCourierCreated, equalTo("Этот логин уже используется. Попробуйте другой."));


    }
}
