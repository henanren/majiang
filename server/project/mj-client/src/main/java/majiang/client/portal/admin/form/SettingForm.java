package majiang.client.portal.admin.form;

import org.forkjoin.apikit.core.Message;

import javax.validation.constraints.NotNull;

/**
 *
 */
@Message
public class SettingForm {
    /**广告（首页）*/
    @NotNull
    private String notice;

    /**广播（跑马灯）*/
    @NotNull
    private String radio;

    /**充值信息*/
    @NotNull
    private String payInfo;

    /**用户协议*/
    @NotNull
    private String agreement;

    /**用户默认房卡*/
    private int initGold;

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public int getInitGold() {
        return initGold;
    }

    public void setInitGold(int initGold) {
        this.initGold = initGold;
    }

    @Override
    public String toString() {
        return "SettingForm{" +
                "notice='" + notice + '\'' +
                ", radio='" + radio + '\'' +
                ", payInfo='" + payInfo + '\'' +
                ", agreement='" + agreement + '\'' +
                ", initGold=" + initGold +
                '}';
    }
}
