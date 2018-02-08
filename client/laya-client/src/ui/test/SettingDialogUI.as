/**Created by the LayaAirIDE,do not modify.*/
package ui.test {
	import laya.ui.*;
	import laya.display.*;
	import laya.display.Text;

	public class SettingDialogUI extends View {
		public var mainSprite:Sprite;
		public var yinxiaoSlider:HSlider;
		public var yinyueSlider:HSlider;
		public var yinxiaoCheckbox:CheckBox;
		public var yinyueCheckbox:CheckBox;

		public static var uiView:Object ={"type":"View","props":{"y":0,"x":0,"width":896,"text":"手机号：","label":"下一步","height":241},"child":[{"type":"Sprite","props":{"y":0,"x":0,"width":896,"var":"mainSprite","height":241},"child":[{"type":"Sprite","props":{"y":0,"x":0,"scaleY":1.2,"scaleX":1.2},"child":[{"type":"HSlider","props":{"y":23.999999999999982,"x":166.99999999999997,"var":"yinxiaoSlider","value":50,"skin":"base/hslider.png","scaleY":1.5,"scaleX":1.5}},{"type":"HSlider","props":{"y":113.00000000000001,"x":166.99999999999997,"var":"yinyueSlider","value":50,"skin":"base/hslider.png","scaleY":1.5,"scaleX":1.5}},{"type":"Text","props":{"y":30.999999999999996,"x":44.00000000000001,"width":91,"text":"音效","fontSize":"48","color":"#cd5216","bold":true}},{"type":"CheckBox","props":{"y":19.000000000000004,"x":626,"var":"yinxiaoCheckbox","stateNum":"2","skin":"base/volume.png","scaleY":1.5,"scaleX":1.5}},{"type":"CheckBox","props":{"y":103,"x":626,"var":"yinyueCheckbox","stateNum":"2","skin":"base/volume.png","scaleY":1.5,"scaleX":1.5}},{"type":"Text","props":{"y":125,"x":46.00000000000004,"width":91,"text":"音乐","fontSize":"48","color":"#cd5216","bold":true}}]}]}]};
		override protected function createChildren():void {
			View.regComponent("Text",Text);
			super.createChildren();
			createView(uiView);
		}
	}
}