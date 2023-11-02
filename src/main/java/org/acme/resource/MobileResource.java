package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/mobile")
@Produces(MediaType.APPLICATION_JSON)
public class MobileResource {
 List<Mobile> mobileList=new ArrayList<>();

@GET
@Produces(MediaType.APPLICATION_JSON)
    public Response getMobileList() {
          return Response.ok(mobileList).build();
    }

    @POST
@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMobile(Mobile mobile){
    mobileList.add(mobile);
    return Response.ok(mobile).build();
    }

@PUT
@Path("/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public Response updateMobile(@PathParam("id") int id,Mobile mobileToUpdate){
    mobileList=mobileList.stream().map(mobile -> {
        if (mobile.getId()==id){
            return mobileToUpdate;
        }else {
            return mobile;
        }
    }).collect(Collectors.toList());
    return Response.ok(mobileList).build();
}




@DELETE
    @Path("/{id}")
    public Response deleteMobile(@PathParam("id") int mobileIdToDelete){
           Optional<Mobile> mobileToDelete = mobileList.stream()
                   .filter(mobile -> mobile.getId()==mobileIdToDelete).findFirst();

           if(mobileToDelete.isPresent()){
                 mobileList.remove(mobileToDelete);
                 return Response.ok(mobileList).build();
           }else{
              return Response.status(Response.Status.BAD_REQUEST).build();
           }
    }


/*
* this method will return mobile information
*  based on id  @Param id
* @return mobile*/

    @GET
    @Path(MediaType.APPLICATION_JSON)
    public Response getMobileById(@PathParam("id") int id){
        Optional<Mobile> optionalMobile=mobileList.stream().filter(mobile -> mobile.getId()==id).findFirst();

            if (optionalMobile.isPresent()) {
                return Response.ok(optionalMobile).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
    }









