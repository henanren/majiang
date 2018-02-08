package mj.ui.dialog
{
    import laya.display.Sprite;
    import laya.display.Text;
    import laya.resource.Texture;
    import laya.ui.Box;
    import laya.ui.Image;
    import laya.utils.Handler;

    import mj.net.message.game.GameUserInfo;
    import mj.net.message.login.RoomHistoryListRet;

    import ui.test.UserDialogUI;

    /**
     * @author zuoge85@gmail.com on 2016/11/9.
     */
    public class UserDialog extends UserDialogUI implements DialogContent
    {

        private static var dialog:Dialog;

        public static function showDialog(name:String, avatar:String, ip:String, id:int,
                                          user:GameUserInfo = null, sceneUsers:Array = null):Dialog
        {
            if (dialog)
            {
                close();
            }
            var userDialog:UserDialog = new UserDialog(name, avatar, ip, id, user, sceneUsers);


            dialog = Dialog.showDialog(
                    DialogSize.AUTO, userDialog, "用户信息", null, Handler.create(
                            null, close
                    ), "确定"
            );

            return dialog;
        }

        public static function close():void
        {
            if (dialog)
            {
                dialog.close();
                dialog = null;
            }
        }


        private var nWidth:Number;
        private var nHeight:Number;
        private var msg:RoomHistoryListRet;

        public function UserDialog(name:String, avatar:String, ip:String, id:int,
                                   user:GameUserInfo = null, sceneUsers:Array = null)
        {
            super();
            this.nWidth = this.width;
            this.nHeight = this.height;
            this.msg = msg;


            userName.text = name;
            userId.text = "ID:" + id;
            this.ip.text = "IP:" + ip;

            var image:Image = userHeadIcon;
            var maskImage:Image = userHeadMask;
            userHeadIconOut.mask = userHeadMask;
            if (avatar)
            {
                Laya.loader.load(avatar, Handler.create(this, function ():void
                {
                    var t:Texture = Laya.loader.getRes(avatar);
                    image.skin = avatar;
                    var radioW:Number = userHeadIcon.width / t.width;
                    var radioH:Number = userHeadIcon.height / t.height;
                    var radio:Number = radioW > radioH ? radioW : radioH;

                    image.width = t.width;
                    image.height = t.height;
                    image.scale(radio, radio);
                    maskImage.visible = false;
                    image.pivotX = -(userHeadIcon.width - (t.width * radio)) / 2 + userHeadIcon.x;
                    image.pivotY = -(userHeadIcon.width - (t.height * radio)) / 2 + userHeadIcon.y;

                    userHeadIconOut.mask = maskImage;
                }));
            }

            this.userInfo0.visible = false;
            this.userInfo1.visible = false;
            this.userInfo2.visible = false;
            //初始化距离信息
            if (user)
            {
                for (var i:int = 0, l:int = 0; i < sceneUsers.length; i++)
                {
                    var curUser:GameUserInfo = sceneUsers[i];
                    if (curUser != null && user.locationIndex != curUser.locationIndex)
                    {
                        var userInfo:Box = this["userInfo" + l];

                        Text(userInfo.getChildByName("name")).text = curUser.userName;
                        var distance:String = curUser["user" + i + "Distance"];
                        Text(userInfo.getChildByName("distance")).text = "距离：" + (distance != null ? distance : "未知");
                        Text(userInfo.getChildByName("ip")).text = "IP:" + curUser.ip;

                        userInfo.visible = true;
                        l++;
                    }
                }
            }
        }

        public function layout():void
        {
            init();
        }

        private function init():void
        {

        }


        public function getDisplayObject():Sprite
        {
            return this;
        }

        public function getContentWidth():Number
        {
            return nWidth * this.scaleX;
        }

        public function getContentHeight():Number
        {
            return nHeight * this.scaleY;
        }
    }
}
