package majiang.client.portal.client.admin.model;

import org.forkjoin.apikit.core.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleImmutableEntry;

/**
 * @author  zuoge85@gmail.com on 2017/1/11.
 */
public class SettingModel implements ApiMessage {

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

	public SettingModel() {
	}

	public SettingModel(String notice, String radio, String payInfo, String agreement, int initGold) {
		this.notice = notice;
		this.radio = radio;
		this.payInfo = payInfo;
		this.agreement = agreement;
		this.initGold = initGold;
	}

	/**
	 * 广告（首页）
	 */
	public String getNotice() {
		return notice;
	}

	/**
	 * 广告（首页）
	 */
	public void setNotice(String notice) {
		this.notice = notice;
	}

	/**
	 * 广播（跑马灯）
	 */
	public String getRadio() {
		return radio;
	}

	/**
	 * 广播（跑马灯）
	 */
	public void setRadio(String radio) {
		this.radio = radio;
	}

	/**
	 * 充值信息
	 */
	public String getPayInfo() {
		return payInfo;
	}

	/**
	 * 充值信息
	 */
	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	/**
	 * 用户协议
	 */
	public String getAgreement() {
		return agreement;
	}

	/**
	 * 用户协议
	 */
	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	/**
	 * 用户默认房卡
	 */
	public int getInitGold() {
		return initGold;
	}

	/**
	 * 用户默认房卡
	 */
	public void setInitGold(int initGold) {
		this.initGold = initGold;
	}

	@Override
	public List<Entry<String, Object>> encode(String parent, List<Entry<String, Object>> $list) {

		if (notice != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "notice", notice));
		}

		if (radio != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "radio", radio));
		}

		if (payInfo != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "payInfo", payInfo));
		}

		if (agreement != null) {
			$list.add(new SimpleImmutableEntry<String, Object>(parent + "agreement", agreement));
		}

		$list.add(new SimpleImmutableEntry<String, Object>(parent + "initGold", initGold));

		return $list;
	}

	@Override
	public String toString() {
		return "SettingModel [notice=" + notice + ",radio=" + radio + ",payInfo=" + payInfo + ",agreement=" + agreement
				+ ",initGold=" + initGold + ", ]";
	}
}