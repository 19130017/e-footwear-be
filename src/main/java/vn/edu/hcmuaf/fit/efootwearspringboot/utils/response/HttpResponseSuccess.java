package vn.edu.hcmuaf.fit.efootwearspringboot.utils.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class HttpResponseSuccess extends HttpResponse {
    private Object data;

    public HttpResponseSuccess(Boolean success, int httpStatusCode, HttpStatus httpStatus, ZonedDateTime timestamp, Object data) {
        super(success, httpStatusCode, httpStatus, timestamp);
        this.data = data;
    }


    public static HttpResponseSuccess success() {
        return new HttpResponseSuccess(true, HttpStatus.OK.value(), HttpStatus.OK, ZonedDateTime.now(), "Success!");
    }

    public static HttpResponseSuccess success(Object data) {
        return new HttpResponseSuccess(true, HttpStatus.OK.value(), HttpStatus.OK, ZonedDateTime.now(), data);
    }
}
