package org.benedict.library.Dao;

import java.util.List;

public interface genericDAO<T> {
    /**
     * Find entity by ID
     */
    T findById(int id);


    /**
     * Update entity
     * @param entity
     */

    void update(T entity);

    /**
     * Delete an entity
     * @param id
     */

    void delete(int id);

    /**
     * Find all entities by type
     */

    List<T> findAll();




}
