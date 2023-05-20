package vn.edu.hcmuaf.fit.efootwearspringboot.services.account;

import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

public interface AccountService {
    BaseResult createAccount(AccountDto accountDto);

    DataResult getProfile(Long accountId);


    DataResult login(AccountDto accountDto);

    DataResult verify(String token);

    DataResult updateProfile(CustomerInfoRequestDto customerInfoRequest);


    DataResult getAllAccount();

    BaseResult changePassword(ChangePasswordDto changePasswordDto);

    BaseResult forgotPassword(String email);

    BaseResult resetPassword(ResetPasswordDto resetPasswordDto);

    BaseResult uploadAvatar(UploadAvatarDto avatar);
}
