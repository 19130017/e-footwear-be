package vn.edu.hcmuaf.fit.efootwearspringboot.services.order_status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.NotFoundException;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.OrderStatusMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.OrderStatus;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.OrderStatusRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;
    private final OrderStatusMapper orderStatusMapper;

    @Autowired
    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository, OrderStatusMapper orderStatusMapper) {
        this.orderStatusRepository = orderStatusRepository;
        this.orderStatusMapper = orderStatusMapper;
    }

    @Override
    public DataResult getAll() {
        List<OrderStatus> optional = orderStatusRepository.findAll();
        if (optional.size() == 0) throw new NotFoundException("Không tìm thấy trạng thái đơn hàng");

        return DataResult.success(orderStatusRepository.findAll());
    }

    @Override
    public DataResult countByDescription() {
        List<Object[]> optional = orderStatusRepository.countByDescription();
        if (optional.isEmpty()) {
            throw new NotFoundException("Không tìm thấy đơn hàng nào!");
        }
        return DataResult.success(optional);
    }
}
