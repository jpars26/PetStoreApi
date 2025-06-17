package com.example.petstore;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

public class PetStoreApiTests {
  
    // Configuração inicial do RestAssured
    // Define a base URI e o caminho base para as requisições
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";
    }

    //Cadastrar novo pedido de pet com sucesso (POST /store/order)   
    @Test
    public void createOrder_Success() {
        String orderJson = "{\n" +
                "  \"id\": 1001,\n" +
                "  \"petId\": 1234,\n" +
                "  \"quantity\": 2,\n" +
                "  \"shipDate\": \"2025-06-16T12:00:00.000Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true\n" +
                "}";

        given()
            .contentType(ContentType.JSON)
            .body(orderJson)
        .when()
            .post("/store/order")
        .then()
            .statusCode(200)
            .body("id", equalTo(1001))
            .body("petId", equalTo(1234))
            .body("quantity", equalTo(2))
            .body("status", equalTo("placed"))
            .body("complete", equalTo(true));
    }

    //Pesquisar por um pet inexistente (GET /pet/{petId})  
    @Test
    public void getPet_Nonexistent_NotFound() {
        long nonexistentId = 999999L;

        given()
            .pathParam("petId", nonexistentId)
        .when()
            .get("/pet/{petId}")
        .then()
            .statusCode(404)
            .body("message", containsString("Pet not found"));
    }

    //Atualizar dados de um pet existente (PUT /pet)    
    @Test
    public void updatePet_Success() {
        // 1) Cria um pet “fake” para garantir que ele exista
        String createJson = "{\n" +
                "  \"id\": 5678,\n" +
                "  \"name\": \"TestPet\",\n" +
                "  \"status\": \"available\"\n" +
                "}";

        given()
            .contentType(ContentType.JSON)
            .body(createJson)
        .when()
            .post("/pet")
        .then()
            .statusCode(200)
            .body("id", equalTo(5678));

        // 2) Atualiza o pet criado acima
        String updateJson = "{\n" +
                "  \"id\": 5678,\n" +
                "  \"name\": \"TestPetUpdated\",\n" +
                "  \"status\": \"sold\"\n" +
                "}";

        given()
            .contentType(ContentType.JSON)
            .body(updateJson)
        .when()
            .put("/pet")
        .then()
            .statusCode(200)
            .body("id", equalTo(5678))
            .body("name", equalTo("TestPetUpdated"))
            .body("status", equalTo("sold"));
    }

    //Pesquisar por pets com status “pending” (GET /pet/findByStatus) 
    @Test
    public void findPetsByStatus_Pending() {
        given()
            .queryParam("status", "pending")
        .when()
            .get("/pet/findByStatus")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("size()", greaterThanOrEqualTo(0))
            .body("status", everyItem(equalTo("pending")));
    }
}
