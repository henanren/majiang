package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	

	import laya.utils.*;
	
	/**
 	 * 牌局结束
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class GameFanResult implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=5;
		
		public static function create(queTou:int, shunZi:Array, keZi:Array, shouPai:Array, huiErBian:Array, anGang:Array, xiaoMingGang:Array, daMingGang:Array, peng:Array, chi:Array, baseFanType:String, fanString:String, fan:int, userName:String, score:int, guaFengXiaYu:int):GameFanResult
		{
			const gameFanResult:GameFanResult = new GameFanResult();
			gameFanResult._queTou = queTou;
			gameFanResult._shunZi = shunZi;
			gameFanResult._keZi = keZi;
			gameFanResult._shouPai = shouPai;
			gameFanResult._huiErBian = huiErBian;
			gameFanResult._anGang = anGang;
			gameFanResult._xiaoMingGang = xiaoMingGang;
			gameFanResult._daMingGang = daMingGang;
			gameFanResult._peng = peng;
			gameFanResult._chi = chi;
			gameFanResult._baseFanType = baseFanType;
			gameFanResult._fanString = fanString;
			gameFanResult._fan = fan;
			gameFanResult._userName = userName;
			gameFanResult._score = score;
			gameFanResult._guaFengXiaYu = guaFengXiaYu;
			return gameFanResult;
		}
	
		protected var _queTou:int;
		protected var _shunZi:Array;
		protected var _keZi:Array;
		protected var _shouPai:Array;
		/**
	 	 * 会儿变化
	 	 */
		protected var _huiErBian:Array;
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
		protected var _baseFanType:String;
		protected var _fanString:String;
		protected var _fan:int;
		protected var _userName:String;
		/**
	 	 * 改变值
	 	 */
		protected var _score:int;
		protected var _guaFengXiaYu:int;
		
		public function GameFanResult()
		{
		}
			
		public function decode(in0:Input):void
		{
			_queTou = in0.readInt();
			_shunZi = in0.readIntArray();
			_keZi = in0.readIntArray();
			_shouPai = in0.readIntArray();
			_huiErBian = in0.readIntArray();
			_anGang = in0.readIntArray();
			_xiaoMingGang = in0.readIntArray();
			_daMingGang = in0.readIntArray();
			_peng = in0.readIntArray();
			_chi = in0.readIntArray();
			_baseFanType = in0.readString();
			_fanString = in0.readString();
			_fan = in0.readInt();
			_userName = in0.readString();
			_score = in0.readInt();
			_guaFengXiaYu = in0.readInt();
		}
		
		public function encode(out:Output):void
		{
			out.writeInt(queTou);
			out.writeIntArray(shunZi);
			out.writeIntArray(keZi);
			out.writeIntArray(shouPai);
			out.writeIntArray(huiErBian);
			out.writeIntArray(anGang);
			out.writeIntArray(xiaoMingGang);
			out.writeIntArray(daMingGang);
			out.writeIntArray(peng);
			out.writeIntArray(chi);
			out.writeString(baseFanType);
			out.writeString(fanString);
			out.writeInt(fan);
			out.writeString(userName);
			out.writeInt(score);
			out.writeInt(guaFengXiaYu);
		}
		

		public function get queTou():int
		{
			return _queTou;
		}
		public function set queTou(queTou:int):void
		{
			_queTou = queTou;
		}

		public function get shunZi():Array
		{
			return _shunZi;
		}
		public function set shunZi(shunZi:Array):void
		{
			_shunZi = shunZi;
		}

		public function get keZi():Array
		{
			return _keZi;
		}
		public function set keZi(keZi:Array):void
		{
			_keZi = keZi;
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
		 * 会儿变化
		 */
		public function get huiErBian():Array
		{
			return _huiErBian;
		}
		/**
		 * 会儿变化
		 */
		public function set huiErBian(huiErBian:Array):void
		{
			_huiErBian = huiErBian;
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

		public function get baseFanType():String
		{
			return _baseFanType;
		}
		public function set baseFanType(baseFanType:String):void
		{
			_baseFanType = baseFanType;
		}

		public function get fanString():String
		{
			return _fanString;
		}
		public function set fanString(fanString:String):void
		{
			_fanString = fanString;
		}

		public function get fan():int
		{
			return _fan;
		}
		public function set fan(fan:int):void
		{
			_fan = fan;
		}

		public function get userName():String
		{
			return _userName;
		}
		public function set userName(userName:String):void
		{
			_userName = userName;
		}

		/**
		 * 改变值
		 */
		public function get score():int
		{
			return _score;
		}
		/**
		 * 改变值
		 */
		public function set score(score:int):void
		{
			_score = score;
		}

		public function get guaFengXiaYu():int
		{
			return _guaFengXiaYu;
		}
		public function set guaFengXiaYu(guaFengXiaYu:int):void
		{
			_guaFengXiaYu = guaFengXiaYu;
		}
		
		public function toString():String
		{
			return "GameFanResult [queTou=" + _queTou + ",shunZi=" + _shunZi + ",keZi=" + _keZi + ",shouPai=" + _shouPai + ",huiErBian=" + _huiErBian + ",anGang=" + _anGang + ",xiaoMingGang=" + _xiaoMingGang + ",daMingGang=" + _daMingGang + ",peng=" + _peng + ",chi=" + _chi + ",baseFanType=" + _baseFanType + ",fanString=" + _fanString + ",fan=" + _fan + ",userName=" + _userName + ",score=" + _score + ",guaFengXiaYu=" + _guaFengXiaYu + ", ]";
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