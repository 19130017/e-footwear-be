package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountLoginRequest;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.CustomerInfoRequestDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.account.AccountService;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponse;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseError;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseSuccess;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ResponseEntity<HttpResponse> login(@RequestBody AccountLoginRequest accountLoginRequest) {
        AccountDto accountDto = AccountDto
                .builder()
                .username(accountLoginRequest.getUsername())
                .email(accountLoginRequest.getEmail())
                .password(accountLoginRequest.getPassword())
                .build();
        DataResult dataResult = accountService.login(accountDto);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData()))
                : ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> createAccount(@RequestBody AccountCreateDto accountCreateDto) {
        AccountDto accountDto = AccountDto.builder()
                .username(accountCreateDto.getUsername())
                .password(accountCreateDto.getPassword())
                .email(accountCreateDto.getEmail())
                .build();

        DataResult dataResult = accountService.createAccount(accountDto);

        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getMessage()))
                : ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity<HttpResponse> verifyAccount(@PathVariable("token") String token) {
        DataResult dataResult = accountService.verify(token);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getMessage())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }


    @GetMapping("/profile/{id}")
    public ResponseEntity<HttpResponse> getProfile(@PathVariable("id") Long id) {
        DataResult dataResult = accountService.getProfile(id);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @PutMapping("/profile")
    public ResponseEntity<HttpResponse> updateProfile(@RequestBody CustomerInfoRequestDto customerInfoRequest) {
        DataResult dataResult = accountService.updateProfile(customerInfoRequest);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }


}
