package majiang.client.services;

import game.admin.SettingMsg;
import game.boss.dao.dao.SettingDao;
import game.boss.dao.entity.SettingDO;
import majiang.client.boss.BossClient;
import majiang.client.portal.admin.form.SettingForm;
import majiang.client.portal.admin.model.SettingModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zuoge85@gmail.com on 2017/1/7.
 */
@Service
public class AdminSettingService {
    public static final int DEFAULT_ID = 1;
    @Autowired
    private SettingDao settingDao;
    @Autowired
    private BossClient bossClient;

    public SettingModel getSetting() {
        SettingModel settingModel = new SettingModel();

        SettingDO settingDO = settingDao.get(DEFAULT_ID);
        if (settingDO != null) {
            BeanUtils.copyProperties(settingDO, settingModel);
        }
        return settingModel;
    }

    public SettingModel change(SettingForm form) {
        SettingDO settingDO = settingDao.get(DEFAULT_ID);
        if (settingDO == null) {
            settingDO = new SettingDO();
            settingDO.setId(DEFAULT_ID);
        }
        BeanUtils.copyProperties(form, settingDO);
        settingDao.replace(settingDO);

        SettingModel settingModel = new SettingModel();
        BeanUtils.copyProperties(settingDO, settingModel);

        SettingMsg msg = new SettingMsg();
        BeanUtils.copyProperties(settingDO, msg);
        bossClient.writeAndFlush(msg);
        return settingModel;
    }

    public void changeAgreement(String agreement) {
        change(SettingDO.Table.AGREEMENT, agreement);
    }

    public void changePayInfo(String payInfo) {
        change(SettingDO.Table.PAYINFO, payInfo);
    }

    public void changeRadio(String radio) {
        change(SettingDO.Table.RADIO, radio);
    }

    public void changeNotice(String notice) {
        change(SettingDO.Table.NOTICE, notice);
    }

    private void change(String fieldName, String value) {
        SettingDO settingDO = settingDao.get(DEFAULT_ID);
        if (settingDO != null) {
            settingDao.updatePartial(
                    fieldName, value,
                    settingDO.getKey()
            );
        } else {
            settingDO = new SettingDO();
            settingDO.set(fieldName, value);
            settingDO.setId(DEFAULT_ID);
            settingDao.replace(settingDO);
        }
    }


}
