package vn.edu.hcmuaf.fit.efootwearspringboot.constants;

public class Momo {
    public static String PARTNER_CODE = "MOMOPX4620210825";
    public static String ACCESS_KEY = "jlvk7j0AwGRgppFm";
    public static String SECRET_KEY = "OZWc3Q8QQN930vAjiEUnm4YOyZLs1Khn";
    public static String ENDPOINT = "https://test-payment.momo.vn/v2/gateway/api/create";
    public static String REDIRECT_URL = "http://localhost:3000/payment/response";
    public static String IPN_URL = "http://localhost:8080/orders/payment/momo/success";
}
