package fr.epsib32526;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        try(
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
            EntityManager em = emf.createEntityManager()
        ) {
            System.out.println(emf);

            // Affichage de livre à travers son id
            int id = 1;
            Livre livre = em.find(Livre.class, id);

            if (livre != null) {
                System.out.println("Titre : " + livre.getTitre());
                System.out.println("Auteur : " + livre.getAuteur());
            }else {
                System.out.println("Livre avec l'id " + id + " n'existe pas.");
            }

            // Insertion d'un nouveau livre
            em.getTransaction().begin();
            Livre nouveauLivre = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry");
            em.persist(nouveauLivre);
            em.getTransaction().commit();

            // Modifier un livre existant
            em.getTransaction().begin();
            Livre livreAModifier = em.find(Livre.class, 5);
            if (livreAModifier != null) {
                livreAModifier.setTitre("Du plaisir dans la cuisine");
            }
            em.getTransaction().commit();
        }
    }

}