package it.exercise;

import jakarta.persistence.*;

import java.util.Set;

@Table(name="cliente")
@Entity
public class Cliente {

   @Id
   @Column(name = "id_cliente")
   private int idCliente;

   @OneToMany(mappedBy = "cliente")
   private Set<Ordini> ordini;

   @Column(name = "nome")
    private String nome;

   @Column(name = "email")
    private String email;

   @Column(name = "telefono")
    private String telefono;

    public Cliente() {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.telefono = telefono;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int id) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
