package vn.edu.hcmuaf.fit.efootwearspringboot.services.address_delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.EntityState;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.address_delivery.AddressDeliveryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.AddressDeliveryMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Account;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Address;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.AddressDelivery;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.AccountRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.AddressDeliveryRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.util.List;
import java.util.Optional;

@Service
public class AddressDeliveryServiceImpl implements AddressDeliveryService {
    private AddressDeliveryRepository addressDeliveryRepository;
    private AddressDeliveryMapper addressDeliveryMapper;
    private AccountRepository accountRepository;

    @Autowired
    public AddressDeliveryServiceImpl(
            AddressDeliveryRepository addressDeliveryRepository,
            AddressDeliveryMapper addressDeliveryMapper,
            AccountRepository accountRepository
    ) {
        this.addressDeliveryRepository = addressDeliveryRepository;
        this.addressDeliveryMapper = addressDeliveryMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public DataResult getAddresses(Long accountId) {
        Optional<List<AddressDelivery>> optional = addressDeliveryRepository.findAddresses(accountId);
        if (optional.isPresent()) {
            List<AddressDelivery> addresses = optional.get();
            return DataResult.success(addressDeliveryMapper.toDtos(addresses));
        }
        return DataResult.error(HttpStatus.BAD_REQUEST, "Tài khoản không tồn tại");
    }

    @Override
    public BaseResult createAddress(AddressDeliveryDto addressDeliveryDto, Long accountId) {
        Optional<Account> optional = accountRepository.findById(accountId);
        if (optional.isPresent()) {
            AddressDelivery addressDelivery = addressDeliveryMapper.toEntity(addressDeliveryDto);

            // get all the addresses by account id
            Optional<List<AddressDelivery>> optionalAddress = addressDeliveryRepository.findAddresses(accountId);
            List<AddressDelivery> addresses = optionalAddress.get();
            if (addresses.isEmpty()) {
                addressDelivery.setIsDefault(true);
            }

            // set account
            addressDelivery.setAccount(optional.get());
            addressDelivery.setState(EntityState.ACTIVE);

            // địa chỉ thêm mới được để là mặc định -> set những địa chỉ khác thành bình thường
            if (optionalAddress.isPresent() && addressDelivery.getIsDefault() == true) {
                List<AddressDelivery> list = optionalAddress.get();
                for (AddressDelivery address : list) {
                    address.setIsDefault(false);
                    addressDeliveryRepository.save(address);
                }
            }

            if (!ObjectUtils.isEmpty(addressDeliveryRepository.save(addressDelivery)))
                return BaseResult.success();
            return BaseResult.error(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi từ hệ thống");
        }
        return BaseResult.error(HttpStatus.BAD_REQUEST, "Tài khoản không tồn tại");
    }

    @Override
    public BaseResult updateAddress(Long accountId, AddressDeliveryDto addressDeliveryDto) {
        Optional<Account> optional = accountRepository.findById(accountId);
        if (optional.isPresent()) {
            // update address to default
            if (addressDeliveryDto.getIsDefault() == true) {
                Optional<List<AddressDelivery>> optionalAddress = addressDeliveryRepository.findAddresses(accountId);
                List<AddressDelivery> list = optionalAddress.get();
                if (list.size() > 1) {
                    for (AddressDelivery address : list) {
                        address.setIsDefault(false);
                        addressDeliveryRepository.save(address);
                    }
                }
            }

            // get address with id
            Optional<AddressDelivery> optionalAddress = addressDeliveryRepository.findById(addressDeliveryDto.getId());

            if (optionalAddress.isPresent()) {
                AddressDelivery addressDelivery = addressDeliveryMapper.toEntity(addressDeliveryDto);
                addressDelivery.setAccount(optional.get());
                addressDelivery.setState(EntityState.ACTIVE);

                if (!ObjectUtils.isEmpty(addressDeliveryRepository.save(addressDelivery))) {
                    return BaseResult.success();
                }
                return BaseResult.error(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi từ hệ thống");
            }
            return BaseResult.error(HttpStatus.BAD_REQUEST, "Địa chỉ không tồn tại");
        }
        return BaseResult.error(HttpStatus.BAD_REQUEST, "Tài khoản tồn tại");
    }

    @Override
    public DataResult getAddress(Long id) {
        Optional<AddressDelivery> optional = addressDeliveryRepository.findById(id);
        if (optional.isPresent()) {
            AddressDelivery address = optional.get();
            return DataResult.success(addressDeliveryMapper.toDto(address));
        }
        return DataResult.error(HttpStatus.BAD_REQUEST, "Không tim thấy địa chỉ");
    }

    @Override
    public BaseResult deleteAddress(Long id) {
        Optional<AddressDelivery> optional = addressDeliveryRepository.findById(id);
        if (optional.isPresent()) {
            AddressDelivery addressDelivery = optional.get();
            if (addressDelivery.getIsDefault() == true) {
                return BaseResult.error(HttpStatus.BAD_REQUEST, "Không thể xoá địa chỉ mặc định");
            }
            addressDelivery.setState(EntityState.DELETED);
            addressDeliveryRepository.save(addressDelivery);
            return BaseResult.success();
        }
        return BaseResult.error(HttpStatus.BAD_REQUEST, "Không tim thấy địa chỉ");
    }
}
