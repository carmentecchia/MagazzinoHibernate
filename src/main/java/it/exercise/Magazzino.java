package it.exercise;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Magazzino {

    public void vediProdotti() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Prodotti> query = builder.createQuery(Prodotti.class);
            Root<Prodotti> root = query.from(Prodotti.class);
            query.select(root);

            List<Prodotti> prodotti = session.createQuery(query).getResultList();

            if (prodotti.isEmpty()) {
                System.out.println("Nessun prodotto.");
            } else {
                for (Prodotti prodotto : prodotti) {
                    System.out.println("id prodotto: " + prodotto.getIdProdotto() +
                            ", nome: " + prodotto.getNome() + ", quantità: " + prodotto.getQuantita());
                }
            }
        } catch (Exception e){
            System.out.println("Visualizzazione non riuscita");
        }
    }
    public void aggiungiProdotto(Integer idMagazzino, int quantita, Integer idCategoria, String nome, String dataIns, String dataMod, Double prezzo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Prodotti nuovoProdotto = new Prodotti();
            nuovoProdotto.setIdMagazzino(idMagazzino);
            nuovoProdotto.setQuantita(quantita);
            nuovoProdotto.setIdCategoria(idCategoria);
            nuovoProdotto.setNome(nome);
            nuovoProdotto.setDataIns(dataIns);
            nuovoProdotto.setDataMod(dataMod);
            nuovoProdotto.setPrezzo(prezzo);

            session.save(nuovoProdotto);
            transaction.commit();
            System.out.println("Aggiunto nuovo prodotto: " + nome);
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Errore nell'aggiunta del prodotto: " + e.getMessage());
        } finally {
            session.close();
        }
    }


    public void eliminaProdotto(int idProdotto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Prodotti prodotto = session.get(Prodotti.class, idProdotto);
            if (prodotto != null) {
                session.delete(prodotto);
                transaction.commit();
                System.out.println("Prodotto eliminato");
            } else {
                System.out.println("Nessun prodotto trovato");
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Errore: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void aggiungiCliente(int idCliente, String nome, String email, String telefono) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Cliente nuovoCliente = new Cliente();
            nuovoCliente.setIdCliente(idCliente);
            nuovoCliente.setNome(nome);
            nuovoCliente.setEmail(email);
            nuovoCliente.setTelefono(telefono);

            session.save(nuovoCliente);

            transaction.commit();
            System.out.println("Cliente aggiunto con successo");
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Errore nell'aggiunta del cliente: " + e.getMessage());
        } finally {
            session.close();
        }
    }


    public void creaOrdine(int idCliente, List<Integer> idProdotti, List<Integer> quantita) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Cliente cliente = session.get(Cliente.class, idCliente);
            if (cliente == null) {
                System.out.println("Cliente con ID " + idCliente + " non trovato.");
                return;
            }

            Ordini nuovoOrdine = new Ordini();
            nuovoOrdine.setCliente(cliente);
            session.save(nuovoOrdine);

            for (int i = 0; i < idProdotti.size(); i++) {
                Integer idProdotto = idProdotti.get(i);
                Integer quantitaProdotto = quantita.get(i);


                Prodotti prodotto = session.get(Prodotti.class, idProdotto);
                if (prodotto != null) {
                    Integer quantitaDisponibile = prodotto.getQuantita();


                    if (quantitaDisponibile != null && quantitaDisponibile >= quantitaProdotto) {
                        prodotto.setQuantita(quantitaDisponibile - quantitaProdotto);
                        session.update(prodotto);

                        OrdiniProdotti ordineProdotto = new OrdiniProdotti();
                        ordineProdotto.setOrdine(nuovoOrdine);
                        ordineProdotto.setProdotto(prodotto);
                        ordineProdotto.setQuantita(quantitaProdotto);
                        session.save(ordineProdotto);
                    } else {
                        System.out.println("Quantità non sufficiente" + idProdotto);
                    }
                } else {
                    System.out.println("Prodotto con ID " + idProdotto + " non trovato");
                }
            }

            transaction.commit();
            System.out.println("Ordine creato con successo");
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Errore durante la creazione dell'ordine");
        } finally {
            session.close();
        }
    }


    public void eliminaOrdine(int idOrdine) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Ordini ordine = session.get(Ordini.class, idOrdine);
            if (ordine != null) {
                session.delete(ordine);
                transaction.commit();
                System.out.println("Ordine eliminato");
            } else {
                System.out.println("Ordine non trovato");
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Errore durante l'eliminazione dell'ordine: " + e.getMessage());
        } finally {
            session.close();
        }
    }


}

