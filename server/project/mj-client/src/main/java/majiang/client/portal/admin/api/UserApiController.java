package majiang.client.portal.admin.api;

import game.boss.dao.entity.UserDO;
import majiang.client.portal.admin.form.PayForm;
import majiang.client.portal.admin.model.PageModel;
import majiang.client.portal.admin.model.UserModel;
import majiang.client.services.AdminUserService;
import org.forkjoin.apikit.core.Api;
import org.forkjoin.apikit.core.Result;
import org.forkjoin.apikit.spring.I18nValidationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zuoge85 on 15/6/11.
 */
@Api
@RestController
@RequestMapping(value = "admin/v1")
public class UserApiController {
    @Autowired
    private AdminUserService adminUserService;

    /**
     * 测试
     */
    @RequestMapping(value = "user/list/{page}/{pageSize}", method = RequestMethod.GET)
    public Result<PageModel<UserModel>> list(
            @PathVariable int page, @PathVariable int pageSize
    ) {
        return Result.createSuccess(adminUserService.getPage(
                page, pageSize
        ));
    }

    @RequestMapping(value = "user/{userId}/level/{level}", method = RequestMethod.POST)
    public Result<Void> changelevel(@PathVariable int userId, @PathVariable int level) {
        adminUserService.changeLevel(userId, level);
        return Result.createSuccess();
    }

    @RequestMapping(value = "user/pay", method = RequestMethod.POST)
    public Result<UserModel> pay(
            @Valid PayForm form
    ) throws InterruptedException {
        UserDO userDo = adminUserService.pay(form.getId(), form.getGold());
        if (userDo == null) {
            throw I18nValidationException.of("user", "error");
        }
        UserModel m = new UserModel();
        BeanUtils.copyProperties(userDo, m);
        return Result.createSuccess(m);
    }
}
