package vn.edu.hcmuaf.fit.efootwearspringboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.Role;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Account;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.account.AccountService;

@SpringBootApplication
public class EFootwearSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EFootwearSpringbootApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AccountService service
    ) {
        return args -> {
            var admin = AccountDto.builder()
                    .username("admin")
                    .email("admin@gmail.com")
                    .password("admin")
                    .role("ADMIN")
                    .isBlocked(false)
                    .build();
            service.createAccount(admin);
        };
    }
}
