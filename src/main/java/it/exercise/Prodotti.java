package it.exercise;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "prodotti")
public class Prodotti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_prodotto")
    private int idProdotto;

    @Column(name="id_mag")
    private Integer idMagazzino;

    @Column(name = "quantita")
    private int quantita;

    @Column(name="id_categoria")
    private Integer idCategoria;

    @Column(name="nome")
    private String nome;

    @Column(name="data_ins")
    private String dataIns;

    @Column(name="data_modifica")
    private String dataMod;

    @Column(name = "prezzo")
    private Double prezzo;

    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<OrdiniProdotti> prodottiOrdini = new HashSet<>();

    public Prodotti() {
    }

    public Prodotti(int idProdotto, Integer idMagazzino, int quantita, Integer idCategoria, String nome, String dataIns, String dataMod, Double prezzo, Set<OrdiniProdotti> prodottiOrdini) {
        this.idProdotto = idProdotto;
        this.idMagazzino = idMagazzino;
        this.quantita = quantita;
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.dataIns = dataIns;
        this.dataMod = dataMod;
        this.prezzo = prezzo;
        this.prodottiOrdini = prodottiOrdini;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public Integer getIdMagazzino() {
        return idMagazzino;
    }

    public void setIdMagazzino(Integer idMagazzino) {
        this.idMagazzino = idMagazzino;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataIns() {
        return dataIns;
    }

    public void setDataIns(String dataIns) {
        this.dataIns = dataIns;
    }

    public String getDataMod() {
        return dataMod;
    }

    public void setDataMod(String dataMod) {
        this.dataMod = dataMod;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public Set<OrdiniProdotti> getProdottiOrdini() {
        return prodottiOrdini;
    }

    public void setProdottiOrdini(Set<OrdiniProdotti> prodottiOrdini) {
        this.prodottiOrdini = prodottiOrdini;
    }
}
