package vn.edu.hcmuaf.fit.efootwearspringboot.services.address_delivery;

import vn.edu.hcmuaf.fit.efootwearspringboot.dto.address_delivery.AddressDeliveryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

public interface AddressDeliveryService {
    DataResult getAddresses(Long accountId);

    BaseResult createAddress(AddressDeliveryDto addressDeliveryDto, Long accountId);
}
