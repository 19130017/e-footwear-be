package vn.edu.hcmuaf.fit.efootwearspringboot.services.order;

import jakarta.servlet.http.HttpServletRequest;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.OrderDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.OrderRequestDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.OrderRequestStatusDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.OrderResponseDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order_status.OrderStatusDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface OrderService {
    DataResult getOrders();

    BaseResult createOrderCOD(OrderRequestDto orderRequestDto);

    DataResult getOrdersByAccountId(Long accountId);

    BaseResult updateStatus(OrderRequestStatusDto orderRequestStatusDto);

    DataResult getOrder(String id);

    DataResult countOrder();

    DataResult listOrderHot();

    DataResult countByMonth();

    DataResult totalByMonth();

    DataResult createOrderMomo(OrderRequestDto orderRequestDto) throws NoSuchAlgorithmException, InvalidKeyException, IOException;

    DataResult updateStatusByCode(OrderRequestStatusDto orderRequestStatusDto);

    DataResult createOrderVNPay(OrderRequestDto orderRequestDto, HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeyException;
}
