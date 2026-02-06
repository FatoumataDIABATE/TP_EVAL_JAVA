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

            // Requête JPQL pour extraire de la base un livre en fonction de son titre
            String titreRecherche = "Germinal";
            Livre livreTrouve = em.createQuery("SELECT l FROM Livre l WHERE l.titre = :titre", Livre.class)
                                    .setParameter("titre", titreRecherche)
                                    .getSingleResult();
            if (livreTrouve != null) {
                System.out.println("Livre trouvé : " + livreTrouve.getTitre() + " , Auteur : " + livreTrouve.getAuteur());
            }else {
                System.out.println("Aucun livre trouvé avec le titre : " + titreRecherche);
            }

            // Requête JPQL pour extraire de la base un livre en fonction de son auteur
            String auteurRecherche = "Jules Verne";
            Livre livreTrouveParAuteur = em.createQuery("SELECT l FROM Livre l WHERE l.auteur = :auteur", Livre.class)
                                            .setParameter("auteur", auteurRecherche)
                                            .getSingleResult();
            if (livreTrouveParAuteur != null) {
                System.out.println("Livre trouvé : " + livreTrouveParAuteur.getTitre() + " , Auteur : " + livreTrouveParAuteur.getAuteur());
            }else {
                System.out.println("Aucun livre trouvé pour l'auteur : " + auteurRecherche);
            }


            // Suppression d'un livre
            em.getTransaction().begin();
            Livre livreASupprimer = em.find(Livre.class, 3);
            if (livreASupprimer != null) {
                em.remove(livreASupprimer);
                System.out.println("Livre avec l'id 3 supprimé.");
            } else {
                System.out.println("Livre avec l'id 3 n'existe pas.");
            }

            em.getTransaction().commit();

            // Affichage de tous les livres
            System.out.println("Liste de tous les livres :");
            em.createQuery("SELECT l FROM Livre l", Livre.class)
                .getResultList()
                .forEach(l -> System.out.println("Titre : " + l.getTitre() + " , Auteur : " + l.getAuteur()));
        }
    }

}