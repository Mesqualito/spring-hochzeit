package rocks.gebsattel.hochzeit.domain

import rocks.gebsattel.hochzeit.enums.OrderStatus

import javax.persistence.CascadeType
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity
class Order extends AbstractDomainClass {

    @OneToOne
    Customer customer

    @Embedded
    Address shipToAddress

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    List<OrderDetail> orderDetails = new ArrayList()

    OrderStatus orderStatus
    Date dateShipped

    void addToOrderDetails(OrderDetail orderDetail){
        orderDetail.setOrder(this)
        orderDetails.add(orderDetail)
    }

    void removeOrderDetail(OrderDetail orderDetail){
        orderDetail.setOrder(null)
        orderDetails.remove(orderDetail)
    }
}
