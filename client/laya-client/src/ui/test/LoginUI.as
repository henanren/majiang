/**Created by the LayaAirIDE,do not modify.*/
package ui.test {
	import laya.ui.*;
	import laya.display.*;
	import laya.display.Text;

	public class LoginUI extends View {
		public var bg:Sprite;
		public var mainBg:Image;
		public var mainSprite:Sprite;
		public var buttonsSprite:Sprite;
		public var anonymousLoginBtn:Button;
		public var smsLoginBtn:Button;
		public var weixinLoginBtn:Button;
		public var agreeCheck:CheckBox;
		public var agreeLink:Label;

		public static var uiView:Object ={"type":"View","props":{"text":"ID:3213124"},"child":[{"type":"Sprite","props":{"y":0,"x":0,"var":"bg"},"child":[{"type":"Image","props":{"y":0,"x":0,"width":1920,"var":"mainBg","skin":"ui/home/bg.jpg","height":1080}},{"type":"Image","props":{"y":216,"x":161,"width":419,"skin":"ui/home/mm.png","height":865}}]},{"type":"Sprite","props":{"width":1920,"var":"mainSprite","height":1080},"child":[{"type":"Sprite","props":{"y":255.5,"x":688,"width":544,"var":"buttonsSprite","height":569},"child":[{"type":"Button","props":{"y":311.9999999999998,"x":0,"width":272,"var":"anonymousLoginBtn","skin":"base/btn_normal.png","scaleY":2,"scaleX":2,"labelSize":38,"labelPadding":"0,0,6,0","labelColors":"#FFFFFF,#FFFFFF,#FFFFFF","labelBold":false,"label":"匿名登录","height":71,"sizeGrid":"31,71,40,71"}},{"type":"Button","props":{"y":155.99999999999991,"x":0,"width":272,"var":"smsLoginBtn","skin":"base/btn_normal.png","scaleY":2,"scaleX":2,"labelSize":38,"labelPadding":"0,0,6,0","labelColors":"#FFFFFF,#FFFFFF,#FFFFFF","labelBold":false,"label":"短信登录","height":71,"sizeGrid":"31,71,40,71"}},{"type":"Button","props":{"y":0,"x":0,"width":272,"var":"weixinLoginBtn","skin":"base/btn_normal.png","scaleY":2,"scaleX":2,"labelSize":38,"labelPadding":"0,0,6,0","labelColors":"#FFFFFF,#FFFFFF,#FFFFFF","labelBold":false,"label":"微信登录","height":71,"sizeGrid":"31,71,40,71"}},{"type":"CheckBox","props":{"y":488,"x":76,"var":"agreeCheck","skin":"base/checkbox.png","labelSize":48,"labelColors":"#ffffff","labelBold":true,"label":" 同意"}},{"type":"Label","props":{"y":491,"x":272,"width":189,"var":"agreeLink","underlineColor":"#ff0300","underline":true,"text":"用户协议","height":53,"fontSize":48,"color":"#ffffff","bold":true}}]},{"type":"Text","props":{"y":1025.5,"x":0,"wordWrap":true,"width":1920,"text":"抵制不良游戏，拒绝盗版游戏。注意自我保护，谨防上当受骗。","fontSize":38,"color":"#ffffff","alpha":0.5,"align":"center"}},{"type":"Text","props":{"y":978.4999999999999,"x":0,"wordWrap":true,"width":1920,"text":"适度游戏益脑，沉迷游戏伤身。合理安排时间，享受健康生活。","fontSize":38,"color":"#ffffff","alpha":0.5,"align":"center"}}]}]};
		override protected function createChildren():void {
			View.regComponent("Text",Text);
			super.createChildren();
			createView(uiView);
		}
	}
}