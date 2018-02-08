/**Created by the LayaAirIDE,do not modify.*/
package ui.test {
	import laya.ui.*;
	import laya.display.*;

	public class OptDialogUI extends Dialog {
		public var hu:Image;
		public var peng:Image;
		public var guo:Image;
		public var gang:Image;
		public var chi:Image;

		public static var uiView:Object ={"type":"Dialog","props":{"width":1920,"height":1080},"child":[{"type":"Image","props":{"y":0,"x":0,"var":"hu","skin":"ui/game/operat_hu.png"}},{"type":"Image","props":{"y":0,"x":560,"var":"peng","skin":"ui/game/operat_peng.png"},"child":[{"type":"Image","props":{"y":28,"x":162,"skin":"ui/majiang/zheng_bg.png"},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_19.png"}}]}]},{"type":"Image","props":{"y":0,"x":1188,"var":"guo","skin":"ui/game/operat_guo.png"}},{"type":"Image","props":{"y":0,"x":273,"var":"gang","skin":"ui/game/operat_gang.png"},"child":[{"type":"Image","props":{"y":28,"x":157,"skin":"ui/majiang/zheng_bg.png"},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_19.png"}}]}]},{"type":"Image","props":{"y":0,"x":881,"var":"chi","skin":"ui/game/operat_chi.png"},"child":[{"type":"Image","props":{"y":66,"x":154,"skin":"ui/majiang/zheng_bg.png","scaleY":0.5,"scaleX":0.5},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_19.png"}}]},{"type":"Image","props":{"y":66,"x":199,"skin":"ui/majiang/zheng_bg.png","scaleY":0.5,"scaleX":0.5},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_19.png"}}]},{"type":"Image","props":{"y":66,"x":243,"skin":"ui/majiang/zheng_bg.png","scaleY":0.5,"scaleX":0.5},"child":[{"type":"Image","props":{"y":0,"x":0,"skin":"ui/majiang/zheng_19.png"}}]}]}]};
		override protected function createChildren():void {
			super.createChildren();
			createView(uiView);
		}
	}
}