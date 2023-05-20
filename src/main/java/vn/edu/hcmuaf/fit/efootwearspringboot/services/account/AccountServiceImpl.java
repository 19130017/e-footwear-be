package vn.edu.hcmuaf.fit.efootwearspringboot.services.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.QUERY;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountLoginResponse;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.CustomerInfoRequestDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.CustomerInfoResponseDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.InternalServerException;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.NotFoundException;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.VerifyType;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.AccountMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.AddressDeliveryMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Account;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Customer;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Verify;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.AccountRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.Role;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.CustomerRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.VerifyRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.mail.MailService;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final VerifyRepository verifyRepository;
    private final AccountMapper accountMapper;
    private final AddressDeliveryMapper addressDeliveryMapper;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final MailService mailService;

    @Autowired
    public AccountServiceImpl(
            AccountRepository accountRepository,
            AccountMapper accountMapper,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            CustomerRepository customerRepository,
            MailService mailService,
            VerifyRepository verifyRepository,
            AddressDeliveryMapper addressDeliveryMapper
    ) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customerRepository = customerRepository;
        this.mailService = mailService;
        this.verifyRepository = verifyRepository;
        this.addressDeliveryMapper = addressDeliveryMapper;
    }


    @Override
    public DataResult login(AccountDto accountDto) {
        // get account
        Optional<Account> optional = accountRepository.findByUsername(accountDto.getUsername());


        if (optional.isPresent()) {
            Account account = optional.get();
            if (!account.getUsername().equals("admin")) {
                Optional<Verify> optional1 = verifyRepository.findByAccountIdAndType(account.getId(), VerifyType.VERIFY.name());
                Verify verify = optional1.get();
                if (!verify.getIsVerified())
                    return DataResult.error(HttpStatus.BAD_REQUEST, "Vui lòng vào email để xác nhận tài khoản");
            }
            if (!passwordEncoder.matches(accountDto.getPassword(), account.getPassword())) {
                return DataResult.error(HttpStatus.BAD_REQUEST, "Mật khẩu sai. Vui lòng nhập lại mật khẩu");
            }

            // authenticate
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountDto.getUsername(), accountDto.getPassword()));
            String jwtToken = jwtService.generateToken(account, account.getAuthorities());
            String jwtRefreshToken = jwtService.refreshToken(account);
            account.setRefreshToken(jwtRefreshToken);

            if (!ObjectUtils.isEmpty(accountRepository.save(account))) {
                AccountLoginResponse response = AccountLoginResponse
                        .builder()
                        .accountId(account.getId())
                        .avatar(account.getCustomer().getAvatar())
                        .username(account.getUsername())
                        .token(jwtToken)
                        .refreshToken(jwtRefreshToken)
                        .build();
                return DataResult.success(response);
            }
            throw new InternalServerException("Không thể lưu refresh-token vào database");
        }
        throw new NotFoundException("Không tìm thấy tài khoản!");
    }

    @Override
    public DataResult verify(String token) {
        Optional<Verify> optional = verifyRepository.findByToken(token);
        if (optional.isPresent()) {
            Verify verify = optional.get();
            verify.setIsVerified(true);
            if (!ObjectUtils.isEmpty(verifyRepository.save(verify))) {
                return DataResult.success("Xác thực tài khoản thành công");
            }
            throw new InternalServerException("Không thể thay đổi trạng thái của mã xác thực tài khoản!");
        }
        throw new NotFoundException("Token không hợp lệ hoặc đã hết hạn");
    }

    @Override
    public DataResult getProfile(Long accountId) {
        Optional<Customer> optional = customerRepository.findByAccountId(accountId);
        if (optional.isPresent()) {
            Customer customer = optional.get();
            CustomerInfoResponseDto customerInfoResponseDto = CustomerInfoResponseDto
                    .builder()
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .birthday(customer.getBirthday())
                    .avatar(customer.getAvatar())
                    .build();
            return DataResult.success(customerInfoResponseDto);
        }
        throw new NotFoundException("Không tìm thấy tài khoản!");
    }

    @Override
    public DataResult updateProfile(CustomerInfoRequestDto customerInfoRequest) {
        Optional<Account> optional = accountRepository.findById(customerInfoRequest.getAccountId());
        if (optional.isPresent()) {
            Account account = optional.get();
            account.getCustomer().setBirthday(customerInfoRequest.getBirthday());
            account.getCustomer().setFirstName(customerInfoRequest.getFirstName());
            account.getCustomer().setLastName(customerInfoRequest.getLastName());
            if (!ObjectUtils.isEmpty(accountRepository.save(account))) {
                return DataResult.success("Cập nhập thông tin khách hàng thành công");
            }
            throw new InternalServerException("Không thể cập nhật được profile!");
        }
        throw new NotFoundException("Không tìm thấy tài khoản!");
    }

    @Override
    public BaseResult changePassword(ChangePasswordDto changePasswordDto) {
        if (changePasswordDto.getNewPassword().equals(changePasswordDto.getOldPassword())) {
            return BaseResult.error(HttpStatus.BAD_REQUEST, "Mật khẩu trùng với mật khẩu cũ. Vui lòng nhập mật khẩu khác!");
        }

        Optional<Account> optional = accountRepository.findById(changePasswordDto.getId());
        if (optional.isEmpty()) {
            throw new NotFoundException("Không tìm thấy tài khoản!");
        }

        Account account = optional.get();
        if (!passwordEncoder.matches(changePasswordDto.getOldPassword(), account.getPassword())) {
            return BaseResult.error(HttpStatus.BAD_REQUEST, "Mật khẩu cũ không trùng khớp!");
        }
        account.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        if (ObjectUtils.isEmpty(accountRepository.save(account))) {
            throw new InternalServerException("Không thể thay đổi mật khẩu!");
        }
        return BaseResult.success();
    }

    @Override
    public BaseResult forgotPassword(String email) {
        // check email
        Optional<Account> optional = accountRepository.findByEmail(email);
        if (optional.isEmpty()) {
            throw new NotFoundException("Không tìm thấy tài khoản!");
        }
        Account account = optional.get();
        if (account.getIsBlocked()) {
            return BaseResult.error(HttpStatus.BAD_REQUEST, "Tài khoản của bạn đã bị khoá!");
        }
        Optional<Verify> optional1 = verifyRepository.findByAccountIdAndType(account.getId(), VerifyType.VERIFY.name());
        if (optional1.isPresent()) {
            Verify verify = optional1.get();
            if (!verify.getIsVerified()) throw new NotFoundException("Tài khoản của bạn chưa kích hoạt !");
        }
        return mailService.sendMailResetPassword(account);
    }

    @Override
    public BaseResult resetPassword(ResetPasswordDto resetPasswordDto) {
        Optional<Verify> optional = verifyRepository.findByToken(resetPasswordDto.getToken());
        if (optional.isEmpty()) {
            throw new NotFoundException("Token không hợp lệ hoặc đã hết hạn!");
        }
        Verify verifier = optional.get();
        Optional<Account> optional1 = accountRepository.findById(verifier.getAccount().getId());
        if (optional.isEmpty()) {
            return BaseResult.error(HttpStatus.BAD_REQUEST, "Không tìm thấy tài khoản");
        }

        Account account = optional1.get();
        if (passwordEncoder.matches(resetPasswordDto.getNewPassword(), account.getPassword())) {
            return BaseResult.error(HttpStatus.BAD_REQUEST, "Mật khẩu đã được sử dụng ở lần trước. Vui lòng thay đổi mật khẩu khác");
        }
        account.setPassword(passwordEncoder.encode(resetPasswordDto.getNewPassword()));
        verifier.setIsVerified(true);

        if (ObjectUtils.isEmpty(accountRepository.save(account)) && ObjectUtils.isEmpty(verifyRepository.save(verifier))) {
            throw new InternalServerException("Không thể reset mật khẩu!");
        }
        return BaseResult.success();
    }

    @Override
    public BaseResult uploadAvatar(UploadAvatarDto avatar) {
        Optional<Account> optional = accountRepository.findById(avatar.getAccountId());
        if (optional.isPresent()) {
            Account account = optional.get();
            account.getCustomer().setAvatar(avatar.getAvatar());
            if (ObjectUtils.isEmpty(accountRepository.save(account))) {
                throw new InternalServerException("Không thể cập nhật ảnh đại diện!");
            }
            return BaseResult.success();
        }
        throw new NotFoundException("Không tìm thấy tài khoản");
    }

    @Override
    public DataResult getAllAccount() {
        Optional<List<Account>> optional = accountRepository.findAllAccount();
        if (optional.isPresent()) {
            return DataResult.success(accountMapper.toSlimAddressDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy tài khoản");
    }


    @Override
    @Transactional
    public BaseResult createAccount(AccountDto accountDto) {
        // check email
        Optional<Account> optional = accountRepository.findByEmail(accountDto.getEmail());
        if (optional.isPresent()) {
            return BaseResult.error(HttpStatus.BAD_REQUEST, "Email đã tồn tại");
        }

        //check username
        optional = accountRepository.findByUsername(accountDto.getUsername());
        if (optional.isPresent()) {
            return BaseResult.error(HttpStatus.BAD_REQUEST, "Username đã tồn tại");
        }

        Account account = accountMapper.toEntity(accountDto);
        if (accountDto.getRole() == null) account.setRole(Role.CUSTOMER);
        else account.setRole(Role.ADMIN);
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        account.setCustomer(new Customer());
        account.getCustomer().setAvatar("https://anhnenchat.com/wp-content/uploads/2021/02/chim-canh-cut-chibi-dang-yeu-nhat-cho-dien-thoai-1.png");
        account.setIsBlocked(false);

        if (!ObjectUtils.isEmpty(accountRepository.save(account))) {
            if (accountDto.getRole() == null) mailService.sendMailVerify(account);

            return BaseResult.success();
        }
        throw new InternalServerException("Không thể thêm mới dữ liệu.");
    }


}
