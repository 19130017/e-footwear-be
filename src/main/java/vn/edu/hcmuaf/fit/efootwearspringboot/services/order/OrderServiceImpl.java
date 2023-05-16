package vn.edu.hcmuaf.fit.efootwearspringboot.services.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.OrderRequestDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.OrderRequestStatusDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.OrderResponseDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order_status.OrderStatusDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.NotFoundException;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Order;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.OrderItem;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.OrderStatus;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.OrderRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.OrderStatusRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private OrderStatusRepository orderStatusRepository;
    private OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderMapper orderMapper,
            OrderStatusRepository orderStatusRepository

    ) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public DataResult getOrders() {
        List<Order> orders = orderRepository.findAll();
        return DataResult.success(orderMapper.toResponseDtos(orders));
    }

    @Override
    public BaseResult createOrder(OrderRequestDto orderRequestDto) {
        Optional<OrderStatus> optional = orderStatusRepository.findByCode("CONFIRMATION");
        if (optional.isEmpty()) {
            return BaseResult.error(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
        }

        Order order = orderMapper.requestToEntity(orderRequestDto);
        String orderId = UUID.randomUUID().toString();
        order.setId(orderId);
        order.setOrderStatus(optional.get());
        for (OrderItem orderItem : order.getItems()) {
            orderItem.setOrder(order);
        }
        if (!ObjectUtils.isEmpty(orderRepository.save(order))) {
            return BaseResult.success();
        }
        return BaseResult.error(HttpStatus.INTERNAL_SERVER_ERROR, "Không thể lưu trữ");
    }

    @Override
    public DataResult getOrdersByAccountId(Long accountId) {
        Optional<List<Order>> optional = orderRepository.findOrdersByAccountId(accountId);

        if (optional.isPresent()) {
            List<Order> orders = optional.get();

            return DataResult.success(orderMapper.toResponseDtos(orders));
        }
        return DataResult.error(HttpStatus.BAD_REQUEST, "khong tim thay voi id");
    }

    @Override
    public BaseResult updateStatus(OrderRequestStatusDto orderRequestStatusDto) {
        Optional<OrderStatus> optional = orderStatusRepository.findById(orderRequestStatusDto.getStatus().getId());
        if (optional.isEmpty()) {
            return BaseResult.error(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
        }

        Optional<Order> optional1 = orderRepository.findByOrderId(orderRequestStatusDto.getId());
        if (optional1.isEmpty()) {
            return BaseResult.error(HttpStatus.INTERNAL_SERVER_ERROR, "Không tìm thấy Order_Id");
        }

        Order order = optional1.get();
        order.setOrderStatus(optional.get());
        if (ObjectUtils.isEmpty(orderRepository.save(order))) {
            return BaseResult.error(HttpStatus.INTERNAL_SERVER_ERROR, "Không thế cập nhật trạng thái đơn hàng!");
        }
        return BaseResult.success();
    }

    @Override
    public DataResult getOrder(String id) {
        Optional<Order> optional = orderRepository.findByOrderId(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Không tìm thấy đơn hàng!");
        }
        return DataResult.success(orderMapper.toResponseDto(optional.get()));
    }
}
