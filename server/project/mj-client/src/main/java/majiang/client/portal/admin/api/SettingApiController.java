package majiang.client.portal.admin.api;

import majiang.client.portal.admin.form.SettingForm;
import majiang.client.portal.admin.model.SettingModel;
import majiang.client.services.AdminSettingService;
import org.forkjoin.apikit.core.Api;
import org.forkjoin.apikit.core.Result;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SettingApiController {
    @Autowired
    private AdminSettingService adminSettingService;

    /**
     * 测试
     */
    @RequestMapping(value = "setting", method = RequestMethod.GET)
    public Result<SettingModel> get() {
        return Result.createSuccess(adminSettingService.getSetting());
    }


    @RequestMapping(value = "setting", method = RequestMethod.POST)
    public Result<SettingModel> change(@Valid SettingForm form) {
        return Result.createSuccess(adminSettingService.change(form));
    }

//
//    @RequestMapping(value = "setting/agreement", method = RequestMethod.POST)
//    public Result<Void> changeAgreement(@Valid ValueForm form) {
//        adminSettingService.changeAgreement(form.getValue());
//        return Result.createSuccess();
//    }
//
//    @RequestMapping(value = "setting/payInfo", method = RequestMethod.POST)
//    public Result<Void> changePayInfo(@Valid ValueForm form) {
//        adminSettingService.changePayInfo(form.getValue());
//        return Result.createSuccess();
//    }
//
//    @RequestMapping(value = "setting/radio", method = RequestMethod.POST)
//    public Result<Void> changeRadio(@Valid ValueForm form) {
//        adminSettingService.changeRadio(form.getValue());
//        return Result.createSuccess();
//    }
//
//    @RequestMapping(value = "setting/notice", method = RequestMethod.POST)
//    public Result<Void> changeNotice(@Valid ValueForm form) {
//        adminSettingService.changeNotice(form.getValue());
//        return Result.createSuccess();
//    }
}
