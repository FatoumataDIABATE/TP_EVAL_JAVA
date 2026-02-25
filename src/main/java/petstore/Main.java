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


            // Insertion des adresses
            Address adresse1 = new Address();
            adresse1.setStreet("10 Rue de la République");
            adresse1.setCity("Paris");
            adresse1.setZipCode("75001");
            adresse1.setNumber("10");
            em.persist(adresse1);

            Address adresse2 = new Address();
            adresse2.setStreet("5 Avenue Victor Hugo");
            adresse2.setCity("Lyon");
            adresse2.setZipCode("69002");
            adresse2.setNumber("5");
            em.persist(adresse2);

            Address adresse3 = new Address();
            adresse3.setStreet("12 Boulevard Saint-Michel");
            adresse3.setCity("Marseille");
            adresse3.setZipCode("13005");
            adresse3.setNumber("12");
            em.persist(adresse3);

            // Insertion des petStores
            PetStore boutique1 = new PetStore();
            boutique1.setName("Animalia Paris");
            boutique1.setManagerName("Sophie Martin");
            boutique1.setAddress(adresse1);
            em.persist(boutique1);

            PetStore boutique2 = new PetStore();
            boutique2.setName("La Ménagerie Lyon");
            boutique2.setManagerName("Julien Dupont");
            boutique2.setAddress(adresse2);
            em.persist(boutique2);

            PetStore boutique3 = new PetStore();
            boutique3.setName("Compagnie des Animaux Marseille");
            boutique3.setManagerName("Claire Bernard");
            boutique3.setAddress(adresse3);
            em.persist(boutique3);

            // Insertion des animaux
            Fish fish1 = new Fish();
            fish1.setLivingEnv(Fish.FishLivEnv.FRESH_WATER);
            fish1.setBirth(new Date());
            fish1.setCouleur("rouge");
            fish1.setPetStore(boutique1);
            em.persist(fish1);

            Fish fish2 = new Fish();
            fish2.setLivingEnv(Fish.FishLivEnv.SEA_WATER);
            fish2.setBirth(new Date());
            fish2.setCouleur("bleu");
            fish2.setPetStore(boutique2);
            em.persist(fish2);

            Fish fish3 = new Fish();
            fish3.setLivingEnv(Fish.FishLivEnv.FRESH_WATER);
            fish3.setBirth(new Date());
            fish3.setCouleur("jaune");
            fish3.setPetStore(boutique3);
            em.persist(fish3);

            Cat cat1 = new Cat();
            cat1.setChipId("CH123FR");
            cat1.setBirth(new Date());
            cat1.setCouleur("noir");
            cat1.setPetStore(boutique1);
            em.persist(cat1);

            Cat cat2 = new Cat();
            cat2.setChipId("CH456FR");
            cat2.setBirth(new Date());
            cat2.setCouleur("blanc");
            cat2.setPetStore(boutique2);
            em.persist(cat2);

            Cat cat3 = new Cat();
            cat3.setChipId("CH789FR");
            cat3.setBirth(new Date());
            cat3.setCouleur("gris");
            cat3.setPetStore(boutique3);
            em.persist(cat3);

            // Insertion des produits
            Product p1 = new Product();
            p1.setCode("PA001");
            p1.setLabel("Croquettes pour chat");
            p1.setPrice(9.99);
            p1.setPetStore(boutique1);
            p1.setType(Product.ProdType.FOOD);
            em.persist(p1);

            Product p2 = new Product();
            p2.setCode("PA002");
            p2.setLabel("Nourriture pour poisson rouge");
            p2.setPrice(4.50);
            p2.setPetStore(boutique2);
            p2.setType(Product.ProdType.ACCESSORY);
            em.persist(p2);

            Product p3 = new Product();
            p3.setCode("PA003");
            p3.setLabel("Litière naturelle pour chat");
            p3.setPrice(6.75);
            p3.setPetStore(boutique3);
            p3.setType(Product.ProdType.CLEANING);
            em.persist(p3);

            em.getTransaction().commit();


            // Requete qui permet d'extraire tous les animaux d'une boutique donnée

            String nomBoutique = "La Ménagerie Lyon";

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
