package ma.emsi.javaproject.response;

import lombok.Data;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor

public class ErrorRes {
    HttpStatus httpStatus;
    String message;
}