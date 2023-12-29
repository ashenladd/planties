package com.example.planties.data.garden.remote.network;

import com.example.planties.core.Constant;
import com.example.planties.data.garden.remote.dto.GardenDetailRes;
import com.example.planties.data.garden.remote.dto.GardenListRes;
import com.example.planties.data.garden.remote.dto.GardenReq;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GardenService {

    @GET(Constant.GARDEN)
    Call<GardenListRes> getGarden(@HeaderMap Map<String, String> token, @Query("sorting") String sorting, @Query("type") String type);
    @GET(Constant.GARDEN)
    Call<GardenListRes> getGardenAll(@HeaderMap Map<String, String> token);
    @POST(Constant.GARDEN)
    Call<GardenDetailRes> postGarden(@Body GardenReq gardenReq, @HeaderMap Map<String, String> token);

    @GET(Constant.DETAIL_GARDEN)
    Call<GardenDetailRes> getDetailGarden(@Path("gardenId") String gardenId, @HeaderMap Map<String, String> token);

    @PUT(Constant.DETAIL_GARDEN)
    Call<GardenDetailRes> putGarden(@Path("gardenId") String gardenId, @Body GardenReq gardenReq, @HeaderMap Map<String, String> token);

    @DELETE(Constant.DETAIL_GARDEN)
    Call<GardenDetailRes> deleteGarden(@Path("gardenId") String gardenId, @HeaderMap Map<String, String> token);
}
