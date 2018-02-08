/**Created by the LayaAirIDE,do not modify.*/
package ui.test {
	import laya.ui.*;
	import laya.display.*;
	import laya.display.Text;

	public class ChapterResultUI extends View {
		public var mainSprite:Sprite;
		public var shouPai3:Sprite;
		public var chi3:Sprite;
		public var anGangHide3:Sprite;
		public var mingGang3:Sprite;
		public var anGang3:Sprite;
		public var shou3:Sprite;
		public var fa3:Sprite;
		public var winSprite:Sprite;
		public var winScore:Label;
		public var winName:Label;
		public var winFan:Label;
		public var winFanString:Text;
		public var zaMaSprite:Sprite;
		public var maPaiSprite:Sprite;
		public var maPai:Image;
		public var yiMaType:Label;
		public var yiMaScore:Label;
		public var yiMaWu:Label;
		public var otherScore:Sprite;
		public var name0:Label;
		public var score0:Label;
		public var name1:Label;
		public var score1:Label;
		public var name2:Label;
		public var score2:Label;
		public var liuju:Label;
		public var title:Image;
		public var titleIcon:Image;

		public static var uiView:Object ={"type":"View","props":{"width":1285,"text":"-100","height":774},"child":[{"type":"Sprite","props":{"y":0,"x":0,"width":1285,"var":"mainSprite","height":774},"child":[{"type":"Sprite","props":{"y":24.999999999999996,"x":27.000000000000167,"width":1227,"var":"shouPai3","scaleY":1,"scaleX":1,"height":172},"child":[{"type":"Sprite","props":{"y":26,"x":0,"width":250,"var":"chi3","height":128},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_bg.png"},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_19.png"}}]},{"type":"Image","props":{"y":0,"x":84,"skin":"ui/majiang/zheng_bg.png"},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_32.png"}}]},{"type":"Image","props":{"y":0,"x":168,"skin":"ui/majiang/zheng_bg.png"},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_19.png"}}]}]},{"type":"Sprite","props":{"y":26,"x":508,"width":250,"var":"anGangHide3","height":128},"child":[{"type":"Image","props":{"y":0,"x":84,"skin":"ui/majiang/zheng_an.png"}},{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_an.png"}},{"type":"Image","props":{"y":-28,"x":84,"skin":"ui/majiang/zheng_an.png"}},{"type":"Image","props":{"y":0,"x":168,"skin":"ui/majiang/zheng_an.png"}}]},{"type":"Sprite","props":{"y":26,"x":760,"width":248,"var":"mingGang3","height":128},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_bg.png"},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_19.png"}}]},{"type":"Image","props":{"y":0,"x":83,"skin":"ui/majiang/zheng_bg.png"},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_19.png"}}]},{"type":"Image","props":{"y":0,"x":166,"skin":"ui/majiang/zheng_bg.png"},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_19.png"}}]},{"type":"Image","props":{"y":-31,"x":83,"skin":"ui/majiang/zheng_bg.png"},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_19.png"}}]}]},{"type":"Sprite","props":{"y":26,"x":254,"width":250,"var":"anGang3","height":128},"child":[{"type":"Image","props":{"y":0,"x":84,"skin":"ui/majiang/zheng_an.png"}},{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_an.png"}},{"type":"Image","props":{"y":0,"x":168,"skin":"ui/majiang/zheng_an.png"}},{"type":"Image","props":{"y":-28,"x":84,"skin":"ui/majiang/zheng_bg.png"},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_19.png"}}]}]},{"type":"Sprite","props":{"y":0,"x":1031,"width":82,"var":"shou3","pivotY":-26,"height":128},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/shou_bg.png"},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/shou_32.png"}}]}]},{"type":"Sprite","props":{"y":0,"x":1144,"width":82,"var":"fa3","pivotY":-26,"height":128},"child":[{"type":"Image","props":{"y":0,"x":0,"width":82,"skin":"ui/majiang/shou_bg.png","height":128},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/shou_0.png"}}]}]}]},{"type":"Sprite","props":{"y":196,"x":29,"width":1230,"var":"winSprite","height":286},"child":[{"type":"Label","props":{"y":45,"x":1056,"width":174,"var":"winScore","text":"+100","overflow":"hidden","height":54,"fontSize":50,"color":"#cd5216","bold":true,"align":"right"}},{"type":"Label","props":{"y":44.5,"x":29,"width":300,"var":"winName","text":"小草小草小草小草小草","overflow":"hidden","height":54,"fontSize":50,"color":"#cd5216","bold":true}},{"type":"Sprite","props":{"y":28,"x":396,"width":585,"height":95},"child":[{"type":"Label","props":{"y":47,"x":-52,"width":749,"var":"winFan","text":"(40番)","overflow":"hidden","height":37,"fontSize":30,"color":"#cd5216","bold":true,"align":"center"}},{"type":"Text","props":{"y":-3,"x":-52,"wordWrap":false,"width":749,"var":"winFanString","text":"杠上花  清一色 一条龙","height":39,"fontSize":"40","color":"#cd5216","bold":true,"align":"center"}}]},{"type":"Sprite","props":{"y":160,"x":-1,"width":1242,"var":"zaMaSprite","height":126},"child":[{"type":"Sprite","props":{"y":-1,"x":355,"var":"maPaiSprite","height":119},"child":[{"type":"Image","props":{"y":-1.1368683772161603e-13,"x":5.684341886080802e-14,"width":82,"var":"maPai","skin":"ui/majiang/zheng_bg.png","height":128},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_19.png"}}]}]},{"type":"Label","props":{"y":28,"x":-1,"width":300,"var":"yiMaType","text":"一码全中","overflow":"hidden","height":54,"fontSize":50,"color":"#573a1e","bold":true,"align":"left"}},{"type":"Label","props":{"y":31,"x":1054,"width":174,"var":"yiMaScore","text":"+100","overflow":"hidden","height":54,"fontSize":50,"color":"#573a1e","bold":true,"align":"right"}},{"type":"Label","props":{"y":33,"x":377,"width":629,"var":"yiMaWu","text":"全部未中","overflow":"hidden","height":50,"fontSize":50,"color":"#573a1e","bold":true,"align":"center"}}]}]},{"type":"Sprite","props":{"y":536.9999999999998,"x":28.000000000000252,"var":"otherScore"},"child":[{"type":"Sprite","props":{"y":1.1368683772161603e-13,"x":0,"width":1230.9999999999998,"height":54},"child":[{"type":"Label","props":{"y":0,"x":31,"width":349,"var":"name0","text":"小草小草小草小草小草","overflow":"hidden","height":54,"fontSize":50,"color":"#573a1e","bold":true}},{"type":"Label","props":{"y":0,"x":905,"width":325,"var":"score0","text":"-100","overflow":"hidden","height":54,"fontSize":50,"color":"#573a1e","bold":true,"align":"right"}}]},{"type":"Sprite","props":{"y":76.00000000000023,"x":0,"width":1230.9999999999998,"height":54},"child":[{"type":"Label","props":{"y":0,"x":31,"width":349,"var":"name1","text":"小草小草小草小草小草","overflow":"hidden","height":54,"fontSize":50,"color":"#573a1e","bold":true}},{"type":"Label","props":{"y":0,"x":902,"width":328,"var":"score1","text":"-100","overflow":"hidden","height":54,"fontSize":50,"color":"#573a1e","bold":true,"align":"right"}}]},{"type":"Sprite","props":{"y":152.0000000000001,"x":0,"width":1230.9999999999998,"height":54},"child":[{"type":"Label","props":{"y":0,"x":31,"width":349,"var":"name2","text":"小草小草小草小草小草","overflow":"hidden","height":54,"fontSize":50,"color":"#573a1e","bold":true}},{"type":"Label","props":{"y":0,"x":906,"width":325,"var":"score2","text":"-100","overflow":"hidden","height":54,"fontSize":50,"color":"#573a1e","bold":true,"align":"right"}}]}]},{"type":"Label","props":{"y":188,"x":60,"width":1159,"var":"liuju","text":"流局","overflow":"hidden","height":119,"fontSize":120,"color":"#1c1c1c","bold":true,"align":"center"},"child":[{"type":"Label","props":{"y":-4,"x":-2,"width":1159,"text":"流局","overflow":"hidden","height":119,"fontSize":120,"color":"#cd5216","bold":true,"align":"center"}}]}]},{"type":"Image","props":{"y":837,"x":1,"var":"title","skin":"ui/game/result_title_bg.png"},"child":[{"type":"Image","props":{"y":-12,"x":107.5,"var":"titleIcon","skin":"ui/game/result_title_win.png"}}]}]};
		override protected function createChildren():void {
			View.regComponent("Text",Text);
			super.createChildren();
			createView(uiView);
		}
	}
}