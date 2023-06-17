package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.CaptureMomoConfirmResponse;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.OrderRequestDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.order.OrderRequestStatusDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.order.OrderService;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponse;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseError;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseSuccess;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public ResponseEntity<HttpResponse> getOrders() {
        DataResult dataResult = orderService.getOrders();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse> getOrder(@PathVariable("id") String id) {
        DataResult dataResult = orderService.getOrder(id);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }


    @GetMapping("/account/{id}")
    public ResponseEntity<HttpResponse> getOrdersByAccountId(@PathVariable("id") Long accountId) {
        DataResult dataResult = orderService.getOrdersByAccountId(accountId);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @PostMapping()
    public ResponseEntity<HttpResponse> createOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        BaseResult baseResult = orderService.createOrderCOD(orderRequestDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success("Đặt hàng thành công")) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }


    @PostMapping("/payment/momo")
    public ResponseEntity<HttpResponse> createOrderMomo(@RequestBody @Valid OrderRequestDto orderRequestDto) throws NoSuchAlgorithmException, IOException, InvalidKeyException {
        DataResult dataResult = orderService.createOrderMomo(orderRequestDto);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @PostMapping("/payment/vn-pay")
    public ResponseEntity<HttpResponse> createOrderVNPay(@RequestBody OrderRequestDto orderRequestDto, HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeyException {
        DataResult dataResult = orderService.createOrderVNPay(orderRequestDto, request);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @PostMapping("/payment/success")
    public ResponseEntity<HttpResponse> updateStatusOrderMomo(@RequestBody OrderRequestStatusDto orderRequestStatusDto) {
        DataResult dataResult = orderService.updateStatusByCode(orderRequestStatusDto);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @PutMapping()
    public ResponseEntity<HttpResponse> updateOrderStatus(@RequestBody OrderRequestStatusDto orderRequestStatusDto) {
        BaseResult baseResult = orderService.updateStatus(orderRequestStatusDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @GetMapping("/count")
    public ResponseEntity<HttpResponse> countOrder() {
        DataResult dataResult = orderService.countOrder();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/hot")
    public ResponseEntity<HttpResponse> listOrderHot() {
        DataResult dataResult = orderService.listOrderHot();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/total")
    public ResponseEntity<HttpResponse> totalByMonth() {
        DataResult dataResult = orderService.countByMonth();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/totalPrice")
    public ResponseEntity<HttpResponse> totalPriceByMonth() {
        DataResult dataResult = orderService.totalByMonth();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }
}
