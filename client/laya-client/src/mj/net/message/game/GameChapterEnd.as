package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	
	import mj.net.message.game.GameFanResult;

	import laya.utils.*;
	
	/**
 	 * 牌局结束
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class GameChapterEnd implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=0;
		
		public static function create(huPaiIndex:int, fangPaoIndex:int, zaMaType:int, zaMaPai:Array, zaMaFan:int, fanResults:Array):GameChapterEnd
		{
			const gameChapterEnd:GameChapterEnd = new GameChapterEnd();
			gameChapterEnd._huPaiIndex = huPaiIndex;
			gameChapterEnd._fangPaoIndex = fangPaoIndex;
			gameChapterEnd._zaMaType = zaMaType;
			gameChapterEnd._zaMaPai = zaMaPai;
			gameChapterEnd._zaMaFan = zaMaFan;
			gameChapterEnd._fanResults = fanResults;
			return gameChapterEnd;
		}
	
		protected var _huPaiIndex:int;
		protected var _fangPaoIndex:int;
		protected var _zaMaType:int;
		protected var _zaMaPai:Array;
		protected var _zaMaFan:int;
		protected var _fanResults:Array;
		
		public function GameChapterEnd()
		{
		}
			
		public function decode(in0:Input):void
		{
			_huPaiIndex = in0.readInt();
			_fangPaoIndex = in0.readInt();
			_zaMaType = in0.readInt();
			_zaMaPai = in0.readIntArray();
			_zaMaFan = in0.readInt();
		
			var fanResultsLen:int = in0.readInt();
			if(fanResultsLen == -1)
			{
				_fanResults = null;
			}
			else
			{
				_fanResults = new Array();
				for(var fanResultsI:int = 0; fanResultsI < fanResultsLen; fanResultsI++)
				{
					var fanResultsItem:GameFanResult = new GameFanResult();
					fanResultsItem.decode(in0);
					_fanResults[fanResultsI] = (fanResultsItem);
				}
			}
		}
		
		public function encode(out:Output):void
		{
			out.writeInt(huPaiIndex);
			out.writeInt(fangPaoIndex);
			out.writeInt(zaMaType);
			out.writeIntArray(zaMaPai);
			out.writeInt(zaMaFan);
		
			if(fanResults == null){
				out.writeInt(-1);
			}else{
				var fanResultsLen:int = fanResults.length;
				out.writeInt(fanResultsLen);
				for(var fanResultsI:int = 0; fanResultsI < fanResultsLen; fanResultsI++)
				{
					fanResults[fanResultsI].encode(out);
				}
			}
		}
		

		public function get huPaiIndex():int
		{
			return _huPaiIndex;
		}
		public function set huPaiIndex(huPaiIndex:int):void
		{
			_huPaiIndex = huPaiIndex;
		}

		public function get fangPaoIndex():int
		{
			return _fangPaoIndex;
		}
		public function set fangPaoIndex(fangPaoIndex:int):void
		{
			_fangPaoIndex = fangPaoIndex;
		}

		public function get zaMaType():int
		{
			return _zaMaType;
		}
		public function set zaMaType(zaMaType:int):void
		{
			_zaMaType = zaMaType;
		}

		public function get zaMaPai():Array
		{
			return _zaMaPai;
		}
		public function set zaMaPai(zaMaPai:Array):void
		{
			_zaMaPai = zaMaPai;
		}

		public function get zaMaFan():int
		{
			return _zaMaFan;
		}
		public function set zaMaFan(zaMaFan:int):void
		{
			_zaMaFan = zaMaFan;
		}

		public function get fanResults():Array
		{
			return _fanResults;
		}
		public function set fanResults(fanResults:Array):void
		{
			_fanResults = fanResults;
		}
		
		public function toString():String
		{
			return "GameChapterEnd [huPaiIndex=" + _huPaiIndex + ",fangPaoIndex=" + _fangPaoIndex + ",zaMaType=" + _zaMaType + ",zaMaPai=" + _zaMaPai + ",zaMaFan=" + _zaMaFan + ",fanResults=" + _fanResults + ", ]";
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