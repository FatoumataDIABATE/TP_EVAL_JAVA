package fr.epsib32526;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

public class TestBibliotheque {

    // Requête qui permet d’extraire un emprunt et tous ses livres associés

    Integer empruntId = 1;
    public static void main(String[] args) {
        try (
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
                EntityManager em = emf.createEntityManager()
        ) {

            Integer empruntId = 1;
            // JPQL avec FETCH JOIN pour charger l'emprunt et sa collection de livres en une seule requête
            Emprunt emprunt = em.createQuery(
                            "SELECT e FROM Emprunt e LEFT JOIN FETCH e.livres WHERE e.id = :id", Emprunt.class)
                    .setParameter("id", empruntId)
                    .getSingleResult();

            if (emprunt != null) {
                System.out.println("Emprunt: " + empruntId);
                emprunt.getLivres().forEach(l -> System.out.println(" - Titre: " + l.getTitre() + ", Auteur: " + l.getAuteur()));
            } else {
                System.out.println("Aucun emprunt trouvé pour l'id " + empruntId);
            }

            // Requête qui permet d’extraire tous les emprunts d’un client donné
            Integer clientId = 1;
            Client client = em.createQuery(
                            "SELECT c FROM Client c LEFT JOIN FETCH c.emprunts WHERE c.id = :id", Client.class)
                    .setParameter("id", clientId)
                    .getSingleResult();

            if (client != null) {
                System.out.println("Emprunts du client: " + client.getPrenom() + " " + client.getNom());
                client.getEmprunts().forEach(e -> System.out.println(" - Emprunt ID: " + e.getId()));
            } else {
                System.out.println("Aucun client trouvé pour l'id " + clientId);
            }

        }


    }


}
