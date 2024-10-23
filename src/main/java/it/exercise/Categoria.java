package it.exercise;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity(name="categoria")
public class Categoria {

    @Id
    @Column(name ="id_categoria")
    @ManyToMany
    public int idCategoria;

    @Column(name = "tipo")
    public String tipo;

    public Categoria(int idCategoria, String tipo) {
        this.idCategoria = idCategoria;
        this.tipo = tipo;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
