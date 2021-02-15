import domain.Member;

import javax.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");
        EntityManager em=emf.createEntityManager();
        em.setFlushMode(FlushModeType.AUTO);
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        try{
            Member member=new Member();
            member.setId(100L);
            member.setName("java");
            em.persist(member);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
