package com.algaworks.algafoodjavaclient.api;

import com.algaworks.algafoodjavaclient.model.RestauranteModel;
import com.algaworks.algafoodjavaclient.model.RestauranteResumoModel;
import com.algaworks.algafoodjavaclient.model.input.RestauranteInput;
import lombok.AllArgsConstructor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class RestauranteClient {
    
    private static final String RESOURCE_PATH = "/restaurantes";
    
    private String url;
    
    private RestTemplate restTemplate;
    
    public RestauranteModel adicionar(RestauranteInput restaurante){

        URI resourceUri = URI.create(url + RESOURCE_PATH);
        try {
            return restTemplate
                    .postForObject(resourceUri, restaurante, RestauranteModel.class);
        } catch (HttpClientErrorException e) {
            throw new ClientApiException(e.getMessage(), e);
        }


    }
    
    public List<RestauranteResumoModel> listar(){

        try {
            URI resourceUri = URI.create(url + RESOURCE_PATH);

            RestauranteResumoModel[] restaurantes = restTemplate
                    .getForObject(resourceUri, RestauranteResumoModel[].class);

            return Arrays.asList(restaurantes);
        } catch (RestClientResponseException e) {
            throw new ClientApiException(e.getMessage(), e);
        }
    }
}
