/*import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class NotExistCourierParameterizedTest {

    private final Courier courier;
    private final int expectedStatus;
    private final String expectedErrorMessage;


    public NotExistCourierParameterizedTest(Courier courier, int expectedStatus, String expectedErrorMessage) {
        this.courier = courier;
        this.expectedStatus = expectedStatus;
        this.expectedErrorMessage = expectedErrorMessage;
    }
    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {Courier.getLoginAndPassword(), 404, "Учетная запись не найдена"},
                {Courier.getWithPasswordOnly(), 400, "Недостаточно данных для входа"},
                {Courier.getWithLoginOnly(), 400, "Недостаточно данных для входа"}

        };
    }

    @DisplayName("Check not exist courier authorisation request") // имя теста
    @Description("Test for api/v1/courier/login/ endpoint") // описание
    @Test
    public void checkNotValidAuthorisation(){
        ValidatableResponse responseLogin = new CourierClient().login(CourierCredentials.from(courier));

        int statusCode = responseLogin.extract().statusCode();
        assertThat(statusCode, equalTo(expectedStatus));

        String errorMessage = responseLogin.extract().path("message");
        assertThat(errorMessage, equalTo(expectedErrorMessage));
    }

}


*/




