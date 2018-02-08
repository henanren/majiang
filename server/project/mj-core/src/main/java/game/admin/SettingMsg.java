package game.admin;

import com.isnowfox.game.proxy.message.AbstractSessionPxMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class SettingMsg extends AbstractSessionPxMsg {
    public static final int ID = PayMsg.ID + 1;

    /**
     * 广告（首页）
     */
    private String notice;
    /**
     * 广播（跑马灯）
     */
    private String radio;
    /**
     * 充值信息
     */
    private String payInfo;
    /**
     * 用户协议
     */
    private String agreement;
    /**
     * 用户默认房卡
     */
    private int initGold;

    public SettingMsg() {
        super(ID);
    }

    @Override
    public void encode(ByteBuf out) throws Exception {
        out.writeInt(initGold);
        writeString(out, notice);
        writeString(out, radio);
        writeString(out, payInfo);
        writeString(out, agreement);
    }

    @Override
    public void decode(ByteBuf in, ChannelHandlerContext ctx) throws Exception {
        initGold = in.readInt();
        notice = readString(in);
        radio = readString(in);
        payInfo = readString(in);
        agreement = readString(in);
    }


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
}
