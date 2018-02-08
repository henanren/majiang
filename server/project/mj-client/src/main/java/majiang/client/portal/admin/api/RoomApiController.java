package majiang.client.portal.admin.api;

import majiang.client.portal.admin.model.PageModel;
import majiang.client.portal.admin.model.RoomModel;
import majiang.client.services.AdminRoomService;
import org.forkjoin.apikit.core.Api;
import org.forkjoin.apikit.core.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zuoge85 on 15/6/11.
 */
@Api
@RestController
@RequestMapping(value = "admin/v1")
public class RoomApiController {
    @Autowired
    private AdminRoomService adminRoomService;

    /**
     * 测试
     */
    @RequestMapping(value = "room/list/{page}/{pageSize}", method = RequestMethod.GET)
    public Result<PageModel<RoomModel>> list(
            @PathVariable int page, @PathVariable int pageSize
    ) {
        return Result.createSuccess(adminRoomService.getPage(
                page, pageSize
        ));
    }
}
