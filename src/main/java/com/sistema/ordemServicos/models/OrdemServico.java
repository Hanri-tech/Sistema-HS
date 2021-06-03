package com.sistema.ordemServicos.models;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;


@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_colaborador")
    private Colaborador colaborador;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    private String titulo;
    private String descricao;
    private Date dataAbertura;
    private Date dataFechado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechado() {
        return dataFechado;
    }

    public void setDataFechado(Date dataFechado) {
        this.dataFechado = dataFechado;
    }
}
