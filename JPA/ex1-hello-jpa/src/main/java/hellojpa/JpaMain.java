package hellojpa;

import javax.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("hello");
        EntityManager em=emf.createEntityManager();
        em.setFlushMode(FlushModeType.AUTO);
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        try{
            Team team=new Team();
            team.setName("TeamA");
            em.persist(team);
            Member member=new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member);
            Member findMember=em.find(Member.class,member.getId());
            Team findTeam=findMember.getTeam();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
