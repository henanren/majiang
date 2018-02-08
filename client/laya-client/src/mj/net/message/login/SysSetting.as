package mj.net.message.login
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	public class SysSetting implements Message
	{
		public static const TYPE:int				=7;
		public static const ID:int					=24;
		
		public static function create(radio:String, notice:String, payInfo:String, agreement:String):SysSetting
		{
			const sysSetting:SysSetting = new SysSetting();
			sysSetting._radio = radio;
			sysSetting._notice = notice;
			sysSetting._payInfo = payInfo;
			sysSetting._agreement = agreement;
			return sysSetting;
		}
	
		/**
	 	 * 广播（跑马灯）
	 	 */
		protected var _radio:String;
		protected var _notice:String;
		protected var _payInfo:String;
		protected var _agreement:String;
		
		public function SysSetting()
		{
		}
			
		public function decode(in0:Input):void
		{
			_radio = in0.readString();
			_notice = in0.readString();
			_payInfo = in0.readString();
			_agreement = in0.readString();
		}
		
		public function encode(out:Output):void
		{
			out.writeString(radio);
			out.writeString(notice);
			out.writeString(payInfo);
			out.writeString(agreement);
		}
		

		/**
		 * 广播（跑马灯）
		 */
		public function get radio():String
		{
			return _radio;
		}
		/**
		 * 广播（跑马灯）
		 */
		public function set radio(radio:String):void
		{
			_radio = radio;
		}

		public function get notice():String
		{
			return _notice;
		}
		public function set notice(notice:String):void
		{
			_notice = notice;
		}

		public function get payInfo():String
		{
			return _payInfo;
		}
		public function set payInfo(payInfo:String):void
		{
			_payInfo = payInfo;
		}

		public function get agreement():String
		{
			return _agreement;
		}
		public function set agreement(agreement:String):void
		{
			_agreement = agreement;
		}
		
		public function toString():String
		{
			return "SysSetting [radio=" + _radio + ",notice=" + _notice + ",payInfo=" + _payInfo + ",agreement=" + _agreement + ", ]";
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