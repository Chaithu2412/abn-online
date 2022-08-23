package nl.abnamro.recipe.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.abnamro.recipe.api.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.lang.String.format;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class RecipeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRecipeNotFound(){

       return ResponseEntity.badRequest().body(ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value()).description("Requested recipe not found").build());
    }

    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ErrorResponse> handleException(Exception e){

        log.error(format("System exception: %s", e.getMessage()), e);
        return ResponseEntity.badRequest().body(ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value()).description("internal server error").build());

    }
}
