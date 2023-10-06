package ENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "KC_MD5")
public class ENTITY_MD5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String TEXT_BEFORE_HASH;
    private String TEXT_AFTER_HASH;
    private String HASH_ALGORITHM;

    public ENTITY_MD5() {
    }
    public ENTITY_MD5(String TEXT_BEFORE_HASH, String TEXT_AFTER_HASH, String HASH_ALGORITHM){
        this.TEXT_BEFORE_HASH=TEXT_BEFORE_HASH;
        this.TEXT_AFTER_HASH=TEXT_AFTER_HASH;
        this.HASH_ALGORITHM=HASH_ALGORITHM;
    }
    public int Get_ID() {
        return this.ID;
    }
    public String GET_TEXT_BEFORE_HASH() {
        return this.TEXT_BEFORE_HASH;
    }

    public String Get_TEXT_AFTER_HASH() {
        return this.TEXT_AFTER_HASH;
    }



}
