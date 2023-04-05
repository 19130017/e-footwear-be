package vn.edu.hcmuaf.fit.efootwearspringboot.utils.result;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DataResult extends AbstractResult {
    private Object data;


    public DataResult(Boolean success, HttpStatus httpStatus, String message, Object data) {
        super(success, httpStatus, message);
        this.data = data;
    }

    public static DataResult success(Object data) {
        return new DataResult(true, HttpStatus.OK, "Success!", data);
    }

    public static DataResult error(HttpStatus httpStatus, String message) {
        return new DataResult(false, httpStatus, message, null);
    }
}
