package rocks.gebsattel.hochzeit.services

import rocks.gebsattel.hochzeit.domain.DomainObject

abstract class AbstractMapService {

    protected Map<Integer, DomainObject> domainMap

    AbstractMapService(){
        domainMap = new HashMap<>()
        loadDomainObjects()
    }

    List<DomainObject> listAll() {
        return new ArrayList<>(domainMap.values())
    }

    DomainObject getById(Integer id) {
        return domainMap.get(id)
    }

    DomainObject saveOrUpdate(DomainObject domainObject) {
        if(domainObject != null){
            if(domainObject.getId() == null){
                domainObject.setId(getNextKey())
            }
            domainMap.put(domainObject.getId(), domainObject)
            return domainObject
        } else {
            throw new RuntimeException("Object can't be null")
        }
    }

    void delete(Integer id) {
        domainMap.remove(id)
         }

    private Integer getNextKey(){
        return Collections.max(domainMap.keySet()) + 1
    }

    protected abstract void loadDomainObjects()

}
