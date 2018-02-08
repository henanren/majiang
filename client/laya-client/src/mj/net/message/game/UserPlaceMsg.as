package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 一局麻将的信息
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class UserPlaceMsg implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=20;
		
		public static function create(shouPai:Array, shouPaiLen:int, anGang:Array, xiaoMingGang:Array, daMingGang:Array, peng:Array, chi:Array, outPai:Array):UserPlaceMsg
		{
			const userPlaceMsg:UserPlaceMsg = new UserPlaceMsg();
			userPlaceMsg._shouPai = shouPai;
			userPlaceMsg._shouPaiLen = shouPaiLen;
			userPlaceMsg._anGang = anGang;
			userPlaceMsg._xiaoMingGang = xiaoMingGang;
			userPlaceMsg._daMingGang = daMingGang;
			userPlaceMsg._peng = peng;
			userPlaceMsg._chi = chi;
			userPlaceMsg._outPai = outPai;
			return userPlaceMsg;
		}
	
		protected var _shouPai:Array;
		/**
	 	 * 别人的信息只显示手牌数量
	 	 */
		protected var _shouPaiLen:int;
		/**
	 	 * 已经显示的暗杠，如果是自己的则全部显示,不是自己的且如果还不能显示，那么传递-1
	 	 */
		protected var _anGang:Array;
		protected var _xiaoMingGang:Array;
		protected var _daMingGang:Array;
		protected var _peng:Array;
		/**
	 	 * 3个一组一组
	 	 */
		protected var _chi:Array;
		/**
	 	 * 打出的牌
	 	 */
		protected var _outPai:Array;
		
		public function UserPlaceMsg()
		{
		}
			
		public function decode(in0:Input):void
		{
			_shouPai = in0.readIntArray();
			_shouPaiLen = in0.readInt();
			_anGang = in0.readIntArray();
			_xiaoMingGang = in0.readIntArray();
			_daMingGang = in0.readIntArray();
			_peng = in0.readIntArray();
			_chi = in0.readIntArray();
			_outPai = in0.readIntArray();
		}
		
		public function encode(out:Output):void
		{
			out.writeIntArray(shouPai);
			out.writeInt(shouPaiLen);
			out.writeIntArray(anGang);
			out.writeIntArray(xiaoMingGang);
			out.writeIntArray(daMingGang);
			out.writeIntArray(peng);
			out.writeIntArray(chi);
			out.writeIntArray(outPai);
		}
		

		public function get shouPai():Array
		{
			return _shouPai;
		}
		public function set shouPai(shouPai:Array):void
		{
			_shouPai = shouPai;
		}

		/**
		 * 别人的信息只显示手牌数量
		 */
		public function get shouPaiLen():int
		{
			return _shouPaiLen;
		}
		/**
		 * 别人的信息只显示手牌数量
		 */
		public function set shouPaiLen(shouPaiLen:int):void
		{
			_shouPaiLen = shouPaiLen;
		}

		/**
		 * 已经显示的暗杠，如果是自己的则全部显示,不是自己的且如果还不能显示，那么传递-1
		 */
		public function get anGang():Array
		{
			return _anGang;
		}
		/**
		 * 已经显示的暗杠，如果是自己的则全部显示,不是自己的且如果还不能显示，那么传递-1
		 */
		public function set anGang(anGang:Array):void
		{
			_anGang = anGang;
		}

		public function get xiaoMingGang():Array
		{
			return _xiaoMingGang;
		}
		public function set xiaoMingGang(xiaoMingGang:Array):void
		{
			_xiaoMingGang = xiaoMingGang;
		}

		public function get daMingGang():Array
		{
			return _daMingGang;
		}
		public function set daMingGang(daMingGang:Array):void
		{
			_daMingGang = daMingGang;
		}

		public function get peng():Array
		{
			return _peng;
		}
		public function set peng(peng:Array):void
		{
			_peng = peng;
		}

		/**
		 * 3个一组一组
		 */
		public function get chi():Array
		{
			return _chi;
		}
		/**
		 * 3个一组一组
		 */
		public function set chi(chi:Array):void
		{
			_chi = chi;
		}

		/**
		 * 打出的牌
		 */
		public function get outPai():Array
		{
			return _outPai;
		}
		/**
		 * 打出的牌
		 */
		public function set outPai(outPai:Array):void
		{
			_outPai = outPai;
		}
		
		public function toString():String
		{
			return "UserPlaceMsg [shouPai=" + _shouPai + ",shouPaiLen=" + _shouPaiLen + ",anGang=" + _anGang + ",xiaoMingGang=" + _xiaoMingGang + ",daMingGang=" + _daMingGang + ",peng=" + _peng + ",chi=" + _chi + ",outPai=" + _outPai + ", ]";
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