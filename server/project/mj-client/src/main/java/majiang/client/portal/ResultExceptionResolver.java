package majiang.client.portal;

import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.spring.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.Ordered;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zuoge85 on 15/4/18.
 */
public class ResultExceptionResolver extends DefaultHandlerExceptionResolver
        implements MessageSourceAware {
    private static final Logger log = LoggerFactory.getLogger(ResultExceptionResolver.class);

    @Autowired
    private ResultMappingJackson2JsonView view = new ResultMappingJackson2JsonView();;

    public ResultExceptionResolver() {
        super();
        setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    private MessageSourceAccessor messageAccessor;

    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object handler, Exception ex) {

        if (handler instanceof HandlerMethod) {
            ModelAndView modelAndView = handlerJson(ex);
            log.error("处理错误结果:{}", modelAndView);
            return modelAndView;
        }
        log.error("处理错误:{},{}", ex.getMessage(), ex.getClass().getName(), ex);
        return noJsonResolveException(request, response, handler, ex);
    }

    private ModelAndView handlerJson(Exception ex) {
        if (ex instanceof BindException) {
            BindingResult bindingResult = ((BindException) ex).getBindingResult();
            Result result = ResultUtils.transform(
                    bindingResult, messageAccessor
            );

            ModelAndView modelAndView = new ModelAndView();

            modelAndView.addObject(ResultUtils.RESULT_ATTRIBUTE_NAME, result);
            modelAndView.addObject("bindingResult", bindingResult);
            modelAndView.setView(view);
            return modelAndView;
        } else if (ex instanceof I18nValidationException) {
            I18nResult i18nResult = ((I18nValidationException) ex).getI18nResult();
            ResultUtils.handleI18n(i18nResult, messageAccessor);

            ModelAndView modelAndView = new ModelAndView();

            modelAndView.addObject(ResultUtils.RESULT_ATTRIBUTE_NAME, i18nResult);
            modelAndView.addObject("i18nResult", i18nResult);
            modelAndView.setView(view);
            return modelAndView;
        } else if (ex instanceof AccountRuntimeException) {
            String message = messageAccessor.getMessage("server.error",
                    new Object[]{ex.getMessage()});

            log.error(message);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(
                    ResultUtils.RESULT_ATTRIBUTE_NAME,
                    Result.createError(Result.ACCOUNT_ERROR, message)
            );
            modelAndView.setView(view);
            return modelAndView;
        } else {
            String message = messageAccessor.getMessage("server.error",
                    new Object[]{ex.getMessage()});
            log.error(message, ex);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(
                    ResultUtils.RESULT_ATTRIBUTE_NAME,
                    Result.createError(Result.ERROR, message)
            );
            modelAndView.setView(view);
            return modelAndView;
        }
    }


    protected ModelAndView noJsonResolveException(
            HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex
    ) {
        return super.doResolveException(request, response, handler, ex);
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageAccessor = new MessageSourceAccessor(messageSource);
    }
}
