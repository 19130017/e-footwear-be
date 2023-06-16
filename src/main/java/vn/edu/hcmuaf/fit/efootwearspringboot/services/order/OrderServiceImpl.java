package vn.edu.hcmuaf.fit.efootwearspringboot.services.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import static vn.edu.hcmuaf.fit.efootwearspringboot.constants.Momo.*;

import vn.edu.hcmuaf.fit.efootwearspringboot.constants.PaymentMethod;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order_status.OrderStatusDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.InternalServerException;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.NotFoundException;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.CouponRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.DetailRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.OrderRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.OrderStatusRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.Encoder;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private OrderStatusRepository orderStatusRepository;
    private OrderMapper orderMapper;
    private DetailRepository detailRepository;
    private CouponRepository couponRepository;

    @Autowired
    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderMapper orderMapper,
            OrderStatusRepository orderStatusRepository,
            DetailRepository detailRepository,
            CouponRepository couponRepository

    ) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderStatusRepository = orderStatusRepository;
        this.detailRepository = detailRepository;
        this.couponRepository = couponRepository;
    }

    @Override
    public DataResult getOrders() {
        List<Order> orders = orderRepository.findAll();
        return DataResult.success(orderMapper.toResponseDtos(orders));
    }

    @Override
    public BaseResult createOrderCOD(OrderRequestDto orderRequestDto) {
        Order order = createOrder(orderRequestDto);
        order.setPaymentMethod(PaymentMethod.COD);
        OrderStatus orderStatus = orderStatusRepository.findByCode("SUCCESS").orElse(null);

        if (orderStatus != null) {
            order.setOrderStatus(orderStatus);
        }

        // save order
        if (!ObjectUtils.isEmpty(orderRepository.save(order))) {
            return BaseResult.success();
        }
        throw new InternalServerException("Không tạo mới đơn hàng!");
    }

    private Order createOrder(OrderRequestDto orderRequestDto) {
        Optional<OrderStatus> optional = orderStatusRepository.findByCode("CONFIRMATION");
        if (optional.isEmpty()) {
            throw new NotFoundException("Không tìm thấy dữ liệu!");
        }

        Order order = orderMapper.requestToEntity(orderRequestDto);
        String orderId = UUID.randomUUID().toString();
        order.setId(orderId);
        order.setOrderStatus(optional.get());

        // set order and update quantity detail
        for (OrderItem orderItem : order.getItems()) {
            Optional<Detail> optionalDetail = detailRepository.findById(orderItem.getDetail().getId());
            if (optionalDetail.isEmpty()) {
                throw new NotFoundException("Không tìm thấy detail!");
            }
            Detail detail = optionalDetail.get();
            detail.setStockQuantity(detail.getStockQuantity() - orderItem.getQuantity());
            if (ObjectUtils.isEmpty(detailRepository.save(detail))) {
                throw new InternalServerException("Không thể cập nhật số lượng tồn kho của sản phẩm!");
            }
            orderItem.setOrder(order);
        }
        // set usage coupon
        if (order.getCoupon() != null) {
            Optional<Coupon> optionalCoupon = couponRepository.findById(order.getCoupon().getId());
            if (optionalCoupon.isEmpty()) {
                throw new NotFoundException("Không tìm thấy coupon!");
            }
            Coupon coupon = optionalCoupon.get();
            coupon.setMaxUsage(coupon.getMaxUsage() - 1);
            if (ObjectUtils.isEmpty(couponRepository.save(coupon))) {
                throw new InternalServerException("Không thể cập nhật số lượng tồn kho của sản phẩm!");
            }
        }
        return order;
    }

    @Override
    public DataResult getOrdersByAccountId(Long accountId) {
        Optional<List<Order>> optional = orderRepository.findOrdersByAccountId(accountId);

        if (optional.isPresent()) {
            List<Order> orders = optional.get();

            return DataResult.success(orderMapper.toResponseDtos(orders));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public BaseResult updateStatus(OrderRequestStatusDto orderRequestStatusDto) {
        Optional<OrderStatus> optional = orderStatusRepository.findById(orderRequestStatusDto.getStatus().getId());
        if (optional.isEmpty()) {
            throw new NotFoundException("Không tìm thấy trạng thái đơn hàng!");
        }

        Optional<Order> optional1 = orderRepository.findByOrderId(orderRequestStatusDto.getId());
        if (optional1.isEmpty()) {
            throw new NotFoundException("Không tìm thấy đơn hàng!");
        }

        Order order = optional1.get();
        order.setOrderStatus(optional.get());
        if (ObjectUtils.isEmpty(orderRepository.save(order))) {
            throw new InternalServerException("Không thể cập nhật trạng thái của đơn hàng!");
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

    @Override
    public DataResult countOrder() {
        Long count = orderRepository.count();
        if (ObjectUtils.isEmpty(count)) {
            throw new InternalServerException("Không thể đếm số lượng đơn hàng");
        }
        return DataResult.success(count);
    }

    @Override
    public DataResult listOrderHot() {
        Optional<List<Order>> optional = orderRepository.findOrdersHot();
        if (optional.isEmpty()) {
            throw new NotFoundException("Không tìm thấy đơn hàng nào!");
        }
        return DataResult.success(orderMapper.toResponseDtos(optional.get()));
    }

    @Override
    public DataResult countByMonth() {
        List<Object[]> optional = orderRepository.countByMonth();
        if (optional.isEmpty()) {
            throw new NotFoundException("Không tìm thấy đơn hàng nào!");
        }
        return DataResult.success(optional);
    }

    @Override
    public DataResult totalByMonth() {
        List<Object[]> optional = orderRepository.totalByMonth();
        if (optional.isEmpty()) {
            throw new NotFoundException("Không tìm thấy đơn hàng nào!");
        }
        return DataResult.success(optional);
    }

    @Override
    public DataResult createOrderMomo(OrderRequestDto orderRequestDto) throws NoSuchAlgorithmException, InvalidKeyException, IOException {

        Order order = createOrder(orderRequestDto);
        order.setPaymentMethod(PaymentMethod.MOMO);
        OrderStatus orderStatus = orderStatusRepository.findByCode("PENDING").orElse(null);

        if (orderStatus != null) {
            order.setOrderStatus(orderStatus);
        }
        // save order
        if (ObjectUtils.isEmpty(orderRepository.save(order))) {
            throw new InternalServerException("Không tạo mới đơn hàng!");
        }

        String partnerCode = PARTNER_CODE;
        String requestId = System.currentTimeMillis() + "";
        Long amount = Long.valueOf(order.getCost());
        String orderId = order.getId();
        String orderInfo = "Thanh toán đơn hàng: " + orderId;
        String redirectUrl = REDIRECT_URL;
        String ipnUrl = IPN_URL;
        String requestType = "captureWallet";
        String extraData = "";
        String lang = "vi";
        String payload = "accessKey=" + ACCESS_KEY + "&amount=" + amount + "&extraData=" + extraData + "&ipnUrl=" +
                ipnUrl + "&orderId=" + orderId + "&orderInfo=" + orderInfo + "&partnerCode=" + partnerCode +
                "&redirectUrl=" + redirectUrl + "&requestId=" + requestId + "&requestType=" + requestType;
        String signature = Encoder.signHmacSHA256(payload, SECRET_KEY);

        JsonObject json = new JsonObject();
        json.addProperty("partnerCode", partnerCode);
        json.addProperty("partnerName", order.getAddress().getFullName());
        json.addProperty("requestType", requestType);
        json.addProperty("ipnUrl", ipnUrl);
        json.addProperty("redirectUrl", redirectUrl);
        json.addProperty("orderId", orderId);
        json.addProperty("amount", amount);
        json.addProperty("lang", lang);
        json.addProperty("autoCapture", true);
        json.addProperty("orderInfo", orderInfo);
        json.addProperty("requestId", requestId);
        json.addProperty("extraData", extraData);
        json.addProperty("signature", signature);

        // response json
        String response = Request.Post(ENDPOINT)
                .bodyString(json.toString(), ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8))
                .execute().returnContent().asString(StandardCharsets.UTF_8);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (jsonElement, type, context)
                        -> new Date(jsonElement.getAsJsonPrimitive().getAsLong()))
                .create();
        return DataResult.success(gson.fromJson(response, CaptureMoMoResponse.class));
    }

    @Override
    public BaseResult updateStatusByCode(OrderRequestStatusDto orderRequestStatusDto) {
        OrderStatus orderStatus = orderStatusRepository.findByCode(orderRequestStatusDto.getStatus().getCode()).orElse(null);
        if (orderStatus == null) {
            throw new NotFoundException("Không tìm thấy trạng thái đơn hàng!");
        }

        Order order = orderRepository.findByOrderId(orderRequestStatusDto.getId()).orElse(null);
        if (order == null) {
            throw new NotFoundException("Không tìm thấy đơn hàng!");
        }

        order.setOrderStatus(orderStatus);
        if (ObjectUtils.isEmpty(orderRepository.save(order))) {
            throw new InternalServerException("Không thể cập nhật trạng thái của đơn hàng!");
        }
        return BaseResult.success();
    }
}
