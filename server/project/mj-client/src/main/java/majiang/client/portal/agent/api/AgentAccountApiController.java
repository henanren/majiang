package majiang.client.portal.agent.api;

import majiang.client.portal.AdminAccount;
import majiang.client.portal.AgentAccount;
import majiang.client.portal.admin.model.LoginTokenModel;
import majiang.client.portal.agent.form.AgentLoginForm;
import majiang.client.services.AdminAccountService;
import majiang.client.services.AgentUserAccountService;
import org.forkjoin.apikit.core.Account;
import org.forkjoin.apikit.core.Api;
import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.spring.I18nValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author zuoge85 on 15/6/11.
 */
@Api
@RestController
@RequestMapping(value = "agent/v1")
public class AgentAccountApiController {
    @Autowired
    private AgentUserAccountService agentUserAccountService;

    /**
     * 测试
     */
    @RequestMapping(value = "account/login", method = RequestMethod.GET)
    @Account(false)
    public Result<LoginTokenModel> login(@Valid AgentLoginForm form, HttpServletRequest request) {
        AgentAccount account = agentUserAccountService.login(form.getId(), form.getPassword());
        if (account != null) {
            LoginTokenModel m = new LoginTokenModel();
            m.setName(account.getUserDO().getName());
            m.setToken(account.getUserAgentTokenDO().getToken());
            return Result.createSuccess(m);
        } else {
            throw I18nValidationException.of("nameOrPassword", "error");
        }
    }


    @RequestMapping(value = "admin/account/test", method = RequestMethod.GET)
    @Account(false)
    public Result<String> test() {
//        Token token = agentService.bindAgent(form.getClientKey());
//        AgentModel agentModel = new AgentModel();
//
//        BeanUtils.copyProperties(token.getAgent(), agentModel);
//        agentModel.setAccessToken(token.getAccessToken());
//        return Result.createSuccess(agentModel);
        return Result.createSuccess("呵呵");
    }
}
