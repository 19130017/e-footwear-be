package vn.edu.hcmuaf.fit.efootwearspringboot.utils;

public enum OrderStatus {
    CANCEL("Huỷ"),
    CHECKING("Đang kiểm tra"),
    DELIVERY("Đang giao hàng"),
    SUCCESS("Giao hàng thành công"),
    CONFIRMATION("Xác nhận đơn hàng");
    String title;

    OrderStatus(String title) {
        this.title = title;
    }

}
