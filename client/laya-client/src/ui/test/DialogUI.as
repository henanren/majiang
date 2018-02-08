/**Created by the LayaAirIDE,do not modify.*/
package ui.test {
	import laya.ui.*;
	import laya.display.*;
	import laya.display.Text;

	public class DialogUI extends Dialog {
		public var mainSprite:Sprite;
		public var bg:Image;
		public var icon1:Image;
		public var icon2:Image;
		public var title:Label;
		public var closeBtn:Button;
		public var contentSprite:Sprite;
		public var contentText:Text;
		public var buttonSprite:Sprite;
		public var cancelBtn:Button;
		public var confirmBtn:Button;

		public static var uiView:Object ={"type":"Dialog","props":{"width":702,"text":"提示信息111","labelPadding":"0,0,5,0","labelColors":"#FFFFFF,#FFFFFF,#FFFFFF","labelBold":true,"label":"确定","height":543},"child":[{"type":"Sprite","props":{"y":0,"x":0,"width":702,"var":"mainSprite","height":543},"child":[{"type":"Image","props":{"y":0,"x":0,"width":702,"var":"bg","skin":"base/dialog_bg.png","height":543,"sizeGrid":"90,37,44,35"}},{"type":"Image","props":{"y":27,"x":96.00000000000004,"var":"icon1","skin":"base/dialog_icon.png","mouseEnabled":true}},{"type":"Image","props":{"y":27,"x":554,"var":"icon2","skin":"base/dialog_icon.png","scaleX":-1,"mouseEnabled":true}},{"type":"Label","props":{"y":20,"x":91,"width":509,"var":"title","text":"标题","overflow":"hidden","mouseEnabled":true,"height":62,"fontSize":48,"color":"#ffffff","align":"center"}},{"type":"Button","props":{"y":-6,"x":610,"var":"closeBtn","skin":"base/btn_close.png"}},{"type":"Sprite","props":{"y":96,"x":31,"width":"640","var":"contentSprite","height":307},"child":[{"type":"Text","props":{"y":15,"x":19,"width":599,"var":"contentText","text":"提示信息","height":272,"fontSize":"48","align":"left"}}]},{"type":"Sprite","props":{"y":417,"x":0,"width":702,"var":"buttonSprite","height":89},"child":[{"type":"Button","props":{"y":0,"x":27,"width":300,"var":"cancelBtn","skin":"base/btn_normal.png","labelSize":40,"labelPadding":"0,0,6,0","labelColors":"#FFFFFF,#FFFFFF,#FFFFFF","labelBold":"true","label":"取消","height":95,"sizeGrid":"31,71,40,71"}},{"type":"Button","props":{"y":0,"x":373,"width":300,"var":"confirmBtn","skin":"base/btn_danger.png","labelSize":40,"labelPadding":"0,0,6,0","labelColors":"#FFFFFF,#FFFFFF,#FFFFFF","labelBold":"true","label":"确定","height":95,"sizeGrid":"31,71,40,71"}}]}]}]};
		override protected function createChildren():void {
			View.regComponent("Text",Text);
			super.createChildren();
			createView(uiView);
		}
	}
}