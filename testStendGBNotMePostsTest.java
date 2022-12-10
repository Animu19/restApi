package org.example.testStendGB;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class testStendGBNotMePostsTest extends baseinfoTest  {

    @Test
    @DisplayName("Проверка, что статус кода равен:'200' ")
    void checkStatusCodTest() {
        given()
                .header("X-Auth-Token",getApiKey())
                .queryParam("owner","notMe")
                .queryParam("sort","createdAt")
                .queryParam("order","ASC")
                .queryParam("page","1")
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Проверка, что время ответа не меньше:'800' ")
    void checkTimeToRespondTest() {
        given()
                .header("X-Auth-Token",getApiKey())
                .queryParam("owner","notMe")
                .queryParam("sort","createdAt")
                .queryParam("order","DESC")
                .queryParam("page","1")
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .time(lessThan(800L));
    }

    @Test
    @DisplayName("Проверка, что статус кода равен:'500 при фильтре order=ALL' ")
    void checkStatusFailFilterOrderAllCodTest() {
        given()
                .header("X-Auth-Token",getApiKey())
                .queryParam("owner","notMe")
                .queryParam("sort","createdAt")
                .queryParam("order","ALL")
                .queryParam("page","1")
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .statusCode(500);
    }

    @Test
    @DisplayName("Проверка,что открылась страница номер'2' ")
    void checkOpenPageTest() {
        given()
                .header("X-Auth-Token",getApiKey())
                .queryParam("owner","notMe")
                .queryParam("sort","createdAt")
                .queryParam("order","ASC")
                .queryParam("page","2")
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .body("meta.prevPage", equalTo(1))
                .body("meta.nextPage", equalTo(3));
    }

    @Test
    @DisplayName("Проверка, сообщение об ошибке при фильтре order=ALL' ")
    void checkMessageFailTest() {
        given()
                .header("X-Auth-Token",getApiKey())
                .queryParam("owner","notMe")
                .queryParam("sort","createdAt")
                .queryParam("order","ALL")
                .queryParam("page","1")
                .when()
                .get(getBaseUrl())
                .then()
                .assertThat()
                .body("message", equalTo("Server error. Dont worry we already know about it."));
    }


    }
