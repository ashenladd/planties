package com.example.planties.data.plant.remote.network;

import com.example.planties.core.Constant;
import com.example.planties.data.plant.remote.dto.PlantDetailRes;
import com.example.planties.data.plant.remote.dto.PlantListRes;
import com.example.planties.data.plant.remote.dto.PlantReq;
import com.example.planties.data.plant.remote.dto.PlantReqPut;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PlantService {
    @POST(Constant.GARDEN_PLANT)
    Call<PlantDetailRes> postPlant(@Body PlantReq plantReq, @Path("gardenId") String gardenId, @HeaderMap Map<String, String> token);
    @GET(Constant.PLANT)
    Call<PlantListRes> getPlants(@HeaderMap Map<String, String> token);
    @GET(Constant.GARDEN_PLANT)
    Call<PlantListRes> getPlantsWithGarden(@Path("gardenId") String gardenId, @HeaderMap Map<String, String> token);
    @GET(Constant.DETAIL_PLANT)
    Call<PlantDetailRes> getDetailPlant(@Path("gardenId") String gardenId, @Path("plantId") String plantId, @HeaderMap Map<String, String> token);
    @PUT(Constant.DETAIL_PLANT)
    Call<PlantDetailRes> putPlant(@Body PlantReqPut plantReq, @Path("gardenId") String gardenId, @Path("plantId") String plantId, @HeaderMap Map<String, String> token);
    @DELETE(Constant.DETAIL_PLANT)
    Call<PlantDetailRes> deletePlant(@Path("gardenId") String gardenId, @Path("plantId") String plantId, @HeaderMap Map<String, String> token);
}
