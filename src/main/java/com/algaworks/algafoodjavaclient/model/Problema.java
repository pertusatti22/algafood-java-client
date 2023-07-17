package com.algaworks.algafoodjavaclient.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Problema {
    
    private Integer status;
    
    private OffsetDateTime timestamp;
    
    private String userMessage;
}
