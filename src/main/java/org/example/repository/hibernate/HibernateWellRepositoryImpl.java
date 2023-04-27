package org.example.repository.hibernate;

import org.example.model.Well;
import org.example.repository.WellRepository;
import org.example.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateWellRepositoryImpl implements WellRepository {

    @Override
    public List<Well> getAll() {
        try (Session session = getSession()) {
            return session.createQuery("From Well", Well.class).list();
        }
    }

    @Override
    public Well create(Well well) {
        return saveWellToDB(well);
    }

    @Override
    public Well getById(Long id) {
        try (Session session = getSession()) {
            return session.get(Well.class, id);
        }
    }

    @Override
    public Well update(Well well) {
        return saveWellToDB(well);
    }

    @Override
    public void delete(Long id) {

        Transaction transaction = null;

        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.remove(getById(id));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Well getByName(String wellName) {
        List<Well> wells = getAll();

        return wells.stream()
                .filter(well -> well.getName().equalsIgnoreCase(wellName)).findFirst()
                .orElse(null);
    }

    private Well saveWellToDB(Well well) {
        Transaction transaction = null;

        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            well = session.merge(well);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return ((well.getId() != null) ? well : null);
    }

    private Session getSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }
}
