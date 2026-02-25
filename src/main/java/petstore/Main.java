package petstore;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("petstore");
             var em = emf.createEntityManager()) {

            System.out.println("Début");
            em.getTransaction().begin();


            Address adresse = new Address();
            adresse.setStreet("123 Main St");
            adresse.setCity("Anytown");
            adresse.setZipCode("12345");
            adresse.setNumber("123");
            em.persist(adresse);

            PetStore boutique = new PetStore();
            boutique.setName("Boutique");
            boutique.setManagerName("manager");
            boutique.setAddress(adresse);
            em.persist(boutique);

            Fish fish = new Fish();
            fish.setLivingEnv(Animal.FishLivEnv.FRESH_WATER);
            fish.setBirth(new Date());
            fish.setCouleur("bleu");
            fish.setPetStore(boutique);
            em.persist(fish);


            Cat cat = new Cat();
            cat.setChipId("ssss");
            cat.setBirth(new Date());
            cat.setCouleur("brun");
            cat.setPetStore(boutique);
            em.persist(cat);

            Product p1 = new Product();
            p1.setCode("ccc");
            p1.setLabel("aaaa");
            p1.setPrice(1.99);
            p1.setPetStore(boutique);
            em.persist(p1);

            em.getTransaction().commit();


            // Requete qui permet d'extraire tous les animaux d'une boutique donnée

            String nomBoutique = "Boutique";

            List<Animal> animals = em.createQuery(
                            "SELECT a FROM Animal a WHERE a.petStore.name = :name", Animal.class)
                    .setParameter("name", nomBoutique)
                    .getResultList();

            System.out.println("Animaux de la boutique" + nomBoutique + " :");
            animals.forEach(a -> System.out.println(" - " + a.getClass().getSimpleName() + " (Couleur: " + a.getCouleur() + ")"));

            System.out.println("Fin");

        }catch (Exception ex) {
            System.out.println("Erreur : " + ex.getMessage());
        }



    }

}
