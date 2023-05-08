package vn.edu.hcmuaf.fit.efootwearspringboot.services.account;

import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

public interface AccountService {
    BaseResult createAccount(AccountDto accountDto);


    DataResult login(AccountDto accountDto);

    BaseResult verify(String token);
}
