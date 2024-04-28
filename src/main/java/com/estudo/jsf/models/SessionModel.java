package com.estudo.jsf.models;

import com.estudo.jsf.db.DBManager;

public class SessionModel {
    private String nome;
    private String senha;

    public SessionModel(String senha, String nome) {
        this.senha = senha;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public boolean isAdmin() {
        return DBManager.isInWhiteList(this);
    }
}
