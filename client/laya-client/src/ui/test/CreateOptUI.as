/**Created by the LayaAirIDE,do not modify.*/
package ui.test {
	import laya.ui.*;
	import laya.display.*;

	public class CreateOptUI extends View {
		public var content:Sprite;
		public var tabContent:Sprite;
		public var typeRadio:RadioGroup;
		public var chapterMaxRadio:RadioGroup;
		public var maiMaRadio:RadioGroup;

		public static var uiView:Object ={"type":"View","props":{"y":0,"x":0,"width":1520,"labelSize":42,"height":608},"child":[{"type":"Sprite","props":{"y":3,"x":0,"width":1520,"var":"content","height":608},"child":[{"type":"Sprite","props":{"y":23,"x":29,"width":1207,"var":"tabContent","scaleY":1.2,"scaleX":1.2,"height":477},"child":[{"type":"Sprite","props":{"y":14,"x":12,"width":1185,"height":62},"child":[{"type":"Label","props":{"y":5,"x":0,"width":167,"text":"类型:","height":46,"fontSize":44,"color":"#000000"}},{"type":"RadioGroup","props":{"y":-6,"x":177,"width":807,"var":"typeRadio","space":40,"skin":"base/radio.png","selectedIndex":0,"labels":"推倒胡,红中变,白板变,单鬼,双鬼","labelSize":42,"labelPadding":"7,0,0,0","labelColors":"#000000","height":68,"direction":"horizontal"}}]},{"type":"Sprite","props":{"y":128,"x":12},"child":[{"type":"Label","props":{"y":5,"x":0,"width":167,"text":"局数:","height":46,"fontSize":44,"color":"#000000"}},{"type":"RadioGroup","props":{"y":-6,"x":177,"width":643,"var":"chapterMaxRadio","space":40,"skin":"base/radio.png","selectedIndex":0,"labels":"8局(1房卡),16局(2房卡)","labelSize":42,"labelPadding":"7,0,0,0","labelColors":"#000000","height":68,"direction":"horizontal"}}]},{"type":"Sprite","props":{"y":224,"x":12,"width":1087,"height":139},"child":[{"type":"Label","props":{"y":7.000000000000114,"x":0,"width":167,"text":"马牌:","height":46,"fontSize":44,"color":"#000000"}},{"type":"RadioGroup","props":{"y":2,"x":177,"var":"maiMaRadio","selectedIndex":1},"child":[{"type":"Radio","props":{"skin":"base/radio.png","name":"item0","labelSize":42,"labelPadding":"7,0,0,0","labelColors":"#000000","label":"无马"}},{"type":"Radio","props":{"x":167,"skin":"base/radio.png","name":"item1","labelSize":42,"labelPadding":"7,0,0,0","labelColors":"#000000","label":"一马全中"}},{"type":"Radio","props":{"x":420,"skin":"base/radio.png","name":"item2","labelSize":42,"labelPadding":"7,0,0,0","labelColors":"#000000","label":"买2马"}},{"type":"Radio","props":{"x":614,"skin":"base/radio.png","name":"item3","labelSize":42,"labelPadding":"7,0,0,0","labelColors":"#000000","label":"买4马"}},{"type":"Radio","props":{"y":72,"skin":"base/radio.png","name":"item4","labelSize":42,"labelPadding":"7,0,0,0","labelColors":"#000000","label":"买6马"}}]}]}]},{"type":"Image","props":{"y":122,"x":1147,"width":3,"skin":"base/line_vertical.png","rotation":90,"height":1100}}]}]};
		override protected function createChildren():void {
			super.createChildren();
			createView(uiView);
		}
	}
}