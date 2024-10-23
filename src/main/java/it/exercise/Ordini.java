package it.exercise;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Table(name = "ordini")
@Entity
public class Ordini {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ordine")
    private int idOrdine;

    @ManyToOne
    @JoinColumn(name= "id_cliente", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<OrdiniProdotti> ordiniProdotti = new HashSet<>();

    public Ordini() {
    }

    public Ordini(int idOrdine, Cliente cliente, Set<OrdiniProdotti> ordiniProdotti) {
        this.idOrdine = idOrdine;
        this.cliente = cliente;
        this.ordiniProdotti = ordiniProdotti;
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<OrdiniProdotti> getOrdiniProdotti() {
        return ordiniProdotti;
    }

    public void setOrdiniProdotti(Set<OrdiniProdotti> ordiniProdotti) {
        this.ordiniProdotti = ordiniProdotti;
    }
}


