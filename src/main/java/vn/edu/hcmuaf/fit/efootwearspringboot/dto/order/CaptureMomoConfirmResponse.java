package vn.edu.hcmuaf.fit.efootwearspringboot.dto.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CaptureMomoConfirmResponse {
    private String partnerCode;
    private String orderId;
    private String requestId;
    private long amount;
    private String orderInfo;
    private String orderType;
    private String transId;
    private int resultCode;
    private String message;
    private String payType;
    private String responseTime;
    private String extraData;
    private String signature;
}
