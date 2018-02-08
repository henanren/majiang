//登陆结果
handler=client
message{
    //id
    int id;
	//昵称
	String name;
	//昵称
	String openId;
	//uuid
	String uuid;
	//头像
	String avatar;
	//0:女生,1:男生,2:未知
	int sex;
    //如果用户进入过房间,未主动退出房间id
	String roomCheckId;
	int gold;
	String loginToken;
	String ip;
}