package com.wjh.ims.back.component.customer;


import com.wjh.ims.back.ResponseUtil;
import com.wjh.ims.biz.customer.CustomerService;
import com.wjh.ims.dto.vo.customer.CustomerVO;
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

@Component
@Path("/customer/")
public class CustomerComponent {

    @Autowired
    CustomerService customerService;

    @GET
    @Path("customer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response customer(@QueryParam("name")String name,@QueryParam("no")String no,
                          @QueryParam("start")int start,@QueryParam("rows")int rows){
        try {
            List<CustomerVO> customerVOS=customerService.getByCondition(name,no,start,rows);
            int count=customerService.countByCondition(name,no);
            return ResponseUtil.data(customerVOS,count);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.data(null,0);
        }
    }


    @POST
    @Path("customer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response customer(CustomerVO customerVO){
        try {
            customerService.addCustomer(customerVO);
            return ResponseUtil.success("添加客户成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.failure(null);
        }
    }


    @PUT
    @Path("customer/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response customer(@PathParam("id")String id, CustomerVO customerVO){
        try {
            customerVO.setId(id);
            customerService.editCustomer(customerVO);
            return ResponseUtil.success("修改客户成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.failure(null);
        }
    }
}
