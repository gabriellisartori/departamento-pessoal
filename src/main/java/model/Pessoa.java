package model;

import java.time.LocalDate;
import java.util.Objects;

class Pessoa {
    private String nome;
    private LocalDate dataNascimento;

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
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

    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Pessoa pessoa = (Pessoa) object;
        return java.util.Objects.equals(getNome(), pessoa.getNome()) && java.util.Objects.equals(getDataNascimento(), pessoa.getDataNascimento());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getNome(), getDataNascimento());
    }
}
