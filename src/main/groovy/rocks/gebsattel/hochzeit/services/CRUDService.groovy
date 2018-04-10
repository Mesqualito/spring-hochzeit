package rocks.gebsattel.hochzeit.services

interface CRUDService<T> {
    List<?> listAll()

    T getById(Integer id)

    T saveOrUpdate(T domainObject)

    void delete(Integer id)

}