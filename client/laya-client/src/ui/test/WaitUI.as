/**Created by the LayaAirIDE,do not modify.*/
package ui.test {
	import laya.ui.*;
	import laya.display.*;
	import laya.display.Text;

	public class WaitUI extends Dialog {
		public var main:Image;
		public var text:Text;

		public static var uiView:Object ={"type":"Dialog","props":{"width":314,"height":263},"child":[{"type":"Image","props":{"y":0,"x":0,"width":314,"var":"main","skin":"base/wait_bg.png","height":263,"alpha":1,"sizeGrid":"24,24,24,24"},"child":[{"type":"Text","props":{"y":204,"x":17,"width":281,"var":"text","text":"正在加载","height":33,"fontSize":"30","color":"#ffffff","align":"center"}}]}]};
		override protected function createChildren():void {
			View.regComponent("Text",Text);
			super.createChildren();
			createView(uiView);
		}
	}
}