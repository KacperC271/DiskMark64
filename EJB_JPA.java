package EJB_JPA;


import ENTITY.ENTITY_MD5;
import jakarta.ejb.Stateful;
import jakarta.persistence.*;
import java.util.List;

@Stateful
public class EJB_JPA {
    public void SAVE(String InputText, String MD5Hash) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
            ENTITY_MD5 ENTITY = new ENTITY_MD5(InputText, MD5Hash, "MD5");
            entityManager.persist(ENTITY);
            entityManager.getTransaction().commit();
            entityManager.close();
            emf.close();
        }
        public List<ENTITY_MD5> HISTORY() {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
            EntityManager entityManager = emf.createEntityManager();
            String qer = "SELECT h FROM ENTITY_MD5 h";
            TypedQuery<ENTITY_MD5> query = entityManager.createQuery(qer, ENTITY_MD5.class);
            return query.getResultList();
        }
    public void DELETE(String TEXT_BEFORE_HASH) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            TypedQuery<ENTITY_MD5> query = entityManager.createQuery(
                    "SELECT e FROM ENTITY_MD5 e WHERE e.TEXT_BEFORE_HASH = :TEXT_BEFORE_HASH_1", ENTITY_MD5.class);
            query.setParameter("TEXT_BEFORE_HASH_1", TEXT_BEFORE_HASH);

            List<ENTITY_MD5> entitiesToDelete = query.getResultList();

            for (ENTITY_MD5 entity : entitiesToDelete) {
                entityManager.remove(entity);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
            emf.close();
        }
    }
}
