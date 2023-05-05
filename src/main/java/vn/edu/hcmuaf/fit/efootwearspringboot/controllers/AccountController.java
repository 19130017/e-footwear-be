package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.Role;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountLoginRequest;
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
                .email(accountLoginRequest.getEmail())
                .role(Role.CUSTOMER.name())
                .password(accountLoginRequest.getPassword()).build();
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

        BaseResult baseResult = accountService.createAccount(accountDto);

        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(baseResult.getMessage()))
                : ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getMessage()));
    }
}
