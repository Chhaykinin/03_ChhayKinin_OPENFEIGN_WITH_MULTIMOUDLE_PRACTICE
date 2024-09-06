package praticeFeign.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import praticeFeign.feign.CustomerServiceFeignClient;
import praticeFeign.feign.ProductServiceFeignClient;
import praticeFeign.model.Order;
import praticeFeign.model.reqest.OrderRequest;
import praticeFeign.model.response.APIResponse;
import praticeFeign.model.response.CustomerResponse;
import praticeFeign.model.response.OrderResponse;
import praticeFeign.model.response.ProductResponse;
import praticeFeign.repository.OrderRepository;
import praticeFeign.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerServiceFeignClient customerServiceFeignClient;
    private final ProductServiceFeignClient productServiceFeignClient;

    @Override
    public OrderResponse insertOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderDate(orderRequest.getOrderDate());

        APIResponse<CustomerResponse> customerResponse = customerServiceFeignClient.getCustomerById(orderRequest.getCustomerId());

        List<ProductResponse> responseList = new ArrayList<>();
        for (Long o : orderRequest.getProductIds()) {
            APIResponse<ProductResponse> productResponse = productServiceFeignClient.getProductById(o);
            if (productResponse != null) {
                responseList.add(productResponse.getPayload());
            }
            order.setProductIds(orderRequest.getProductIds());
            order.setCustomerId(orderRequest.getCustomerId());
            order.setOrderDate(orderRequest.getOrderDate());

            orderRepository.save(order);
        }

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setCustomerResponse(customerResponse.getPayload());
        orderResponse.setProductResponse(responseList);
        orderResponse.setLocalDate(order.getOrderDate());
        return orderResponse;
    }

    @Override
    public List<OrderResponse> getAllOrder() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            APIResponse<CustomerResponse> customer = customerServiceFeignClient.getCustomerById(order.getCustomerId());
            CustomerResponse customerResponse = customer != null ? customer.getPayload() : null;

            List<ProductResponse> productResponses = new ArrayList<>();

            for (Long productId : order.getProductIds()) {
                APIResponse<ProductResponse> productResponseAPIResponse = productServiceFeignClient.getProductById(productId);
                if (productResponseAPIResponse != null && productResponseAPIResponse.getPayload() != null) {
                    productResponses.add(productResponseAPIResponse.getPayload());
                }
            }

            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(order.getId());
            orderResponse.setCustomerResponse(customerResponse);
            orderResponse.setProductResponse(productResponses);
            orderResponse.setLocalDate(order.getOrderDate());

            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }

    @Override
    public OrderResponse getOrderById(long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new RuntimeException("Order ID not found");
        }

        APIResponse<CustomerResponse> customerResponse = customerServiceFeignClient.getCustomerById(order.getCustomerId());
        if (customerResponse == null || customerResponse.getPayload() == null) {
            throw new RuntimeException("Customer details not found");
        }

        List<ProductResponse> productResponses = new ArrayList<>();

        for (Long productId : order.getProductIds()) {
            APIResponse<ProductResponse> productResponseAPIResponse = productServiceFeignClient.getProductById(productId);
            if (productResponseAPIResponse != null && productResponseAPIResponse.getPayload() != null) {
                productResponses.add(productResponseAPIResponse.getPayload());
            } else {
                throw new RuntimeException("Product ID " + productId + " not found");
            }
        }

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setCustomerResponse(customerResponse.getPayload());
        orderResponse.setProductResponse(productResponses);
        orderResponse.setLocalDate(order.getOrderDate());
        return orderResponse;
    }

    @Override
    public OrderResponse updateOrderById(long id, OrderRequest orderRequest) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new RuntimeException("Order ID not found");
        }

        APIResponse<CustomerResponse> customerResponse = customerServiceFeignClient.getCustomerById(orderRequest.getCustomerId());
        if (customerResponse == null || customerResponse.getPayload() == null) {
            throw new RuntimeException("Customer details not found");
        }

        List<ProductResponse> productResponses = new ArrayList<>();

        for (Long productId : orderRequest.getProductIds()) {
            APIResponse<ProductResponse> productResponseAPIResponse = productServiceFeignClient.getProductById(productId);
            if (productResponseAPIResponse != null && productResponseAPIResponse.getPayload() != null) {
                productResponses.add(productResponseAPIResponse.getPayload());
            } else {
                throw new RuntimeException("Product ID " + productId + " not found");
            }
        }
        order.setCustomerId(orderRequest.getCustomerId());
        order.setOrderDate(orderRequest.getOrderDate());
        order.setProductIds(orderRequest.getProductIds());
        orderRepository.save(order);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setCustomerResponse(customerResponse.getPayload());
        orderResponse.setProductResponse(productResponses);
        orderResponse.setLocalDate(order.getOrderDate());
        return orderResponse;
    }

    @Override
    public void deleteOrderById(long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new RuntimeException("Order ID not found");
        }
        orderRepository.deleteById(id);
    }


}
