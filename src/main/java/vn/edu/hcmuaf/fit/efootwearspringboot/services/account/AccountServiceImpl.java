package vn.edu.hcmuaf.fit.efootwearspringboot.services.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountLoginResponse;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.CustomerInfoRequestDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.CustomerInfoResponseDto;
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
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

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
        // authenticate
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountDto.getUsername(), accountDto.getPassword()));

        // get account
        Optional<Account> optional = accountRepository.findByUsername(accountDto.getUsername());
        if (optional.isPresent()) {
            Account account = optional.get();
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
            return DataResult.error(HttpStatus.BAD_GATEWAY, "Lỗi từ hệ thống");
        }
        return DataResult.error(HttpStatus.BAD_REQUEST, "Tài khoản không tồn tại");
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
            return DataResult.error(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
        }
        return DataResult.error(HttpStatus.BAD_REQUEST, "Thông tin token không hợp lệ hoặc đã hết hạn");
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
        return DataResult.error(HttpStatus.BAD_REQUEST, "Thông tin tài khoản lỗi");
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
            return DataResult.error(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
        }
        return DataResult.error(HttpStatus.BAD_REQUEST, "Tài khoản không tồn tại");
    }


    @Override
    public DataResult createAccount(AccountDto accountDto) {
        // check email
        Optional<Account> optional = accountRepository.findByEmail(accountDto.getEmail());
        if (optional.isPresent()) {
            return DataResult.error(HttpStatus.BAD_REQUEST, "Email đã tồn tại");
        }

        //check username
        optional = accountRepository.findByUsername(accountDto.getUsername());
        if (optional.isPresent()) {
            return DataResult.error(HttpStatus.BAD_REQUEST, "Username đã tồn tại");
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

            return DataResult.success("Chúc mừng bạn đăng ký tài khoản thành công. Vui lòng truy cập email để kích hoạt tài khoản.");
        }
        return DataResult.error(HttpStatus.BAD_REQUEST, "Không thể thêm mới dữ liệu.");
    }


}
