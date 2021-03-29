package com.medimpact.medeasy.service.validation;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.*;
import java.util.Set;
/** 
 * 全局校验器
* @author Crystal E-mail: 
* @version 创建时间：2017年11月9日 
* 类说明 
*/
public class GlobalValidator {
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();
    private static final Class[] defaultGroup = new Class<?>[]{};

    public static <T> void validate(T bean, Class<?>... groups) {
        groups = ArrayUtils.isEmpty(groups) ? defaultGroup : groups;
        checkValidatedResult(validator.validate(bean, groups));
    }

    public static <T> void validate(T bean) {
        checkValidatedResult(validator.validate(bean, defaultGroup));
    }

    private static <T> void checkValidatedResult(Set<ConstraintViolation<T>> violations) throws ValidationException {
        if (CollectionUtils.isEmpty(violations)) {
            return;
        }
        String message = getErrorMessage(violations);
        throw new ValidationException(message);
    }

    /**
     * 将验证结果集合的描述信息转换为字符串
     *
     * @param violations
     * @return 错误消息
     */
    private static <T> String getErrorMessage(Set<ConstraintViolation<T>> violations) {
        if (CollectionUtils.isEmpty(violations)) {
            return "";
        }
        String[] traces = new String[violations.size()];
        int i = 0;
        StringBuilder message = new StringBuilder();
        for (ConstraintViolation<T> violation : violations) {
            Object value = violation.getInvalidValue();
            if (value != null && value.getClass().isArray()) {
                value = ArrayUtils.toString(value);
            }
            message.append("[property=" + violation.getPropertyPath().toString() + "; invalidValue=" + value
                    + "; message=");
            message.append(StringUtils.isBlank(violation.getMessage()) ? violation.getMessageTemplate() : violation.getMessage());
            message.append("]");
            traces[i++] = message.toString();
            message.setLength(0);
        }
        return ArrayUtils.toString(traces);
    }

}
