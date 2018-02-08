//登陆信息
handler=server
message{
    String type;//SMS 短信登录，WEIXIN_CLIENT 微信客户端，TOKEN token登录
	String openId;
	String code;
	String longitude;//经度
	String latitude;//纬度
}