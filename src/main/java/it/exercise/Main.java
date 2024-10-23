package it.exercise;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Magazzino magazzino = new Magazzino();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nScegli un'operazione:");
            System.out.println("1 - Visualizza i prodotti");
            System.out.println("2 - Aggiungi un prodotto");
            System.out.println("3 - Aggiungi un cliente");
            System.out.println("4 - Elimina un prodotto");
            System.out.println("5 - Effettua un ordine");
            System.out.println("6 - Elimina un ordine");
            System.out.println("7 - Esci");
            System.out.print("Comando: ");
            String comando = scanner.nextLine();

            switch (comando) {
                case "1":
                    magazzino.vediProdotti();
                    break;

                case "2":
                    System.out.print("Inserisci id magazzino: ");
                    int idMagazzino = Integer.parseInt(scanner.nextLine());

                    System.out.print("Inserisci quantità: ");
                    int quantita = Integer.parseInt(scanner.nextLine());

                    System.out.print("Inserisci id categoria: ");
                    int idCategoria = Integer.parseInt(scanner.nextLine());

                    System.out.print("Inserisci nome del prodotto: ");
                    String nomeProdotto = scanner.nextLine();

                    System.out.print("Inserisci data di inserimento (yyyy-mm-dd): ");
                    String dataIns = scanner.nextLine();

                    System.out.print("Inserisci data di modifica (yyyy-mm-dd): ");
                    String dataMod = scanner.nextLine();

                    System.out.print("Inserisci prezzo: ");
                    Double prezzo = Double.parseDouble(scanner.nextLine());

                    magazzino.aggiungiProdotto(idMagazzino,quantita,idCategoria, nomeProdotto, dataIns, dataMod, prezzo);
                    break;

                case "3":
                    System.out.print("Inserisci id cliente: ");
                    int idCliente = Integer.parseInt(scanner.nextLine());

                    System.out.print("Inserisci nome cliente: ");
                    String nomeCliente = scanner.nextLine();

                    System.out.print("Inserisci email cliente: ");
                    String emailCliente = scanner.nextLine();

                    System.out.print("Inserisci telefono cliente: ");
                    String telefonoCliente = scanner.nextLine();

                    magazzino.aggiungiCliente(idCliente, nomeCliente, emailCliente, telefonoCliente);
                    break;

                case "4":
                    System.out.print("Inserisci id del prodotto da eliminare: ");
                    int idProdottoElimina = Integer.parseInt(scanner.nextLine());

                    magazzino.eliminaProdotto(idProdottoElimina);
                    break;

                case "5":
                    System.out.print("Inserisci id cliente: ");
                    int idClienteOrdine = Integer.parseInt(scanner.nextLine());

                    List<Integer> idProdotti = new ArrayList<>();
                    List<Integer> quantitaProdotti = new ArrayList<>();

                    while (true) {
                        System.out.print("Inserisci id prodotto (o 'fine' per terminare): ");
                        String idProdottoOrdine = scanner.nextLine();
                        if (idProdottoOrdine.equalsIgnoreCase("fine")) {
                            break;
                        }

                        System.out.print("Inserisci quantità: ");
                        int quantitaProdottoOrdine = Integer.parseInt(scanner.nextLine());

                        idProdotti.add(Integer.parseInt(idProdottoOrdine));
                        quantitaProdotti.add(quantitaProdottoOrdine);
                    }

                    try {
                        magazzino.creaOrdine(idClienteOrdine, idProdotti, quantitaProdotti);
                    } catch (Exception e) {
                        System.out.println("Errore nella creazione dell'ordine: " + e.getMessage());
                    }
                    break;

                case "6":
                    System.out.print("Inserisci id dell'ordine da eliminare: ");
                    int idOrdineElimina = Integer.parseInt(scanner.nextLine());

                    try {
                        magazzino.eliminaOrdine(idOrdineElimina);
                    } catch (Exception e) {
                        System.out.println("Errore nell'eliminazione dell'ordine: " + e.getMessage());
                    }
                    break;

                case "7":
                    System.out.println("Uscita dal programma");
                    scanner.close();
                    break;

                default:
                    System.out.println("Comando non valido. Riprovare.");
            }
        }
    }

}


