package game.scene.msg;

import com.isnowfox.game.proxy.PxMsgFactory;


public class ScenePxMsgFactory extends PxMsgFactory {
    @Override
    protected void init() {
        super.init();
        super.add(RegSceneMsg.ID, RegSceneMsg.class);
        super.add(CheckJoinRoomMsg.ID, CheckJoinRoomMsg.class);
        super.add(CheckExitRoomMsg.ID, CheckExitRoomMsg.class);
        super.add(CheckDelRoomMsg.ID, CheckDelRoomMsg.class);
        super.add(CheckOfflineRoomMsg.ID, CheckOfflineRoomMsg.class);
        super.add(ChapterEndMsg.ID, ChapterEndMsg.class);
        super.add(CheckJoinRoomRetMsg.ID, CheckJoinRoomRetMsg.class);
        super.add(RoomEndMsg.ID, RoomEndMsg.class);
        super.add(ChapterStartMsg.ID, ChapterStartMsg.class);
    }
}
