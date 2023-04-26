package org.example.repository.hibernate;

import org.example.model.Equipment;
import org.example.repository.EquipmentRepository;
import org.example.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateEquipmentRepositoryImpl implements EquipmentRepository {

    @Override
    public List<Equipment> getAll() {
        try (Session session = getSession()) {
            return session.createQuery("From Equipment", Equipment.class).list();
        }
    }

    @Override
    public Equipment create(Equipment equipment) {
        return saveEquipmentToDB(equipment);
    }

    @Override
    public Equipment getById(Long aLong) {
        return null;
    }

    @Override
    public Equipment update(Equipment equipment) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    private Equipment saveEquipmentToDB(Equipment equipment) {
        Transaction transaction = null;

        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            equipment = session.merge(equipment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return ((equipment.getId() != null) ? equipment : null);
    }

    private Session getSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }
}
