package com.ticketBooking.client;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketBooking.dto.CustomerDTO;
import com.ticketBooking.entity.Ticket;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;
import java.time.LocalDate;
import java.util.List;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.URI;


import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
@Component
public class APIConfig {


    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {


        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setFirstName("shaurabh");
        customerDTO.setLastName("singh");
        customerDTO.setDateOfBirth(LocalDate.of(1996, 1, 9));

        customerDTO.setAddress("987 Cedar Ln, Bigtown, USA");
        customerDTO.setEmail("customerF@example.com");
        customerDTO.setPhone("7778889995");

        addProductDetails(customerDTO);

        findAll();

    }


    public static void findAll() throws IOException, InterruptedException {


        var url = "http://localhost:8080/api/accounts/getTransactionHistory?accountNumber=8501535618&startDate=2024-10-24&endDate=2024-10-30";
        //  var url = "http://localhost:8080/api/product/findAllProducts";


        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();

        var client = HttpClient.newBuilder().build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());


    }


    public static void addProductDetails(CustomerDTO customerDTO) throws IOException, InterruptedException, URISyntaxException {
        String BASE_URL = "http://localhost:8080/api/customer/addCustomer";
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();


        objectMapper.registerModule(new JavaTimeModule());


        String jsonData = objectMapper.writeValueAsString(customerDTO);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response from /add: " + response.body());

    }


}
