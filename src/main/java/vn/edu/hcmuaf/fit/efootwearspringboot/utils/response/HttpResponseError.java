package vn.edu.hcmuaf.fit.efootwearspringboot.utils.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class HttpResponseError extends HttpResponse {
    private String message;

    public HttpResponseError(Boolean success, int httpStatusCode, HttpStatus httpStatus, ZonedDateTime timestamp, String message) {
        super(success, httpStatusCode, httpStatus, timestamp);
        this.message = message;
    }

    public static HttpResponseError error() {
        return new HttpResponseError(false, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(), null);
    }

    public static HttpResponseError error(String message) {
        return new HttpResponseError(false, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(), message);
    }

    public static HttpResponseError error(HttpStatus status, String message) {
        return new HttpResponseError(false, status.value(), status, ZonedDateTime.now(), message);
    }
}
