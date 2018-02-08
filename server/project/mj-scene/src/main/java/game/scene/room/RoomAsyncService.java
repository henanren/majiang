package game.scene.room;

import com.isnowfox.service.AbstractScheduledAsyncService;

import java.util.concurrent.ScheduledFuture;

/**
 * room 线程
 *
 * @author zuoge85 on 14-1-8.
 */
public final class RoomAsyncService extends AbstractScheduledAsyncService {
    public RoomAsyncService(int threadNums) {
        super(0, threadNums);
    }

    public void run(int id, Runnable task) {
        submit(id, task);
    }

    protected ScheduledFuture<?> runFrame(int id, Runnable task, int delayMilliseconds) {
        return submit(id, task, delayMilliseconds);
    }
}
