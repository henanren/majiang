package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	
	import mj.net.message.login.OptionEntry;

	import laya.utils.*;
	
	/**
 	 * 创建房间
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class CreateRoom implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=0;
		
		public static function create(profile:String, options:Array):CreateRoom
		{
			const createRoom:CreateRoom = new CreateRoom();
			createRoom._profile = profile;
			createRoom._options = options;
			return createRoom;
		}
	
		protected var _profile:String;
		protected var _options:Array;
		
		public function CreateRoom()
		{
		}
			
		public function decode(in0:Input):void
		{
			_profile = in0.readString();
		
			var optionsLen:int = in0.readInt();
			if(optionsLen == -1)
			{
				_options = null;
			}
			else
			{
				_options = new Array();
				for(var optionsI:int = 0; optionsI < optionsLen; optionsI++)
				{
					var optionsItem:OptionEntry = new OptionEntry();
					optionsItem.decode(in0);
					_options[optionsI] = (optionsItem);
				}
			}
		}
		
		public function encode(out:Output):void
		{
			out.writeString(profile);
		
			if(options == null){
				out.writeInt(-1);
			}else{
				var optionsLen:int = options.length;
				out.writeInt(optionsLen);
				for(var optionsI:int = 0; optionsI < optionsLen; optionsI++)
				{
					options[optionsI].encode(out);
				}
			}
		}
		

		public function get profile():String
		{
			return _profile;
		}
		public function set profile(profile:String):void
		{
			_profile = profile;
		}

		public function get options():Array
		{
			return _options;
		}
		public function set options(options:Array):void
		{
			_options = options;
		}
		
		public function toString():String
		{
			return "CreateRoom [profile=" + _profile + ",options=" + _options + ", ]";
		}
		
		public function getMessageType():int
		{
			return TYPE;
		}
		
		public function getMessageId():int
		{
			return ID;
		}
	}
}