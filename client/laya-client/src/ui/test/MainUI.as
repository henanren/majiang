/**Created by the LayaAirIDE,do not modify.*/
package ui.test {
	import laya.ui.*;
	import laya.display.*;
	import laya.display.Text;

	public class MainUI extends View {
		public var bg:Sprite;
		public var mainBg:Image;
		public var mainSprite:Sprite;
		public var userHead:Sprite;
		public var userHeadOut:Sprite;
		public var userHeadMask:Image;
		public var userHeadIconOut:Sprite;
		public var userHeadIcon:Image;
		public var addFangkaSprite:Sprite;
		public var userId:Text;
		public var gold:Text;
		public var userName:Text;
		public var addBtn:Button;
		public var notice:Sprite;
		public var noticeBg:Image;
		public var noticeLabel:Label;
		public var crateBtn:Button;
		public var joinBtn:Button;
		public var quitBtn:Button;
		public var settingBtn:Button;
		public var historyBtn:Button;
		public var newNoticeSprite:Sprite;
		public var newNotice:Text;

		public static var uiView:Object ={"type":"View","props":{"text":"ID:3213124"},"child":[{"type":"Sprite","props":{"y":0,"x":3,"width":1920,"var":"bg","height":1080},"child":[{"type":"Image","props":{"y":0,"x":0,"var":"mainBg","skin":"ui/home/bg.jpg"}},{"type":"Image","props":{"y":217,"x":96,"width":419,"skin":"ui/home/mm.png","height":865}}]},{"type":"Sprite","props":{"y":0,"x":3,"width":1920,"var":"mainSprite","height":1080},"child":[{"type":"Sprite","props":{"y":9,"x":126,"width":370,"var":"userHead","scaleY":1.3,"scaleX":1.3,"height":168},"child":[{"type":"Sprite","props":{"y":12,"x":14,"width":530,"var":"userHeadOut","scaleY":0.25,"scaleX":0.25,"height":534},"child":[{"type":"Image","props":{"y":2,"x":-2,"width":133,"skin":"ui/game/user_head_bg.png","scaleY":4,"scaleX":4,"height":133,"sizeGrid":"60,60,60,60"},"child":[{"type":"Image","props":{"y":0,"x":0,"width":133,"var":"userHeadMask","skin":"ui/game/user_head_mask.png","height":133,"sizeGrid":"60,60,60,60"}},{"type":"Sprite","props":{"y":0,"x":0,"width":133,"var":"userHeadIconOut","height":133},"child":[{"type":"Image","props":{"y":4,"x":7,"width":119,"var":"userHeadIcon","skin":"ui/game/user_head_default.png","pivotY":0,"pivotX":0,"height":115}}]}]}]},{"type":"Sprite","props":{"y":19.00000000000002,"x":145.00000000000006,"width":219.99999999999983,"var":"addFangkaSprite","mouseThrough":false,"mouseEnabled":true,"height":121.99999999999997},"child":[{"type":"Text","props":{"y":46.000000000000036,"x":-1.0000000000000568,"width":204,"var":"userId","text":"ID:7777777","height":35,"fontSize":30,"color":"#ffcc01","align":"center"}},{"type":"Image","props":{"y":80.99999999999994,"x":5,"skin":"ui/game/gold_bg.png"}},{"type":"Text","props":{"y":84,"x":3,"width":183,"var":"gold","text":"房卡:888888","height":35,"fontSize":28,"color":"#ffcc01","align":"center"}},{"type":"Text","props":{"y":0,"x":0,"width":195,"var":"userName","text":"用户名称","height":46,"fontSize":40,"color":"#ffcc01","align":"center"}},{"type":"Button","props":{"y":76.99999999999997,"x":174.99999999999983,"var":"addBtn","skin":"ui/home/btn_add_gold.png"}}]}]},{"type":"Sprite","props":{"y":215.00000000000003,"x":476.99999999999983,"var":"notice"},"child":[{"type":"Image","props":{"y":0,"x":0,"var":"noticeBg","skin":"ui/home/notice_bg.png"}},{"type":"Image","props":{"y":5,"x":3,"skin":"ui/home/notice_icon.png"}},{"type":"Label","props":{"y":12,"x":111,"width":897,"var":"noticeLabel","text":"我是跑马灯我是跑马灯我是跑马灯我是跑马灯我是跑马灯我是跑马灯我是跑马灯我是跑马灯","overflow":"hidden","height":40,"fontSize":40,"color":"#ffcc01","bold":false,"align":"left"}}]},{"type":"Sprite","props":{"y":352.25,"x":726},"child":[{"type":"Button","props":{"y":0,"x":0,"width":531,"var":"crateBtn","skin":"ui/home/btn_create.png","label":"label","height":595}},{"type":"Button","props":{"y":0,"x":554.0000000000002,"width":531,"var":"joinBtn","skin":"ui/home/btn_join.png","label":"label","height":595}}]},{"type":"Button","props":{"y":16,"x":1384,"width":120,"var":"quitBtn","stateNum":"1","skin":"base/quit.png","sizeGrid":"121,0,0,0","labelSize":40,"labelPadding":"50,0,0,0","labelColors":"#ffffff","label":"退出","height":150}},{"type":"Button","props":{"y":13,"x":1523,"width":120,"var":"settingBtn","stateNum":"1","skin":"base/shezhi.png","sizeGrid":"121,0,0,0","labelSize":40,"labelPadding":"50,0,0,0","labelColors":"#ffffff","label":"设置","height":150}},{"type":"Button","props":{"y":16,"x":1662,"width":120,"var":"historyBtn","stateNum":"1","skin":"base/history.png","sizeGrid":"121,0,0,0","labelSize":40,"labelPadding":"50,0,0,0","labelColors":"#ffffff","label":"战绩","height":150}},{"type":"Sprite","props":{"y":316,"x":104,"var":"newNoticeSprite"},"child":[{"type":"Image","props":{"y":0,"x":0,"width":373,"skin":"base/dialog_bg.png","scaleY":1.5,"scaleX":1.5,"height":445,"alpha":0.7,"sizeGrid":"90,37,44,35"}},{"type":"Text","props":{"y":41,"x":127,"width":278,"text":"健康游戏公告","height":56,"fontSize":"50","color":"#ffffff","alpha":0.8}},{"type":"Text","props":{"y":149,"x":72,"wordWrap":true,"width":414,"var":"newNotice","text":"健康游戏公告","height":442,"fontSize":"50","color":"#000000","alpha":0.8}}]},{"type":"Text","props":{"y":1035,"x":7,"wordWrap":true,"width":1920,"text":"抵制不良游戏，拒绝盗版游戏。注意自我保护，谨防上当受骗。","fontSize":"35","color":"#ffffff","alpha":0.5,"align":"center"}},{"type":"Text","props":{"y":988,"x":7,"wordWrap":true,"width":1920,"text":"适度游戏益脑，沉迷游戏伤身。合理安排时间，享受健康生活。","fontSize":"35","color":"#ffffff","alpha":0.5,"align":"center"}}]}]};
		override protected function createChildren():void {
			View.regComponent("Text",Text);
			super.createChildren();
			createView(uiView);
		}
	}
}