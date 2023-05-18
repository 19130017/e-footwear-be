package vn.edu.hcmuaf.fit.efootwearspringboot.services.account;

import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.ChangePasswordDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.CustomerInfoRequestDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.ResetPasswordDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

public interface AccountService {
    DataResult createAccount(AccountDto accountDto);

    DataResult getProfile(Long accountId);


    DataResult login(AccountDto accountDto);

    DataResult verify(String token);

    DataResult updateProfile(CustomerInfoRequestDto customerInfoRequest);


    DataResult getAllAccount();

    BaseResult changePassword(ChangePasswordDto changePasswordDto);

    BaseResult forgotPassword(String email);

    BaseResult resetPassword(ResetPasswordDto resetPasswordDto);

    DataResult countAccount();
}
