package com.curso.ecommerce.pasarelaPaypal.webapp.source.payments.servlet;

public class Z2SaveCreditCard {

    private String numero;
    private String nombres;
    private String CVV;


    public Z2SaveCreditCard() {
    }

    public Z2SaveCreditCard(String numero, String nombres, String CVV) {
        this.numero = numero;
        this.nombres = nombres;
        this.CVV = CVV;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }
}
