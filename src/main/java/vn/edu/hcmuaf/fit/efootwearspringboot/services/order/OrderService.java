package vn.edu.hcmuaf.fit.efootwearspringboot.services.order;

import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.OrderDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.OrderRequestDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.OrderRequestStatusDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.OrderResponseDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order_status.OrderStatusDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

public interface OrderService {
    DataResult getOrders();

    BaseResult createOrder(OrderRequestDto orderRequestDto);

    DataResult getOrdersByAccountId(Long accountId);

    BaseResult updateStatus(OrderRequestStatusDto orderRequestStatusDto);

    DataResult getOrder(String id);

    DataResult countOrder();

    DataResult listOrderHot();
    DataResult totalByMonth();
}
