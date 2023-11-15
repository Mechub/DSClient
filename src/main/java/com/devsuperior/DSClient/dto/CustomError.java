package com.devsuperior.DSClient.dto;

import java.time.Instant;

public class CustomError {

    private Instant timestemp;
    private Integer status;
    private String erro;
    private String path;

    public CustomError(Instant timestemp, Integer status, String erro, String path) {
        this.timestemp = timestemp;
        this.status = status;
        this.erro = erro;
        this.path = path;
    }

    public Instant getTimestemp() {
        return timestemp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getErro() {
        return erro;
    }

    public String getPath() {
        return path;
    }




}
