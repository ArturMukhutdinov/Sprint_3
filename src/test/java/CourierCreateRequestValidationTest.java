import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;



@RunWith(Parameterized.class)
public class CourierCreateRequestValidationTest {



    private final Courier courier;
    private final int expectedStatus;
    private final String expectedErrorMessage;


    public CourierCreateRequestValidationTest(Courier courier, int expectedStatus, String expectedErrorMessage) {
        this.courier = courier;
        this.expectedStatus = expectedStatus;
        this.expectedErrorMessage = expectedErrorMessage;
    }


    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {Courier.getWithLoginOnly(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getWithPasswordOnly(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getFirstNameOnly(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getLoginAndPassword(), 201, null}
        };
    }

    @DisplayName("Check courier validation request") // имя теста
    @Description("Test for api/v1/courier/ endpoint") // описание
    @Test
    public void CourierRequestValidationTest(){
        ValidatableResponse response = new CourierClient().create(courier);

        int statusCode = response.extract().statusCode();

        String errorMessage = response.extract().path("message");

        assertThat(statusCode, equalTo(expectedStatus));
        assertThat(errorMessage, equalTo(expectedErrorMessage));
    }


}

