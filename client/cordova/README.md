#麻将客户端

#cordova

npm install -g cordova.

sudo npm install -g cordova@5.4.1

cordova create cordova org.forkjoin.bjmajiang bjmajiang

cd cordova

cordova platform add ios
cordova platform add android

cordova platform rm android && cordova platform add android
cordova platform rm ios && cordova platform add ios

cordova platforms ls 检查支持的系统

cordova prepare
cordova build

cordova emulate android
cordova run android
adb devices

cordova platform update android && cordova build android && cordova run android --device
cordova platform update ios && cordova build ios&& cordova run ios --device

cordova run ios --device

cordova build  && cordova run ios --device

cordova platform update ios

解决图标什么的问题

ionic resources
cordova resources
ionic platform update android@6.1.0
cordova platform update android@6.1.0
[文档地址](https://cordova.apache.org/docs/zh-cn/6.x/guide/cli/index.html)




cordova plugin add cordova-plugin-wechat --variable wechatappid=wx15a6c9172f161305
cordova plugin remove cordova-plugin-whitelist

 在plist文件中添加NSLocationWhenInUseUsageDescription或（与）NSLocationAlwaysUsageDescription字段：  
 找到info.plist文件－>右击->Open As->Source Code->在尾部的</dict>标签之前添加以下一个或两个：  
 <key>NSLocationWhenInUseUsageDescription</key><string>需要定位</string>  
 <key>NSLocationAlwaysUsageDescription</key><string>需要定位</string>
 
 
sudo npm  uninstall -g ionic cordova
sudo npm install -g ionic cordova

## 需要插件
cordova plugin add cordova-plugin-geolocation --save --variable GEOLOCATION_USAGE_DESCRIPTION="需要定位确定是否作弊"
cordova plugin add cordova-plugin-splashscreen --save
cordova plugin add cordova-plugin-statusbar --save
cordova plugin add cordova-plugin-app-name --variable APP_NAME="麻将" --save
cordova plugin add cordova-plugin-wechat  --save --variable wechatappid=wx



cordova plugin add cordova-plugin-crosswalk-webview --save




keytool -genkey -v -keystore release-key.keystore -alias majiang -keyalg RSA -keysize 2048 -validity 10000





cordova plugin remove cordova-plugin-geolocation


export CORDOVA_ANDROID_GRADLE_DISTRIBUTION_URL=file:///Users/zuoge85/Downloads/gradle-2.14.1-all.zip

cordova build android --release
cordova build ios --release --device