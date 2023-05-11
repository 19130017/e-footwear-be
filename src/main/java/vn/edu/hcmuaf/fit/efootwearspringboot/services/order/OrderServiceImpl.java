package vn.edu.hcmuaf.fit.efootwearspringboot.services.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.OrderMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Order;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.OrderRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderMapper orderMapper
    ) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public DataResult getOrders() {
        List<Order> orders = orderRepository.findAll();
        return DataResult.success(orderMapper.toDtos(orders));
    }
}
