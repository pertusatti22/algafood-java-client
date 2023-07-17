package com.algaworks.algafoodjavaclient;

import com.algaworks.algafoodjavaclient.api.ClientApiException;
import com.algaworks.algafoodjavaclient.api.RestauranteClient;
import com.algaworks.algafoodjavaclient.model.RestauranteModel;
import com.algaworks.algafoodjavaclient.model.input.CidadeInput;
import com.algaworks.algafoodjavaclient.model.input.CozinhaInput;
import com.algaworks.algafoodjavaclient.model.input.EnderecoInput;
import com.algaworks.algafoodjavaclient.model.input.RestauranteInput;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class InclusaoRestaurantesMain {

    public static void main(String[] args) {
        try {
            var restTemplate = new RestTemplate();
            var restauranteClient = new RestauranteClient(
                    "http://localhost:8080", restTemplate);

            var cozinha = new CozinhaInput();
            cozinha.setId(1L);

            var cidade = new CidadeInput();
            cidade.setId(1L);

            var endereco = new EnderecoInput();
            endereco.setCidade(cidade);
            endereco.setCep("38500-111");
            endereco.setLogradouro("Rua Xyz");
            endereco.setNumero("300");
            endereco.setBairro("Centro");

            var restaurante = new RestauranteInput();
            restaurante.setNome("Comida Mineira");
            restaurante.setTaxaFrete(new BigDecimal(9.5));
            restaurante.setCozinha(new CozinhaInput());
            restaurante.setCozinha(cozinha);
            restaurante.setEndereco(endereco);

            RestauranteModel restauranteModel = restauranteClient.adicionar(restaurante);

            System.out.println(restauranteModel);
        } catch (ClientApiException e) {
            if (e.getProblema() != null) {
                System.out.println(e.getProblema().getUserMessage());

                e.getProblema().getObjects().stream()
                        .forEach(p -> System.out.println("- " + p.getUserMessage()));

            } else {
                System.out.println("Erro desconhecido");
                e.printStackTrace();
            }
        }
    }
} 