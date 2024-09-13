package in.ashokit.exception;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, Model m) {
        logger.error(e.getMessage());
        m.addAttribute("email", "Email already used. Try Again!");
        return "index";
    }
}
