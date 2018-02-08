package majiang.client.portal;

import majiang.client.services.AdminAccountService;
import majiang.client.services.AgentUserAccountService;
import org.forkjoin.apikit.core.Account;
import org.forkjoin.apikit.spring.AccountHandlerInterceptor;
import org.forkjoin.apikit.spring.AccountRuntimeException;
import org.forkjoin.apikit.spring.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;

/**
 * 加入微信相关!
 *
 * @author zuoge85 on 15/4/25.
 */
@Component
public class AdminAccountHandlerInterceptor extends AccountHandlerInterceptor<AccountObject> {
    private static final Logger log = LoggerFactory.getLogger(AdminAccountHandlerInterceptor.class);
    public static final String ADMIN_ACCOUNT_TOKEN_HEADER_NAME = "adminAccountToken";
    public static final String AGENT_ACCOUNT_TOKEN_HEADER_NAME = "agentAccountToken";
    @Autowired
    private AdminAccountService adminAccountService;
    @Autowired
    private AgentUserAccountService agentUserAccountService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("request:{}", RequestUtils.dump(request));
        }

        if (!(handler instanceof HandlerMethod) || !request.getRequestURI().startsWith("/v1")) {
            return true;
        } else {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Account methodAnnotation = handlerMethod.getMethodAnnotation(Account.class);
            if (methodAnnotation == null || methodAnnotation.value()) {
                AccountObject accountObject = this.getAccountObject(request);
                if (accountObject == null) {
                    throw new AccountRuntimeException("未登录:" + request.getRequestURI() + ",ParameterMap:" + request.getParameterMap());
                }

                if (!this.check(handlerMethod, accountObject)) {
                    throw new AccountRuntimeException("没有权限:" + request.getRequestURI() + ",ParameterMap:" + RequestUtils.dump(request));
                }

                request.setAttribute(ACCOUNT_REQUEST_ATTRIBUTE, accountObject);
            }

            return super.preHandle(request, response, handler);
        }
    }

    @Override
    protected AccountObject getAccountObject(HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();

        if (requestURI.startsWith("admin")) {
            String adminAccessToken = httpServletRequest.getHeader(ADMIN_ACCOUNT_TOKEN_HEADER_NAME);
            if (adminAccessToken == null) {
                return null;
            }
            try {
                return adminAccountService.getByToken(adminAccessToken);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        } else if (requestURI.startsWith("agent")) {
            String agentAccessToken = httpServletRequest.getHeader(AGENT_ACCOUNT_TOKEN_HEADER_NAME);
            if (agentAccessToken == null) {
                return null;
            }
            try {
                return agentUserAccountService.getByToken(agentAccessToken);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
