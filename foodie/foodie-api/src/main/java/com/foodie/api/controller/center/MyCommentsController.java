package com.foodie.api.controller.center;

import com.foodie.api.controller.BaseController;
import com.foodie.common.enums.YesOrNo;
import com.foodie.common.utils.PageR;
import com.foodie.common.utils.R;
import com.foodie.pojo.OrderItems;
import com.foodie.pojo.Orders;
import com.foodie.pojo.bo.center.OrderItemsCommentBO;
import com.foodie.pojo.vo.MyCommentVO;
import com.foodie.service.center.MyCommentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "用户中心评价模块", tags = {"用户中心评价模块相关接口"})
@RestController
@RequestMapping("mycomments")
public class MyCommentsController extends BaseController {

    @Autowired
    private MyCommentsService myCommentsService;

    @ApiOperation(value = "查询订单列表", notes = "查询订单列表", httpMethod = "POST")
    @PostMapping("/pending")
    public R<List<OrderItems>> pending(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId, @ApiParam(name = "orderId", value = "订单id", required = true) @RequestParam String orderId) {

        // 判断用户和订单是否关联
        Orders orders = super.checkUserOrder(userId, orderId);
        if(orders == null){
            return R.errorMsg("订单不存在");
        }
        // 判断该笔订单是否已经评价过，评价过了就不再继续
        if(orders.getIsComment().equals(YesOrNo.YES.type)){
            return R.errorMsg("该笔订单已经评价");
        }

        List<OrderItems> list = myCommentsService.queryPendingComment(orderId);

        return R.ok(list);
    }

    @ApiOperation(value = "保存评论列表", notes = "保存评论列表", httpMethod = "POST")
    @PostMapping("/saveList")
    public R<String> saveList(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId
            , @ApiParam(name = "orderId", value = "订单id", required = true) @RequestParam String orderId,
                              @RequestBody List<OrderItemsCommentBO> commentList) {

        System.out.println(commentList);

        // 判断用户和订单是否关联
        Orders orders = super.checkUserOrder(userId, orderId);
        if(orders == null){
            return R.errorMsg("订单不存在");
        }
        // 判断评论内容list不能为空
        if(commentList == null || commentList.isEmpty()){
            return R.errorMsg("评论内容不能为空！");
        }

        myCommentsService.saveComments(orderId, userId, commentList);
        return R.ok();
    }

    @ApiOperation(value = "查询我的评价", notes = "查询我的评价", httpMethod = "POST")
    @PostMapping("/query")
    public R<PageR<MyCommentVO>> query(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId, @ApiParam(name = "page", value = "查询下一页的第几页") @RequestParam Integer page, @ApiParam(name = "pageSize", value = "分页的每一页显示的条数") @RequestParam Integer pageSize) {

        if(StringUtils.isBlank(userId)){
            return R.errorMsg(null);
        }
        if(page == null){
            page = 1;
        }
        if(pageSize == null){
            pageSize = COMMON_PAGE_SIZE;
        }

        PageR<MyCommentVO> grid = myCommentsService.queryMyComments(userId, page, pageSize);

        return R.ok(grid);
    }

}
