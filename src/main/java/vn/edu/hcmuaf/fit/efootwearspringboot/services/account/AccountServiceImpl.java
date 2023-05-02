package vn.edu.hcmuaf.fit.efootwearspringboot.services.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountLoginResponse;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.AccountMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Account;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Customer;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.AccountRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.Role;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.CustomerRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.VerifyRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Autowired
    public AccountServiceImpl(
            AccountRepository accountRepository,
            AccountMapper accountMapper,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            CustomerRepository customerRepository
    ) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customerRepository = customerRepository;
    }

    @Override
    public DataResult login(AccountDto accountDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountDto.getEmail(), accountDto.getPassword()));
        Optional<Account> optional = accountRepository.findByEmail(accountDto.getEmail());
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
                        .firstName(account.getCustomer().getFirstName())
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
    public BaseResult createAccount(AccountDto accountDto) {
        Optional<Account> optional = accountRepository.findByEmail(accountDto.getEmail());
        if (optional.isPresent()) {
            return BaseResult.error(HttpStatus.BAD_REQUEST, "Tài khoản đã tồn tại");
        }

        Account account = accountMapper.toEntity(accountDto);
        account.setRole(Role.CUSTOMER);
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        account.setCustomer(new Customer());
        account.getCustomer().setAvatar("https://p7.hiclipart.com/preview/636/702/321/computer-icons-user-profile-avatar-black-man.jpg");
        account.setIsBlocked(false);

        if (!ObjectUtils.isEmpty(accountRepository.save(account))) {
            // Send mail verify


            return BaseResult.success("Chúc mừng bạn đăng ký tài khoản thành công. Vui lòng truy cập email để kích hoạt tài khoản.");
        }
        return BaseResult.error(HttpStatus.BAD_REQUEST, "Không thể thêm mới dữ liệu.");
    }


}
