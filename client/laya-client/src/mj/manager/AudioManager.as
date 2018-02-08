package mj.manager
{
    import laya.media.SoundChannel;
    import laya.media.SoundManager;
    import laya.utils.Handler;

    import mj.utils.LayaUtils;

    /**
     * @author zuoge85@gmail.com on 2016/11/24.
     */
    public class AudioManager
    {
        private static const TYPE:String = ".mp3";

        public static const instance:AudioManager = new AudioManager();

        public function AudioManager()
        {
        }

        private var bgmSoundChannel:SoundChannel;

        public function playStartBgm():void
        {
            if (bgmSoundChannel)
            {
                bgmSoundChannel.stop();
            }
            bgmSoundChannel = SoundManager.playMusic("audio/bgm2" + TYPE, 0);
        }

        public function playGame():void
        {
            if (bgmSoundChannel)
            {
                bgmSoundChannel.stop();
            }
            bgmSoundChannel = SoundManager.playMusic("audio/bgm1" + TYPE, 0);
        }


        public static function getYinxiaoVolume():Number
        {
            return SoundManager.soundVolume;
        }

        public static function getYinyueVolume():Number
        {
            return SoundManager.musicVolume;
        }


        public static function changeYinxiao(number:Number):void
        {

            SoundManager.soundMuted = true;
            SoundManager.soundVolume = number;
            SoundManager.soundMuted = false;
        }

        public static function changeYinyue(number:Number):void
        {
            SoundManager.musicMuted = true;
            SoundManager.musicVolume = number;
            SoundManager.musicMuted = false;
        }

        public static function changeYinxiaoMuted(selected:Boolean):void
        {
            SoundManager.soundMuted = selected;
        }

        public static function changeYinyueMuted(selected:Boolean):void
        {
            SoundManager.musicMuted = selected;
        }



        public static function getYinxiaoMuted():Boolean
        {
            return SoundManager.soundMuted;
        }

        public static function getYinyueMuted():Boolean
        {
            return SoundManager.musicMuted;
        }
        /**
         * 0:女生,1:男生,2:未知
         */
        public static function paiOut(sex:int, pai:int):void
        {
            var sexStr:String = sex == 0 ? "woman" : "man";
            play("audio/pu_" + sexStr + "/pai_" + pai);
            play("audio/common/card_out");
        }

        public static function paiClick():void
        {
            play("audio/common/card_click");
        }

        /**
         * 处理牌
         */
        public static function dealCard():void
        {
            play("audio/common/deal_card");
        }

        /**
         * enter
         */
        public static function enter():void
        {
            play("audio/common/enter");
        }

        public static function win(sex:int, fangPaoIndex:int, fan:int):void
        {
            play("audio/common/win");

            var sexStr:String = sex == 0 ? "woman" : "man";
            if (fan == 1)
            {
                play("audio/pu_" + sexStr + "/hu_xiaohu");
            }
            else if (fan > 8)
            {
                play("audio/pu_" + sexStr + "/hu_da" + (LayaUtils.random(2) + 1));
            }
            else if (fangPaoIndex != -1)
            {
                play("audio/pu_" + sexStr + "/hu_pao" + (LayaUtils.random(3) + 1));
            }
            else if (fan > 1 && fangPaoIndex == -1)
            {
                play("audio/pu_" + sexStr + "/hu_zimo" + (LayaUtils.random(2) + 1));
            }
            else
            {
                play("audio/pu_" + sexStr + "/hu_" + (LayaUtils.random(3) + 1));
            }
        }

        public static function liuJu():void
        {
            play("audio/common/liuju");
        }

        public static function lose():void
        {
            play("audio/common/lose");
        }

        private static var isTimeupAlarmSoundChannel:SoundChannel;

        public static function stopTimeupAlarm():void
        {
            if (isTimeupAlarmSoundChannel)
            {
                isTimeupAlarmSoundChannel.stop();
                isTimeupAlarmSoundChannel = null;
            }
        }

        /**
         * 时间不够了
         */
        public static function timeupAlarm():void
        {
            if (isTimeupAlarmSoundChannel)
            {
                return;
            }
            isTimeupAlarmSoundChannel = play("audio/common/timeup_alarm", Handler.create(null, function ():void
            {
                isTimeupAlarmSoundChannel = null;
            }));
        }

        public static function buttonClick():void
        {
            play("audio/common/button_click");
        }


        private static function play(url:String, complete:Handler = null):SoundChannel
        {
            return SoundManager.playSound(url + TYPE, 1, complete);
        }

        public static function gang(sex:int):void
        {

            playOpt("gang", sex, 3);
        }

        public static function peng(sex:int):void
        {
            playOpt("peng", sex, 5);
        }

        public static function chi(sex:int):void
        {
            playOpt("chi", sex, 4);
        }

        private static function playOpt(type:String, sex:int, nums:int):void
        {
            var sexStr:String = sex == 0 ? "woman" : "man";
            play("audio/pu_" + sexStr + "/" + type + (LayaUtils.random(nums) + 1));
        }


    }
}
