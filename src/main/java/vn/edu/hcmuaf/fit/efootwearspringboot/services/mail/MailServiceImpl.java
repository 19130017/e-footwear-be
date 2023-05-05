package vn.edu.hcmuaf.fit.efootwearspringboot.services.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import vn.edu.hcmuaf.fit.efootwearspringboot.configs.SpringMailConfig;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.VerifyType;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Account;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Verify;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.VerifyRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Value("${app.url")
    private String app_url;

    private VerifyRepository verifyRepository;
    private JavaMailSender javaMailSender;
    private SpringTemplateEngine templateEngine;

    @Autowired
    public MailServiceImpl(VerifyRepository verifyRepository, JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.verifyRepository = verifyRepository;
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendMailer(
            String mailFrom, String mailTo, String text, String subject, boolean html)
            throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(mailTo);
        helper.setText(text, html);
        helper.setSubject(subject);
        helper.setFrom(mailFrom);

        javaMailSender.send(message);
    }

    @Override
    public BaseResult sendMailVerify(Account account) {
        Verify verify = new Verify();
        verify.setType(VerifyType.VERIFY.name());
        verify.setIsExpired(false);
        verify.setToken(UUID.randomUUID().toString());
        verify.setExpireTime(new Date(System.currentTimeMillis() + (60 * 60)));
        verify.setAccount(account);

        //save
        verifyRepository.save(verify);
        System.out.println(verify.getToken().toString());
        // send email
        boolean isSuccess = isSendVerify(account.getEmail(), verify.getToken().toString());
        if (isSuccess) {
            return BaseResult.success();
        }
        return BaseResult.error(HttpStatus.INTERNAL_SERVER_ERROR, "Gửi email lỗi");
    }

    public boolean isSendVerify(String email, String token) {
        try {
            final Context context = new Context();
            context.setVariable("app_url", app_url);
            context.setVariable("token", token);
            String html = templateEngine.process("verify-template", context);

            sendMailer(from, email, html, "Xác thực tài khoản", true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public BaseResult sendMailResetPassword(String email, String context) {
        return null;
    }
}
