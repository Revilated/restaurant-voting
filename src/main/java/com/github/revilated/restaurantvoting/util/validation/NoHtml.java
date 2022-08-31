package com.github.revilated.restaurantvoting.util.validation;

import javax.validation.*;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Documented
@Constraint(validatedBy = NoHtmlValidator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface NoHtml {
    String message() default "{error.noHtml}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
