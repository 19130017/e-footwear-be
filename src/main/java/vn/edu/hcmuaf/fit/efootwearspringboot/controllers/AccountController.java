package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.account.AccountService;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponse;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseError;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseSuccess;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.io.IOException;

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
    public ResponseEntity<HttpResponse> login(@RequestBody @Valid AccountLoginRequest accountLoginRequest) {
        AccountDto accountDto = AccountDto
                .builder()
                .username(accountLoginRequest.getUsername())
                .password(accountLoginRequest.getPassword())
                .build();
        DataResult dataResult = accountService.login(accountDto);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData()))
                : ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> createAccount(@RequestBody @Valid AccountCreateDto accountCreateDto) {
        AccountDto accountDto = AccountDto.builder()
                .username(accountCreateDto.getUsername())
                .password(accountCreateDto.getPassword())
                .email(accountCreateDto.getEmail())
                .build();

        BaseResult baseResult = accountService.createAccount(accountDto);

        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success("Chúc mừng bạn đăng ký tài khoản thành công. Vui lòng truy cập email để kích hoạt tài khoản."))
                : ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity<HttpResponse> verifyAccount(@PathVariable("token") String token) {
        DataResult dataResult = accountService.verify(token);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getMessage())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }


    @GetMapping("/profile/{id}")
    public ResponseEntity<HttpResponse> getProfile(@PathVariable("id") Long id) {
        DataResult dataResult = accountService.getProfile(id);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @PutMapping("/profile")
    public ResponseEntity<HttpResponse> updateProfile(@RequestBody @Valid CustomerInfoRequestDto customerInfoRequest) {
        DataResult dataResult = accountService.updateProfile(customerInfoRequest);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }


    @GetMapping()
    public ResponseEntity<HttpResponse> getAllAccount() {
        DataResult dataResult = accountService.getAllAccount();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @PutMapping("/change-password")
    public ResponseEntity<HttpResponse> changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto) {
        BaseResult baseResult = accountService.changePassword(changePasswordDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<HttpResponse> forgotPassword(@Valid @RequestParam("email") String email) {
        BaseResult baseResult = accountService.forgotPassword(email);


        return baseResult.getSuccess() ? ResponseEntity.ok(HttpResponseSuccess.success("Vui lòng kiểm tra email của bạn!"))
                : ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @PutMapping("/reset-password")
    public ResponseEntity<HttpResponse> resetPassword(@RequestBody @Valid ResetPasswordDto resetPasswordDto) {
        BaseResult baseResult = accountService.resetPassword(resetPasswordDto);
        return baseResult.getSuccess() ? ResponseEntity.ok(HttpResponseSuccess.success("Thay đổi mật khẩu thành công"))
                : ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @PostMapping("/upload-avatar")
    public ResponseEntity<HttpResponse> uploadAvatar(@RequestParam("avatar") MultipartFile avatar, @RequestParam("accountId") Long accountId) throws IOException {
        BaseResult baseResult = accountService.uploadAvatar(avatar, accountId);
        return baseResult.getSuccess() ? ResponseEntity.ok(HttpResponseSuccess.success())
                : ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }
}
