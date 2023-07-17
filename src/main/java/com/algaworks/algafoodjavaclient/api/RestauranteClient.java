package com.algaworks.algafoodjavaclient.api;

import com.algaworks.algafoodjavaclient.model.RestauranteResumoModel;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class RestauranteClient {
    
    private static final String RESOURCE_PATH = "/restaurantes";
    
    private String url;
    
    private RestTemplate restTemplate;
    
    public List<RestauranteResumoModel> listar(){
        
        URI resourceUri = URI.create(url + RESOURCE_PATH);

        RestauranteResumoModel[] restaurantes = restTemplate
                .getForObject(resourceUri, RestauranteResumoModel[].class);
        
        return Arrays.asList(restaurantes);
    }
}
