package com.foodie.api.controller.center;

import com.foodie.api.controller.BaseController;
import com.foodie.common.utils.PageR;
import com.foodie.common.utils.R;
import com.foodie.pojo.OrderStatus;
import com.foodie.pojo.Orders;
import com.foodie.pojo.vo.MyOrdersVO;
import com.foodie.pojo.vo.OrderStatusCountsVO;
import com.foodie.service.center.MyOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "用户中心我的订单", tags = {"用户中心我的订单相关接口"})
@RestController
@RequestMapping("myorders")
public class MyOrdersController extends BaseController {

    @Autowired
    private MyOrdersService myOrdersService;

    @ApiOperation(value = "获得订单状态数概况", notes = "获得订单状态数概况", httpMethod = "POST")
    @PostMapping("/statusCounts")
    public R<OrderStatusCountsVO> statusCounts(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId) {

        if(StringUtils.isBlank(userId)){
            return R.errorMsg(null);
        }

        OrderStatusCountsVO result = myOrdersService.getOrderStatusCounts(userId);

        return R.ok(result);
    }

    @ApiOperation(value = "查询订单列表", notes = "查询订单列表", httpMethod = "POST")
    @PostMapping("/query")
    public R<PageR<MyOrdersVO>> query(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId, @ApiParam(name = "orderStatus", value = "订单状态") @RequestParam Integer orderStatus, @ApiParam(name = "page", value = "查询下一页的第几页") @RequestParam Integer page, @ApiParam(name = "pageSize", value = "分页的每一页显示的条数") @RequestParam Integer pageSize) {

        if(StringUtils.isBlank(userId)){
            return R.errorMsg(null);
        }
        if(page == null){
            page = 1;
        }
        if(pageSize == null){
            pageSize = COMMON_PAGE_SIZE;
        }

        PageR<MyOrdersVO> grid = myOrdersService.queryMyOrders(userId, orderStatus, page, pageSize);

        return R.ok(grid);
    }

    /** 商家发货没有后端，所以这个接口仅仅只是用于模拟 */
    @ApiOperation(value = "商家发货", notes = "商家发货", httpMethod = "GET")
    @GetMapping("/deliver")
    public R<String> deliver(@ApiParam(name = "orderId", value = "订单id", required = true) @RequestParam String orderId) {

        if(StringUtils.isBlank(orderId)){
            return R.errorMsg("订单ID不能为空");
        }
        myOrdersService.updateDeliverOrderStatus(orderId);
        return R.ok();
    }

    @ApiOperation(value = "用户确认收货", notes = "用户确认收货", httpMethod = "POST")
    @PostMapping("/confirmReceive")
    public R<String> confirmReceive(@ApiParam(name = "orderId", value = "订单id", required = true) @RequestParam String orderId, @ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId) {
        Orders orders = super.checkUserOrder(userId, orderId);
        if(orders == null){
            return R.errorMsg("订单不存在");
        }
        boolean res = myOrdersService.updateReceiveOrderStatus(orderId);
        if(!res){
            return R.errorMsg("订单确认收货失败！");
        }
        return R.ok();
    }

    @ApiOperation(value = "用户删除订单", notes = "用户删除订单", httpMethod = "POST")
    @PostMapping("/delete")
    public R<String> delete(@ApiParam(name = "orderId", value = "订单id", required = true) @RequestParam String orderId
            , @ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId) {
        Orders orders = super.checkUserOrder(userId, orderId);
        if(orders == null){
            return R.errorMsg("订单不存在");
        }
        boolean res = myOrdersService.deleteOrder(userId, orderId);
        if(!res){
            return R.errorMsg("订单删除失败！");
        }
        return R.ok();
    }

    @ApiOperation(value = "查询订单动向", notes = "查询订单动向", httpMethod = "POST")
    @PostMapping("/trend")
    public R<PageR<OrderStatus>> trend(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId, @ApiParam(name = "page", value = "查询下一页的第几页") @RequestParam Integer page, @ApiParam(name = "pageSize", value = "分页的每一页显示的条数") @RequestParam Integer pageSize) {
        if(StringUtils.isBlank(userId)){
            return R.errorMsg(null);
        }
        if(page == null){
            page = 1;
        }
        if(pageSize == null){
            pageSize = COMMON_PAGE_SIZE;
        }

        PageR<OrderStatus> grid = myOrdersService.getOrdersTrend(userId, page, pageSize);
        return R.ok(grid);
    }

}
