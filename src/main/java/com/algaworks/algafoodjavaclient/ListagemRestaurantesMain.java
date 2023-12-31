package com.algaworks.algafoodjavaclient;

import com.algaworks.algafoodjavaclient.api.ClientApiException;
import com.algaworks.algafoodjavaclient.api.RestauranteClient;
import org.springframework.web.client.RestTemplate;

public class ListagemRestaurantesMain {

    public static void main(String[] args) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            RestauranteClient restauranteClient = new RestauranteClient("http://localhost:8080", restTemplate);

            restauranteClient
                    .listar()
                    .stream()
                    .forEach(restaurante -> System.out.println(restaurante));
        } catch (ClientApiException e) {
            if (e.getProblema() != null) {
                System.out.println(e.getProblema().getUserMessage());
            } else {
                System.out.println("Erro desconhecido");
                e.printStackTrace();
            }
        }
    }
}
