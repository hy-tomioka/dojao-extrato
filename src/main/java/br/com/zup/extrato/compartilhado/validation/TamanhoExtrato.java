package br.com.zup.extrato.compartilhado.validation;

import org.hibernate.validator.constraints.ConstraintComposition;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.hibernate.validator.constraints.CompositionType.AND;

@Positive
@Max(value = 50)
@ConstraintComposition(AND)
@Constraint(validatedBy = {})
@Documented
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface TamanhoExtrato {

    String message() default "Tamanho do extrato é inválido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
