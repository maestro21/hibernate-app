package com.flamexander.hibernate.dao;

import org.hibernate.Session;

import java.io.Serializable;

public class AbstractDao<T, ID extends Serializable> {
    protected Class<T> typeClass;
    protected Class<ID> idClass;

    public AbstractDao(Class<T> typeClass, Class<ID> idClass) {
        this.typeClass = typeClass;
        this.idClass = idClass;
    }

    public T findById(ID id) {
        Session session = null;
        return session.get(typeClass, id);
    }
}
