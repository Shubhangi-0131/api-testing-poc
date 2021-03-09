import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import io.restassured.http.Headers;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import petclinic.api.onlineshop.OnlineshopApiClient;
import petclinic.api.onlineshop.data.OrderSummary;
import petclinic.api.onlineshop.data.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class OnlineshopApiTest {

    static String apiUrl;
    
    @BeforeAll
    static void getApiUrl() {
        apiUrl = System.getProperty("apiUrl");
    }
    //Status code
    @Test
    public void getServices_StatusCode() throws InvalidResponseException {
    OnlineshopApiClient client = new OnlineshopApiClient(apiUrl, "/api/shop/getproducts");
    int statusCode = client.getStatus();
        SoftAssertions softly = new SoftAssertions();
    softly.assertThat(statusCode).isEqualTo(200);
        softly.assertAll();

}
    //Header verfication
     @Test
    public void getServices_Header() throws InvalidResponseException {
    OnlineshopApiClient client = new OnlineshopApiClient(apiUrl, "/api/shop/getproducts");

    SoftAssertions softly = new SoftAssertions();

    Headers contentType = client.getHeader("Content-Type");
    softly.assertThat(contentType ).isNotNull() ;


    softly.assertAll();

}

    //Get Product details
    @Test
    public void getProduct_DefaultLimit_ShouldReturn10Results() throws InvalidResponseException {
        OnlineshopApiClient client = new OnlineshopApiClient(apiUrl, "/api/shop/getproducts");
        Response[] getproduct = client.getResponses();

        SoftAssertions softly = new SoftAssertions();
        System.out.println("The value at index 1" + getproduct[0]);
        softly.assertThat(getproduct[0].getAmount()).as("the Amount is  635:").isEqualTo("635.00");
        softly.assertThat(getproduct[1].getId()).as("the given ID is :  ").isEqualTo("2");
        softly.assertThat(getproduct[2].getId()).as("ID's should be unique").isNotSameAs(getproduct[3].getId());
        softly.assertThat(getproduct[0].getName()).as("the name is Kennel Kitchen:").isEqualTo("Kennel Kitchen");
        softly.assertThat(getproduct[0].getCurrency()).as("the currency is dollar:").isEqualTo("$");
        softly.assertAll();

    }
    //Create Product using property file and hardcoding value
    @Test
        public void createProduct_checkId_ShouldReturnNewProduct () throws InvalidResponseException, IOException {

        Properties pro = new Properties();
        FileInputStream file = new FileInputStream("src/main/java/petclinic/api/onlineshop/PostData.properties");
        pro.load(file);
            OnlineshopApiClient client = new OnlineshopApiClient(apiUrl, "/api/shop/addtocart/");

          /* Response createdProduct = client.createdProduct(Response.builder()
                    .name("Shubhangi")
                    .amount("100.00")
                    .currency("INR")
                    .id("60").build());*/


/*
            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(createdProduct.getName()).as("First name should be Harry").isEqualTo("Shubhangi");
            softly.assertThat(createdProduct.getAmount()).as("First name should be Harry").isEqualTo("100.00");
            softly.assertThat(createdProduct.getCurrency()).as("First name should be Harry").isEqualTo("INR");
            softly.assertThat(createdProduct.getId()).as("A unique ID should be populated").isNotBlank();
            softly.assertThat(createdProduct.getId()).as("Id is different than the existing one's").isGreaterThan("1");

            softly.assertAll();*/

        // Iterating properties using Java 8 forEach

        pro.forEach((key, value) -> System.out.println(key + " : " + value));
        Response createdProduct = client.createdProduct(Response.builder()
                .name(pro.getProperty("name"))
                .amount(pro.getProperty("amount"))
                .currency(pro.getProperty("currency"))
                .id(pro.getProperty("id")).build());




        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(createdProduct.getName()).as("Name should be SHUBHANGI").isEqualTo(pro.getProperty("name"));
        softly.assertThat(createdProduct.getId()).as("A unique ID should be populated").isNotBlank();
        softly.assertThat(createdProduct.getId()).as("Id is different than the existing one's").isGreaterThan("1");
        softly.assertThat(createdProduct.getAmount()).as("Amount should be 100.00").isEqualTo(pro.getProperty("amount"));
        softly.assertThat(createdProduct.getCurrency()).as("Currency should be INR").isEqualTo(pro.getProperty("currency"));

        softly.assertAll();

        //json file
        /*JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("//cdn.crunchify.com/Users/Shared/crunchify.json"));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray companyList = (JSONArray) jsonObject.get("Company List");

            // An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
            // Iterators differ from enumerations in two ways:
            // 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
            // 2. Method names have been improved.
            Iterator<JSONObject> iterator = companyList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }*/

        }
     //Get Order Summary


    @Test
    public void getOrdersSummary_fromCart() throws InvalidResponseException {
        OnlineshopApiClient client = new OnlineshopApiClient(apiUrl,"/api/shop/addtocart");
        Response createdProduct = client.createdProduct(Response.builder()
                .name("TestOrderSummary")
                .amount("500.00")
                .currency("$")
                .id("4")
                .build());

        OnlineshopApiClient clientRequest = new OnlineshopApiClient(apiUrl,"/api/shop/ordersummary");
        OrderSummary order = clientRequest.getOrderSummary().getContent();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(createdProduct.getName()).as(" name should be TestOrderSummary").isEqualTo("TestOrderSummary");
        softly.assertThat(order.getTotalAmount()).isNotNull();
        softly.assertAll();

    }
    //Delete Product
        @Test

        public void deleteNewOwners_throughID_ShouldDisplayDeletedId() throws InvalidResponseException, IOException {
            //create a new product


            OnlineshopApiClient client = new OnlineshopApiClient(apiUrl, "/api/shop/addtocart/");

            Response createdProduct = client.createdProduct(Response.builder()
                    .name("Shubhangi")
                    .amount("100.00")
                    .currency("INR")
                    .id("61").build());




            //delete the created product
            OnlineshopApiClient client1 = new OnlineshopApiClient(apiUrl, "/api/shop/clearcart" );
            ApiResponse<Response[]> deletedProduct = client1.deleteProduct();


            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(deletedProduct.getHttpStatusCode()).as("The Status code is :").isEqualTo(200);
            softly.assertAll();

            
        }

        

    }
