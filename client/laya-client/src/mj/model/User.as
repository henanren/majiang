package mj.model
{
    import mj.net.message.login.LoginRet;

    /**
     * @author zuoge85@gmail.com on 16/10/3.
     */
    public class User
    {
        /**
         * id
         */
        private var _id:int;
        /**
         * 昵称
         */
        private var _name:String;
        /**
         * 昵称
         */
        private var _openId:String;
        /**
         * uuid
         */
        private var _uuid:String;
        /**
         * 头像
         */
        private var _avatar:String;
        /**
         * 0:女生,1:男生,2:未知
         */
        private var _sex:int;
        private var _gold:Number;
        private var _ip:String;


        public function User(ret:LoginRet)
        {
            this._avatar = ret.avatar;
            this._id = ret.id;
            this._name = ret.name;
            this._openId = ret.openId;
            this._sex = ret.sex;
            this._uuid = ret.uuid;
            this._gold = ret.gold;
            this._ip = ret.ip;
        }

        public function get id():int
        {
            return _id;
        }

        public function set id(value:int):void
        {
            _id = value;
        }

        public function get name():String
        {
            return _name;
        }

        public function set name(value:String):void
        {
            _name = value;
        }

        public function get openId():String
        {
            return _openId;
        }

        public function set openId(value:String):void
        {
            _openId = value;
        }

        public function get uuid():String
        {
            return _uuid;
        }

        public function set uuid(value:String):void
        {
            _uuid = value;
        }

        public function get avatar():String
        {
            return _avatar;
        }

        public function set avatar(value:String):void
        {
            _avatar = value;
        }

        public function get sex():int
        {
            return _sex;
        }

        public function set sex(value:int):void
        {
            _sex = value;
        }


        public function get gold():Number
        {
            return _gold;
        }

        public function set gold(value:Number):void
        {
            _gold = value;
        }


        public function get ip():String
        {
            return _ip;
        }

        public function set ip(value:String):void
        {
            _ip = value;
        }
    }
}
