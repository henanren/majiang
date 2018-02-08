package mj.net.message.game
{
	import com.isnowfox.core.io.Input;
	import com.isnowfox.core.io.Output;
	import com.isnowfox.core.io.message.Message;
	
	import mj.net.message.game.UserPlaceMsg;
	import mj.net.message.game.OperationCPGH;
	import mj.net.message.game.OperationFaPai;
	import mj.net.message.game.OperationOut;
	import mj.net.message.game.SyncOptTime;
	import mj.net.message.game.GameChapterEnd;
	import mj.net.message.game.TingPai;

	import laya.utils.*;
	
	/**
 	 * 一局麻将的信息
 	 * 
 	 * <b>生成器生成代码，请勿修改，扩展请继承</b>
 	 * @author isnowfox消息生成器
 	 */
	public class MajiangChapterMsg implements Message
	{
		public static const TYPE:int				=1;
		public static const ID:int					=9;
		
		public static function create(freeLength:int, baoliuLength:int, huiEr:Array, bianType:String, bianSource:int, userPlace:Array, currentIndex:int, chapterNums:int, chapterNumsMax:int, quanIndex:int, zhuangIndex:int, optCpgh:mj.net.message.game.OperationCPGH, optFaPai:mj.net.message.game.OperationFaPai, optOut:mj.net.message.game.OperationOut, syncOptTime:mj.net.message.game.SyncOptTime, gameChapterEnd:mj.net.message.game.GameChapterEnd, tingPai:mj.net.message.game.TingPai):MajiangChapterMsg
		{
			const majiangChapterMsg:MajiangChapterMsg = new MajiangChapterMsg();
			majiangChapterMsg._freeLength = freeLength;
			majiangChapterMsg._baoliuLength = baoliuLength;
			majiangChapterMsg._huiEr = huiEr;
			majiangChapterMsg._bianType = bianType;
			majiangChapterMsg._bianSource = bianSource;
			majiangChapterMsg._userPlace = userPlace;
			majiangChapterMsg._currentIndex = currentIndex;
			majiangChapterMsg._chapterNums = chapterNums;
			majiangChapterMsg._chapterNumsMax = chapterNumsMax;
			majiangChapterMsg._quanIndex = quanIndex;
			majiangChapterMsg._zhuangIndex = zhuangIndex;
			majiangChapterMsg._optCpgh = optCpgh;
			majiangChapterMsg._optFaPai = optFaPai;
			majiangChapterMsg._optOut = optOut;
			majiangChapterMsg._syncOptTime = syncOptTime;
			majiangChapterMsg._gameChapterEnd = gameChapterEnd;
			majiangChapterMsg._tingPai = tingPai;
			return majiangChapterMsg;
		}
	
		/**
	 	 * 剩余张数
	 	 */
		protected var _freeLength:int;
		/**
	 	 * 保留张数
	 	 */
		protected var _baoliuLength:int;
		/**
	 	 * 会儿牌
	 	 */
		protected var _huiEr:Array;
		/**
	 	 * 变化类型！！
	 	 */
		protected var _bianType:String;
		/**
	 	 * 变化类型！！
	 	 */
		protected var _bianSource:int;
		protected var _userPlace:Array;
		/**
	 	 * 当前操作用户
	 	 */
		protected var _currentIndex:int;
		/**
	 	 * 局数, 0开始
	 	 */
		protected var _chapterNums:int;
		/**
	 	 * 局数, 0开始
	 	 */
		protected var _chapterNumsMax:int;
		/**
	 	 * 圈index 0 东 1南 2西 3北 逆时针顺序
	 	 */
		protected var _quanIndex:int;
		/**
	 	 * 庄index 0 东 1南 2西 3北 逆时针顺序
	 	 */
		protected var _zhuangIndex:int;
		protected var _optCpgh:mj.net.message.game.OperationCPGH;
		protected var _optFaPai:mj.net.message.game.OperationFaPai;
		protected var _optOut:mj.net.message.game.OperationOut;
		protected var _syncOptTime:mj.net.message.game.SyncOptTime;
		protected var _gameChapterEnd:mj.net.message.game.GameChapterEnd;
		protected var _tingPai:mj.net.message.game.TingPai;
		
		public function MajiangChapterMsg()
		{
		}
			
		public function decode(in0:Input):void
		{
			_freeLength = in0.readInt();
			_baoliuLength = in0.readInt();
			_huiEr = in0.readIntArray();
			_bianType = in0.readString();
			_bianSource = in0.readInt();
		
			var userPlaceLen:int = in0.readInt();
			if(userPlaceLen == -1)
			{
				_userPlace = null;
			}
			else
			{
				_userPlace = new Array();
				for(var userPlaceI:int = 0; userPlaceI < userPlaceLen; userPlaceI++)
				{
					var userPlaceItem:UserPlaceMsg = new UserPlaceMsg();
					userPlaceItem.decode(in0);
					_userPlace[userPlaceI] = (userPlaceItem);
				}
			}
			_currentIndex = in0.readInt();
			_chapterNums = in0.readInt();
			_chapterNumsMax = in0.readInt();
			_quanIndex = in0.readInt();
			_zhuangIndex = in0.readInt();
		
			var optCpghIsNotNull:Boolean = in0.readBoolean();
			if(optCpghIsNotNull)
			{
				_optCpgh = new mj.net.message.game.OperationCPGH();
				_optCpgh.decode(in0);
			}
			else
			{
				_optCpgh = null;
			}
		
			var optFaPaiIsNotNull:Boolean = in0.readBoolean();
			if(optFaPaiIsNotNull)
			{
				_optFaPai = new mj.net.message.game.OperationFaPai();
				_optFaPai.decode(in0);
			}
			else
			{
				_optFaPai = null;
			}
		
			var optOutIsNotNull:Boolean = in0.readBoolean();
			if(optOutIsNotNull)
			{
				_optOut = new mj.net.message.game.OperationOut();
				_optOut.decode(in0);
			}
			else
			{
				_optOut = null;
			}
		
			var syncOptTimeIsNotNull:Boolean = in0.readBoolean();
			if(syncOptTimeIsNotNull)
			{
				_syncOptTime = new mj.net.message.game.SyncOptTime();
				_syncOptTime.decode(in0);
			}
			else
			{
				_syncOptTime = null;
			}
		
			var gameChapterEndIsNotNull:Boolean = in0.readBoolean();
			if(gameChapterEndIsNotNull)
			{
				_gameChapterEnd = new mj.net.message.game.GameChapterEnd();
				_gameChapterEnd.decode(in0);
			}
			else
			{
				_gameChapterEnd = null;
			}
		
			var tingPaiIsNotNull:Boolean = in0.readBoolean();
			if(tingPaiIsNotNull)
			{
				_tingPai = new mj.net.message.game.TingPai();
				_tingPai.decode(in0);
			}
			else
			{
				_tingPai = null;
			}
		}
		
		public function encode(out:Output):void
		{
			out.writeInt(freeLength);
			out.writeInt(baoliuLength);
			out.writeIntArray(huiEr);
			out.writeString(bianType);
			out.writeInt(bianSource);
		
			if(userPlace == null){
				out.writeInt(-1);
			}else{
				var userPlaceLen:int = userPlace.length;
				out.writeInt(userPlaceLen);
				for(var userPlaceI:int = 0; userPlaceI < userPlaceLen; userPlaceI++)
				{
					userPlace[userPlaceI].encode(out);
				}
			}
			out.writeInt(currentIndex);
			out.writeInt(chapterNums);
			out.writeInt(chapterNumsMax);
			out.writeInt(quanIndex);
			out.writeInt(zhuangIndex);
		
			if(optCpgh == null){
				out.writeBoolean(false);
			}else{
				out.writeBoolean(true);
				optCpgh.encode(out);
			}
		
			if(optFaPai == null){
				out.writeBoolean(false);
			}else{
				out.writeBoolean(true);
				optFaPai.encode(out);
			}
		
			if(optOut == null){
				out.writeBoolean(false);
			}else{
				out.writeBoolean(true);
				optOut.encode(out);
			}
		
			if(syncOptTime == null){
				out.writeBoolean(false);
			}else{
				out.writeBoolean(true);
				syncOptTime.encode(out);
			}
		
			if(gameChapterEnd == null){
				out.writeBoolean(false);
			}else{
				out.writeBoolean(true);
				gameChapterEnd.encode(out);
			}
		
			if(tingPai == null){
				out.writeBoolean(false);
			}else{
				out.writeBoolean(true);
				tingPai.encode(out);
			}
		}
		

		/**
		 * 剩余张数
		 */
		public function get freeLength():int
		{
			return _freeLength;
		}
		/**
		 * 剩余张数
		 */
		public function set freeLength(freeLength:int):void
		{
			_freeLength = freeLength;
		}

		/**
		 * 保留张数
		 */
		public function get baoliuLength():int
		{
			return _baoliuLength;
		}
		/**
		 * 保留张数
		 */
		public function set baoliuLength(baoliuLength:int):void
		{
			_baoliuLength = baoliuLength;
		}

		/**
		 * 会儿牌
		 */
		public function get huiEr():Array
		{
			return _huiEr;
		}
		/**
		 * 会儿牌
		 */
		public function set huiEr(huiEr:Array):void
		{
			_huiEr = huiEr;
		}

		/**
		 * 变化类型！！
		 */
		public function get bianType():String
		{
			return _bianType;
		}
		/**
		 * 变化类型！！
		 */
		public function set bianType(bianType:String):void
		{
			_bianType = bianType;
		}

		/**
		 * 变化类型！！
		 */
		public function get bianSource():int
		{
			return _bianSource;
		}
		/**
		 * 变化类型！！
		 */
		public function set bianSource(bianSource:int):void
		{
			_bianSource = bianSource;
		}

		public function get userPlace():Array
		{
			return _userPlace;
		}
		public function set userPlace(userPlace:Array):void
		{
			_userPlace = userPlace;
		}

		/**
		 * 当前操作用户
		 */
		public function get currentIndex():int
		{
			return _currentIndex;
		}
		/**
		 * 当前操作用户
		 */
		public function set currentIndex(currentIndex:int):void
		{
			_currentIndex = currentIndex;
		}

		/**
		 * 局数, 0开始
		 */
		public function get chapterNums():int
		{
			return _chapterNums;
		}
		/**
		 * 局数, 0开始
		 */
		public function set chapterNums(chapterNums:int):void
		{
			_chapterNums = chapterNums;
		}

		/**
		 * 局数, 0开始
		 */
		public function get chapterNumsMax():int
		{
			return _chapterNumsMax;
		}
		/**
		 * 局数, 0开始
		 */
		public function set chapterNumsMax(chapterNumsMax:int):void
		{
			_chapterNumsMax = chapterNumsMax;
		}

		/**
		 * 圈index 0 东 1南 2西 3北 逆时针顺序
		 */
		public function get quanIndex():int
		{
			return _quanIndex;
		}
		/**
		 * 圈index 0 东 1南 2西 3北 逆时针顺序
		 */
		public function set quanIndex(quanIndex:int):void
		{
			_quanIndex = quanIndex;
		}

		/**
		 * 庄index 0 东 1南 2西 3北 逆时针顺序
		 */
		public function get zhuangIndex():int
		{
			return _zhuangIndex;
		}
		/**
		 * 庄index 0 东 1南 2西 3北 逆时针顺序
		 */
		public function set zhuangIndex(zhuangIndex:int):void
		{
			_zhuangIndex = zhuangIndex;
		}

		public function get optCpgh():mj.net.message.game.OperationCPGH
		{
			return _optCpgh;
		}
		public function set optCpgh(optCpgh:mj.net.message.game.OperationCPGH):void
		{
			_optCpgh = optCpgh;
		}

		public function get optFaPai():mj.net.message.game.OperationFaPai
		{
			return _optFaPai;
		}
		public function set optFaPai(optFaPai:mj.net.message.game.OperationFaPai):void
		{
			_optFaPai = optFaPai;
		}

		public function get optOut():mj.net.message.game.OperationOut
		{
			return _optOut;
		}
		public function set optOut(optOut:mj.net.message.game.OperationOut):void
		{
			_optOut = optOut;
		}

		public function get syncOptTime():mj.net.message.game.SyncOptTime
		{
			return _syncOptTime;
		}
		public function set syncOptTime(syncOptTime:mj.net.message.game.SyncOptTime):void
		{
			_syncOptTime = syncOptTime;
		}

		public function get gameChapterEnd():mj.net.message.game.GameChapterEnd
		{
			return _gameChapterEnd;
		}
		public function set gameChapterEnd(gameChapterEnd:mj.net.message.game.GameChapterEnd):void
		{
			_gameChapterEnd = gameChapterEnd;
		}

		public function get tingPai():mj.net.message.game.TingPai
		{
			return _tingPai;
		}
		public function set tingPai(tingPai:mj.net.message.game.TingPai):void
		{
			_tingPai = tingPai;
		}
		
		public function toString():String
		{
			return "MajiangChapterMsg [freeLength=" + _freeLength + ",baoliuLength=" + _baoliuLength + ",huiEr=" + _huiEr + ",bianType=" + _bianType + ",bianSource=" + _bianSource + ",userPlace=" + _userPlace + ",currentIndex=" + _currentIndex + ",chapterNums=" + _chapterNums + ",chapterNumsMax=" + _chapterNumsMax + ",quanIndex=" + _quanIndex + ",zhuangIndex=" + _zhuangIndex + ",optCpgh=" + _optCpgh + ",optFaPai=" + _optFaPai + ",optOut=" + _optOut + ",syncOptTime=" + _syncOptTime + ",gameChapterEnd=" + _gameChapterEnd + ",tingPai=" + _tingPai + ", ]";
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