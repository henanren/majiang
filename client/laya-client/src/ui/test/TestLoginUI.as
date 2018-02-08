/**Created by the LayaAirIDE,do not modify.*/
package ui.test {
	import laya.ui.*;
	import laya.display.*;

	public class TestLoginUI extends View {
		public var input:TextInput;
		public var confirmBtn:Button;

		public static var uiView:Object ={"type":"View","props":{"width":600,"height":400},"child":[{"type":"TextInput","props":{"y":138,"x":116,"width":291,"var":"input","text":"a","height":62,"fontSize":40,"color":"#ffffff","borderColor":"#ffffff"}},{"type":"Button","props":{"y":260,"x":131,"width":300,"var":"confirmBtn","skin":"base/btn_danger.png","labelSize":40,"labelPadding":"0,0,6,0","labelColors":"#FFFFFF,#FFFFFF,#FFFFFF","labelBold":"true","label":"确定","height":95,"sizeGrid":"31,71,40,71"}}]};
		override protected function createChildren():void {
			super.createChildren();
			createView(uiView);
		}
	}
}