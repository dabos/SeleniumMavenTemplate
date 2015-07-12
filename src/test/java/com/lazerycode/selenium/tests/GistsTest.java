package com.lazerycode.selenium.tests;

import com.jayway.restassured.filter.Filter;
import com.jayway.restassured.filter.FilterContext;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.jsv.JsonSchemaValidator;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.FilterableRequestSpecification;
import com.jayway.restassured.specification.FilterableResponseSpecification;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * @author alexandrubostan
 * @since 11.07.2015
 */
public class GistsTest {
    String host = "https://api.github.com";
    String token = System.getProperty("token");
    String username = System.getProperty("username");

    public void createAGist() throws Exception {
               given().
                  baseUri(host).
                  filter(sign(token)).
                  accept(ContentType.JSON).
                  contentType(ContentType.JSON).
                  body(gist).
                when().
                  post("/gists").
                then().
                  statusCode(201);
    }


    @Test
    public void listAUsersGists() throws Exception {
                given().
                  baseUri(host).
                  filter(sign(token)).
                when().
                  get("/users/" + username + "/gists").
                then().
                  assertThat().
                  body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonschema.json"));
    }

    @Test
    public void deleteAGist() throws Exception {
        String id = makeAGist();

                given().
                  baseUri(host).
                  filter(sign(token)).
                when().
                  delete("/gists/" + id).
                then().
                  statusCode(204);
    }

    private String makeAGist() {
        return given().
                baseUri(host).
                filter(sign(token)).
                contentType("application/json").body(gist).
                when().
                log().all().
                post("/gists").andReturn().jsonPath().getString("id");
    }

    private String gist = "{" +
            "  \"description\": \"the description for this gist\",\n" +
            "  \"public\": true,\n" +
            "  \"files\": {\n" +
            "    \"hw.txt\": {\n" +
            "      \"content\": \"String file contents\"\n" +
            "    }\n" +
            "  }\n" +
            "}";

    private static Filter sign(final String accessToken) {
        return new Filter() {
            @Override
            public Response filter(FilterableRequestSpecification requestSpec,
                                   FilterableResponseSpecification responseSpec,
                                   FilterContext ctx) {
                requestSpec.header("Authorization", String.format("Bearer %s",
                        accessToken));
                return ctx.next(requestSpec, responseSpec);
            }
        };
    }
}
