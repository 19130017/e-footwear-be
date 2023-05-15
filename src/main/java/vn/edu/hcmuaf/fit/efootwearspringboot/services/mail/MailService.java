package vn.edu.hcmuaf.fit.efootwearspringboot.services.mail;

import vn.edu.hcmuaf.fit.efootwearspringboot.models.Account;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;

public interface MailService {
    BaseResult sendMailVerify(Account account);

    BaseResult sendMailResetPassword(Account account);
}
