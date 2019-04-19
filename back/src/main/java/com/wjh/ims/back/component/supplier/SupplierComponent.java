package com.wjh.ims.back.component.supplier;


import com.wjh.ims.back.ResponseUtil;
import com.wjh.ims.biz.supplier.SupplierService;
import com.wjh.ims.dto.vo.supplier.SupplierVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @desc: 供应商控制器
 * @date: 2019年3月25日10:26:37
 */
@Component
@Path("/supplier/")
public class SupplierComponent {

    @Autowired
    SupplierService supplierService;

    @GET
    @Path("supplier")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response supplier(@QueryParam("name")String name,@QueryParam("no")String no,
                          @QueryParam("start")int start,@QueryParam("rows")int rows){
        try {
            List<SupplierVO> supplierVOS=supplierService.getByCondition(name,no,start,rows);
            int count=supplierService.countByCondition(name,no);
            return ResponseUtil.data(supplierVOS,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.data(null,0);
        }
    }



    @POST
    @Path("supplier")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response supplier(SupplierVO supplierVO){
        try {
            supplierService.addSupplier(supplierVO);
            return ResponseUtil.success("添加供应商成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.failure(null);
        }
    }


    @PUT
    @Path("supplier/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response supplier(@PathParam("id")String id, SupplierVO supplierVO){
        try {
            supplierVO.setId(id);
            supplierService.editSupplier(supplierVO);
            return ResponseUtil.success("修改供应商成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.failure(null);
        }
    }
}
