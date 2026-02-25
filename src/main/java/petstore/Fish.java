package petstore;


import jakarta.persistence.*;

@Entity
@Table(name = "fish")
public class Fish extends Animal {

    @Enumerated(EnumType.STRING)
    private FishLivEnv livingEnv;

    public FishLivEnv getLivingEnv() {
        return livingEnv;
    }

    public enum FishLivEnv {
        FRESH_WATER,
        SEA_WATER
    }
    public void setLivingEnv(FishLivEnv livingEnv) {
        this.livingEnv = livingEnv;
    }
}