package vn.edu.hcmuaf.fit.efootwearspringboot.services.account;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.io.IOException;

public interface AccountService extends UserDetailsService {
    BaseResult createAccount(AccountDto accountDto);

    DataResult getProfile(Long accountId);


    DataResult login(AccountDto accountDto);

    DataResult verify(String token);

    DataResult updateProfile(CustomerInfoRequestDto customerInfoRequest);


    DataResult getAllAccount();

    BaseResult changePassword(ChangePasswordDto changePasswordDto);

    BaseResult forgotPassword(String email);

    BaseResult resetPassword(ResetPasswordDto resetPasswordDto);


    DataResult countAccount();

    DataResult uploadAvatar(MultipartFile avatar, Long accountId) throws IOException;

    DataResult loginWithGoogle(AccountLoginGGRequestDto accountLoginGGRequestDto);

    DataResult loginWithFacebook(AccountLoginFBRequestDto accountLoginFBRequestDto);
}
