package vn.edu.hcmuaf.fit.efootwearspringboot.utils.result;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseResult extends AbstractResult {
    public BaseResult(Boolean success, HttpStatus httpStatus, String message) {
        super(success, httpStatus, message);
    }

    public static BaseResult success() {
        return new BaseResult(true, HttpStatus.OK, "Success!");
    }

    public static BaseResult error(HttpStatus httpStatus, String message) {
        return new BaseResult(false, httpStatus, message);
    }

}
