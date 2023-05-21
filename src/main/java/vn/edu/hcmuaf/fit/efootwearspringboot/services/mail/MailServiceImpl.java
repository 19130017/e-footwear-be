package vn.edu.hcmuaf.fit.efootwearspringboot.services.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.spring6.SpringTemplateEngine;
import vn.edu.hcmuaf.fit.efootwearspringboot.configs.SpringMailConfig;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.VerifyType;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.InternalServerException;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Account;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Verify;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.VerifyRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Value("${app.url}")
    private String host;

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
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setFrom(mailFrom, "HB Footwear - Nâng niu bàn chân việt");
        helper.setTo(mailTo);
        helper.setText(text, html);
        helper.setSubject(subject);
        ClassPathResource imageResource = new ClassPathResource("static/images/logo.png");
        helper.addInline("logo", imageResource);

        javaMailSender.send(message);
    }

    @Override
    public BaseResult sendMailVerify(Account account) throws TemplateProcessingException {
        Verify verify = new Verify();
        verify.setType(VerifyType.VERIFY.name());
        verify.setIsVerified(false);
        verify.setToken(UUID.randomUUID().toString());
        verify.setExpireTime(new Date(System.currentTimeMillis() + (3600 * 1000 * 3)));
        verify.setAccount(account);

        //save
        verifyRepository.save(verify);

        // send email
        boolean isSuccess = isSendVerify(account.getEmail(), verify.getToken().toString());
        if (isSuccess) {
            return BaseResult.success();
        }
        throw new InternalServerException("Gửi mail lỗi");
    }

    private boolean isSendVerify(String email, String token) throws TemplateProcessingException {
        try {
            final Context context = new Context();
            String link = host + "auth/verify-account/" + token;
            context.setVariable("url_verify_account", link);
            String html = templateEngine.process("verify-template", context);
            sendMailer(from, email, html, "[HB Footwear] Xác thực tài khoản", true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public BaseResult sendMailResetPassword(Account account) {
        Verify verify = new Verify();
        verify.setType(VerifyType.RESET_PASSWORD.name());
        verify.setIsVerified(false);
        verify.setToken(UUID.randomUUID().toString());
        verify.setExpireTime(new Date(System.currentTimeMillis() + (3600 * 1000 * 3)));
        verify.setAccount(account);

        //save
        verifyRepository.save(verify);

        boolean isSuccess = isSendResetPassword(account.getEmail(), verify.getToken());
        if (isSuccess) {
            return BaseResult.success();
        }
        throw new InternalServerException("Gửi mail lỗi");

    }

    private boolean isSendResetPassword(String email, String token) {
        try {
            final Context context = new Context();
            String link = host + "auth/reset-password/" + token;
            context.setVariable("url_reset_password", link);
            String html = templateEngine.process("reset-template", context);
            sendMailer(from, email, html, "[HB Footwear] Quên mật khẩu", true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
