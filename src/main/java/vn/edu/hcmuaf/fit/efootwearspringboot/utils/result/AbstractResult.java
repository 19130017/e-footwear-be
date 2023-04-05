package vn.edu.hcmuaf.fit.efootwearspringboot.utils.result;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class AbstractResult {
    protected Boolean success;
    protected int httpStatusCode;
    protected HttpStatus httpStatus;
    protected String message;

    public AbstractResult(Boolean success, HttpStatus httpStatus, String message) {
        this.success = success;
        this.httpStatusCode = httpStatus.value();
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
