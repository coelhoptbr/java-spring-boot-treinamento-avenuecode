package br.com.sicredi.revendedora.e2e;

import static io.restassured.RestAssured.given;

import br.com.sicredi.revendedora.RevendedoraApplication;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(
    classes = {RevendedoraApplication.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public abstract class AbstractE2ETest {

  @Value("${local.server.port}")
  private int port;

  protected int getPort() {
    return port;
  }

  private String getBasePath() {
    return String.format("http://localhost:%s", port);
  }

  protected Response executeGet(String url) {
    String completeUrl = getBasePath() + url;
    Response response = given().get(completeUrl).andReturn();
    return response;
  }

  protected Response executePost(String url) {
    String completeUrl = getBasePath() + url;
    Response response = given().contentType(ContentType.JSON).post(completeUrl).andReturn();
    return response;
  }

  protected Response executePost(String url, Object bodyObject) {
    String completeUrl = getBasePath() + url;
    Response response =
        given().body(bodyObject).contentType(ContentType.JSON).post(completeUrl).andReturn();
    return response;
  }

  protected Response executePostWithHeaders(
      String url, Object bodyObject, Map<String, String> headers) {
    String completeUrl = getBasePath() + url;
    Response response = given().headers(headers).body(bodyObject).post(completeUrl).andReturn();
    return response;
  }

  protected Response executePut(String url, Object bodyObject) {
    String completeUrl = getBasePath() + url;
    Response response =
        given().body(bodyObject).contentType(ContentType.JSON).put(completeUrl).andReturn();
    return response;
  }

  protected Response executePutWithHeaders(
      String url, Object bodyObject, Map<String, String> headers) {
    String completeUrl = getBasePath() + url;
    Response response = given().headers(headers).body(bodyObject).put(completeUrl).andReturn();
    return response;
  }

  protected Response executePut(String url) {
    String completeUrl = getBasePath() + url;
    Response response = given().contentType(ContentType.JSON).put(completeUrl).andReturn();
    return response;
  }

  protected Response executeGetWithHeaders(String url, Map<String, String> headers) {
    String completeUrl = getBasePath() + url;
    Response response = given().headers(headers).get(completeUrl).andReturn();
    return response;
  }

  protected Response executeDelete(String url) {
    String completeUrl = getBasePath() + url;
    Response response = given().delete(completeUrl).andReturn();
    return response;
  }

  protected Response executeDeleteWithHeaders(String url, Map<String, String> headers) {
    String completeUrl = getBasePath() + url;
    Response response = given().headers(headers).delete(completeUrl).andReturn();
    return response;
  }
}
