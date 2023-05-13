package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.account.AccountSlimDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Account;

import java.util.List;

@Mapper(componentModel = "spring")
@Component("accountMapper")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto toDto(Account account);
    AccountSlimDto toSlimDto(Account account);


    Account toEntity(AccountDto accountDto);

    List<Account> toEntities(List<AccountDto> accountDtos);

    List<AccountDto> toDtos(List<Account> accounts);
}
