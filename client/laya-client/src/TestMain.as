package {
	import com.isnowfox.core.test.TestRunner;

	import test.IoTest;

	import view.*;
	import laya.display.Stage;
	import laya.net.Loader;
	import laya.utils.Browser;
	import laya.utils.Handler;
	import laya.utils.Log;
	import laya.utils.Stat;
	import laya.webgl.WebGL;

	import view.TestView;

	public class TestMain
	{

		public function TestMain()
		{
			//初始化引擎
			Laya.init(Browser.width, Browser.height, WebGL, Log);
			trace("Browser.clientWidth", Browser.clientWidth, "Browser.clientHeight", Browser.clientHeight);
			trace("Browser.width", Browser.width, "Browser.height", Browser.height);
			trace("Browser.pixelRatio", Browser.pixelRatio);
			trace("Browser.soundType", Browser.soundType);

			Laya.stage.scaleMode = Stage.SCALE_NOSCALE;
			Laya.stage.alignH = Stage.ALIGN_LEFT;
			Laya.stage.alignV = Stage.ALIGN_TOP;

			Laya.stage.scale(Browser.pixelRatio, Browser.pixelRatio);

			Stat.show(0, 0);

			start();
		}

		private function start():void
		{
			//实例UI界面
			var runner:TestRunner = new TestRunner();
			runner.addCase(IoTest);

			runner.run();
		}
	}
}