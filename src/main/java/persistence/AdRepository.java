package persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public class AdRepository implements Store, AutoCloseable {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {

        private static Store inst;
    }

    private AdRepository() {
    }

    public static Store instOf() {
        if (Lazy.inst == null) {
            Lazy.inst = new AdRepository();
        }
        return Lazy.inst;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List findRecent() {
        return this.tx(
                session -> {
                    final Query query = session.createQuery("select distinct a FROM model.Ad a "
                            + "join fetch a.photos WHERE a.created BETWEEN :startDate AND :endDate");
                    var now = Timestamp.valueOf(LocalDateTime.now());
                    var oneDayAgo = Timestamp.valueOf(LocalDateTime.now().plusDays(-1));
                    query.setParameter("startDate", oneDayAgo);
                    query.setParameter("endDate", now);
                    return query.list();
                }
        );
    }

    @Override
    public List findWithPhoto() {
        return this.tx(
                session -> {
                    final Query query = session.createQuery("select distinct a FROM model.Ad a "
                            + "join fetch a.photos p WHERE p is not null");
                    return query.list();
                }
        );
    }

    @Override
    public List findByBrand(String brandName) {
        return this.tx(
                session -> {
                    final Query query = session.createQuery("select distinct a FROM model.Ad a "
                            + "LEFT JOIN FETCH a.photos WHERE a.brand.name = :name");
                    query.setParameter("name", brandName);
                    return query.list();
                }
        );
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
