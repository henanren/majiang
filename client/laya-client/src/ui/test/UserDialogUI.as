/**Created by the LayaAirIDE,do not modify.*/
package ui.test {
	import laya.ui.*;
	import laya.display.*;
	import laya.display.Text;

	public class UserDialogUI extends View {
		public var mainSprite:Sprite;
		public var userHead:Sprite;
		public var userHeadMask:Image;
		public var userHeadIconOut:Sprite;
		public var userHeadIcon:Image;
		public var userId:Text;
		public var userName:Text;
		public var ip:Text;
		public var userInfo0:Box;
		public var userInfo1:Box;
		public var userInfo2:Box;

		public static var uiView:Object ={"type":"View","props":{"y":0,"x":0,"width":731,"text":"距你：10公里","label":"下一步","height":650},"child":[{"type":"Sprite","props":{"y":0,"x":0,"width":731,"var":"mainSprite","height":674},"child":[{"type":"Sprite","props":{"y":66,"x":154.5,"width":422,"var":"userHead","height":168},"child":[{"type":"Sprite","props":{"y":-30,"x":-20,"scaleY":0.4,"scaleX":0.4},"child":[{"type":"Image","props":{"y":2,"x":-2,"width":133,"skin":"ui/game/user_head_bg.png","scaleY":4,"scaleX":4,"height":133,"sizeGrid":"60,60,60,60"},"child":[{"type":"Image","props":{"y":0,"x":0,"width":133,"var":"userHeadMask","skin":"ui/game/user_head_mask.png","height":133,"sizeGrid":"60,60,60,60"}},{"type":"Sprite","props":{"y":0,"x":0,"width":133,"var":"userHeadIconOut","height":133},"child":[{"type":"Image","props":{"y":4,"x":7,"width":119,"var":"userHeadIcon","skin":"ui/game/user_head_default.png","pivotY":0,"pivotX":0,"height":115}}]}]},{"type":"Sprite","props":{"y":38,"x":550,"scaleY":3,"scaleX":3},"child":[{"type":"Text","props":{"y":65.15384615384613,"width":208,"var":"userId","text":"ID:7777777","height":35,"fontSize":30,"color":"#573a1e","align":"center"}},{"type":"Text","props":{"y":1.1538461538460965,"width":208,"var":"userName","text":"用户名称","height":46,"fontSize":40,"color":"#573a1e","align":"center"}},{"type":"Text","props":{"y":117.15384615384616,"width":208,"var":"ip","text":"IP:192.168.3.2","height":35,"fontSize":30,"color":"#573a1e","align":"center"}}]}]}]},{"type":"Box","props":{"y":289,"x":54,"width":623,"var":"userInfo0","height":120},"child":[{"type":"Image","props":{"y":0,"x":618,"width":3,"skin":"base/line_vertical.png","rotation":90,"height":620}},{"type":"Text","props":{"y":31.5,"x":6,"width":246,"text":"小草：","name":"name","height":57,"fontSize":"50","color":"#000000","align":"left"}},{"type":"Text","props":{"y":62,"x":214,"width":433,"text":"距你：10公里","name":"distance","fontSize":"42","color":"#cd5216","align":"center"}},{"type":"Text","props":{"y":11,"x":216,"width":433,"text":"IP:192.168.3.2","name":"ip","fontSize":"42","color":"#cd5216","align":"center"}}]},{"type":"Box","props":{"y":415,"x":54,"width":623,"var":"userInfo1","height":120},"child":[{"type":"Image","props":{"y":0,"x":618,"width":3,"skin":"base/line_vertical.png","rotation":90,"height":620}},{"type":"Text","props":{"y":31.5,"x":6,"width":246,"text":"小草：","name":"name","height":57,"fontSize":"50","color":"#000000","align":"left"}},{"type":"Text","props":{"y":62,"x":214,"width":433,"text":"距你：10公里","name":"distance","fontSize":"42","color":"#cd5216","align":"center"}},{"type":"Text","props":{"y":11,"x":216,"width":433,"text":"IP:192.168.3.2","name":"ip","fontSize":"42","color":"#cd5216","align":"center"}}]},{"type":"Box","props":{"y":541,"x":54,"width":623,"var":"userInfo2","height":120},"child":[{"type":"Image","props":{"y":0,"x":618,"width":3,"skin":"base/line_vertical.png","rotation":90,"height":620}},{"type":"Text","props":{"y":31.5,"x":6,"width":246,"text":"小草：","name":"name","height":57,"fontSize":"50","color":"#000000","align":"left"}},{"type":"Text","props":{"y":62,"x":214,"width":433,"text":"距你：10公里","name":"distance","fontSize":"42","color":"#cd5216","align":"center"}},{"type":"Text","props":{"y":11,"x":216,"width":433,"text":"IP:192.168.3.2","name":"ip","fontSize":"42","color":"#cd5216","align":"center"}}]}]}]};
		override protected function createChildren():void {
			View.regComponent("Text",Text);
			super.createChildren();
			createView(uiView);
		}
	}
}