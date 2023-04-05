package vn.edu.hcmuaf.fit.efootwearspringboot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private String message;
    private String code;

    public BaseException(String message) {
        this.message = message;
    }
}
