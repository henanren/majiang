package game.boss.services;

/**
 * @author zuoge85@gmail.com on 16/10/5.
 */
public interface BaseService {
    /**
     * 执行时间变成1毫秒间隔
     */
    int FRAME_TIME_SPAN = 8;
    int RUN_QUEUE_MAX = 1024;
    int CHECK_ROOM_ID_LEN = 6;

    int INIT_ID_NUMS = 200;
    int INIT_ID_BUFFER = 50;
    int LOOP_NUMS = 5;
}
