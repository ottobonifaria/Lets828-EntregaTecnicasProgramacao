package com.letscode.modeloentrega.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "TB_CLIENTES")
@Entity
public class Cliente {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "COD_CLI")
    private Integer codigo;

    @Column(name = "NOM_CLI", nullable = false)
    private String nome;

    @Column(name = "DAT_NASC_CLI", nullable = false)
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Column(name = "GEN_CLI", nullable = false)
    private Character genero;

    @Column(name = "QTD_VST", nullable = false)
    private Integer quantidadeVisitas;

    public Cliente() {}

    public Cliente(Integer codigo, String nome, LocalDate dataNascimento, Character genero, Integer quantidadeVisitas) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.quantidadeVisitas = quantidadeVisitas;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public Integer getQuantidadeVisitas() {
        return quantidadeVisitas;
    }

    public void setQuantidadeVisitas(Integer quantidadeVisitas) {
        this.quantidadeVisitas = quantidadeVisitas;
    }
}
