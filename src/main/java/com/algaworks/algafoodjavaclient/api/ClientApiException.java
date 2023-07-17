package com.algaworks.algafoodjavaclient.api;

import com.algaworks.algafoodjavaclient.model.Problema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientResponseException;

@Slf4j
public class ClientApiException extends RuntimeException{
    
    public static final long serialVersionUID = 1L;
    
    @Getter
    private Problema problema;
    
    public ClientApiException(String message, RestClientResponseException cause) {
        super(message, cause);
        
        deserializeProblema(cause);
    }
    
    private void deserializeProblema(RestClientResponseException cause){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.findAndRegisterModules();

        try {
            this.problema = mapper.readValue(cause.getResponseBodyAsString(), Problema.class);
        } catch (JsonProcessingException e) {
            log.warn("Não foi possível desserializar a resposta em um problema", e);
        }
    }
}
