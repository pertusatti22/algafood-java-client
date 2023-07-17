package com.algaworks.algafoodjavaclient;

import com.algaworks.algafoodjavaclient.api.RestauranteClient;
import org.springframework.web.client.RestTemplate;

public class ListagemRestaurantesMain {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        
        RestauranteClient restauranteClient = new RestauranteClient("http://localhost:8080", restTemplate);
        
        restauranteClient
                .listar()
                .stream()
                .forEach(restaurante -> System.out.println(restaurante));
    }
}
