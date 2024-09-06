package praticeFeign.service;

import praticeFeign.model.Order;
import praticeFeign.model.reqest.OrderRequest;
import praticeFeign.model.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse insertOrder(OrderRequest orderRequest);

    List<OrderResponse> getAllOrder();

    OrderResponse getOrderById(long id);

    OrderResponse updateOrderById(long id, OrderRequest orderRequest);
    void deleteOrderById(long id);
}
