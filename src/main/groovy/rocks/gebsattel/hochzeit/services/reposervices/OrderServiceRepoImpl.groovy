package rocks.gebsattel.hochzeit.services.reposervices

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import rocks.gebsattel.hochzeit.domain.Order
import rocks.gebsattel.hochzeit.repositories.OrderRepository
import rocks.gebsattel.hochzeit.services.OrderService

@Service
@Profile("springdatajpa")
class OrderServiceRepoImpl implements OrderService {

    OrderRepository orderRepository

    @Autowired
    void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository
    }

    @Override
    List<?> listAll(){
        List<Order> orders = new ArrayList<>()
        orderRepository.findAll().each{ orders.add(it) }
        return orders
    }

    @Override
    Order getById(Integer id) { orderRepository.findById(id).get() }

    @Override
    Order saveOrUpdate(Order domainObject) { orderRepository.save(domainObject) }

    @Override
    void delete(Integer id) {
        orderRepository.delete(id)
    }
}
