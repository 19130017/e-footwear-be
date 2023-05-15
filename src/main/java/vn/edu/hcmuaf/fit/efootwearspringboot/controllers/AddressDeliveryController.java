package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.address_delivery.AddressDeliveryCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.address_delivery.AddressDeliveryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.address_delivery.AddressDeliveryUpdateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.address_delivery.AddressDeliveryService;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponse;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseError;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseSuccess;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressDeliveryController {

    private AddressDeliveryService addressDeliveryService;

    @Autowired
    public AddressDeliveryController(AddressDeliveryService addressDeliveryService) {
        this.addressDeliveryService = addressDeliveryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse> getAddress(@PathVariable("id") Long id) {
        DataResult dataResult = addressDeliveryService.getAddress(id);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @GetMapping("/list/{accountId}")
    public ResponseEntity<HttpResponse> getAddresses(@PathVariable("accountId") Long accountId) {
        DataResult dataResult = addressDeliveryService.getAddresses(accountId);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @PostMapping()
    public ResponseEntity<HttpResponse> createAddress(
            @RequestBody AddressDeliveryCreateDto addressDeliveryCreateDto,
            @RequestParam("accountId") Long accountId) {
        AddressDeliveryDto addressDeliveryDto = AddressDeliveryDto
                .builder()
                .fullName(addressDeliveryCreateDto.getFullName())
                .phone(addressDeliveryCreateDto.getPhone())
                .address(addressDeliveryCreateDto.getAddress())
                .email(addressDeliveryCreateDto.getEmail())
                .isDefault(addressDeliveryCreateDto.getIsDefault())
                .addresses(addressDeliveryCreateDto.getAddresses())
                .build();

        BaseResult baseResult = addressDeliveryService.createAddress(addressDeliveryDto, accountId);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getMessage()));
    }

    @PutMapping()
    public ResponseEntity<HttpResponse> updateAddressDelivery(
            @RequestBody AddressDeliveryUpdateDto addressDeliveryUpdateDto,
            @RequestParam("accountId") Long accountId) {
        AddressDeliveryDto addressDeliveryDto = AddressDeliveryDto
                .builder()
                .id(addressDeliveryUpdateDto.getId())
                .fullName(addressDeliveryUpdateDto.getFullName())
                .phone(addressDeliveryUpdateDto.getPhone())
                .address(addressDeliveryUpdateDto.getAddress())
                .email(addressDeliveryUpdateDto.getEmail())
                .isDefault(addressDeliveryUpdateDto.getIsDefault())
                .addresses(addressDeliveryUpdateDto.getAddresses())
                .build();
        BaseResult baseResult = addressDeliveryService.updateAddress(accountId, addressDeliveryDto);

        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getMessage()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> deleteAddress(@PathVariable("id") Long id) {
        BaseResult baseResult = addressDeliveryService.deleteAddress(id);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getMessage()));
    }
}
