package vn.edu.hcmuaf.fit.efootwearspringboot.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaptureMoMoResponse {
    public String partnerCode;
    public String orderId;
    public String requestId;
    public long amount;
    public Date responseTime;

    public String message;
    public int errorCode;
    public String payUrl;
}
