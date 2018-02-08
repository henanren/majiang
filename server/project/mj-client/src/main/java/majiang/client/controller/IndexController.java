package majiang.client.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import majiang.client.model.UserAccount;
import majiang.client.model.WeixinUserInfo;
import majiang.client.services.AccountService;
import majiang.client.services.WeiXinService;
import majiang.client.services.WeixinLoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.forkjoin.apikit.core.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

/**
 * @author zuoge85@gmail.com on 2016/12/6.
 */
@Controller
public class IndexController {
    private static final Logger logger = LogManager.getLogger(IndexController.class);

    @Value("${site.title}")
    private String title;

    @Value("${site.serverUrl}")
    private String serverUrl;

    @Autowired
    private WeiXinService weiXinService;

    @Autowired
    private WeixinLoginService weiXinLoginService;

    @Autowired
    private AccountService accountService;

    @GetMapping({"/", "/index"})
    private String index(
            @CookieValue(name = AccountService.ACCOUNT_TOKEN_NAME, required = false) String accountToken,
            Model model,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        UserAccount userAccount = accountService.checkLogin(accountToken);
        String agent = request.getHeader("user-agent");
        model.addAttribute("account", userAccount);
        model.addAttribute("version", "v1");
        model.addAttribute("title", title);
        model.addAttribute("serverUrl", serverUrl);

        boolean isWeixin = agent.contains("MicroMessenger");
//      return "redirect:/hello";
        if (userAccount != null) {
            model.addAttribute("userEncrypt", accountService.checkLoginToEncrypt(userAccount));
        }
        if (isWeixin) {
            ObjectNode weixinConfig;
            if (request.getQueryString() == null) {
                weixinConfig = weiXinService.weixinConifg(request.getRequestURI());
            } else {
                weixinConfig = weiXinService.weixinConifg(request.getRequestURI() + "?" + request.getQueryString());
            }

            model.addAttribute("weixinConfig", weixinConfig);
            return "weixinIndex";
        } else {
            return "index";
        }
    }

    @GetMapping(value = "/icon/{uuid}")
    private void icon(
            @PathVariable String uuid, HttpServletResponse res
    ) throws Exception {
        byte[] icon = accountService.getIcon(uuid);
        if (icon != null) {
            res.getOutputStream().write(icon);
            res.flushBuffer();
        }
    }

    @GetMapping("/weixinLogin")
    private String weixinLogin(
            @CookieValue(name = AccountService.ACCOUNT_TOKEN_NAME, required = false) String accountToken,
            Model model,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        UserAccount userAccount = accountService.checkLogin(accountToken);
        String agent = request.getHeader("user-agent");
        if (userAccount == null && agent != null && agent.contains("MicroMessenger")) {

            Optional<WeixinUserInfo> userAccountOpt = weiXinLoginService.checkWeixin(request, response);
            if (null == userAccountOpt) {
                return "weixinLogin";
            }
            WeixinUserInfo weixinUserInfo = userAccountOpt.orElse(null);
            userAccount = accountService.login(weixinUserInfo, response);
        }
        request.setAttribute("userAccount", userAccount);
        return "weixinLogin";
    }

    @PostMapping("/appWeixinLogin")
    @ResponseBody
    private Result<String> appWeixinLogin(
            @RequestParam("data") String data,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        logger.debug("appWeixinLogin:{}", data);
        Map.Entry<WeixinUserInfo, String> resultEntry = weiXinLoginService.checkAppWeixin(
                data
        );

        if (resultEntry.getKey() != null) {
            WeixinUserInfo weixinUserInfo = resultEntry.getKey();
            UserAccount userAccount = accountService.login(weixinUserInfo, response);
            return Result.createSuccess(
                    accountService.checkLoginToEncrypt(userAccount)
            );
        } else {
            return Result.createError(resultEntry.getValue());
        }
    }
}