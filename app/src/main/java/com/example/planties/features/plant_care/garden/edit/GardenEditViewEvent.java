package com.example.planties.features.plant_care.garden.edit;

import com.example.planties.data.garden.remote.dto.GardenReq;

public class GardenEditViewEvent {
    public static class OnLoadGarden extends GardenEditViewEvent {
        public String gardenId;

        public OnLoadGarden(String gardenId) {
            this.gardenId = gardenId;
        }
    }

    public static class OnSaveEdit extends GardenEditViewEvent {
        public String gardenId;
        public GardenReq gardenReq;

        public OnSaveEdit(String gardenId,  GardenReq gardenReq) {
            this.gardenId = gardenId;
            this.gardenReq = gardenReq;
        }
    }

    public static class OnAddImage extends GardenEditViewEvent {
        public String gardenId;
        public GardenReq gardenReq;

        public OnAddImage(String gardenId,  GardenReq gardenReq) {
            this.gardenId = gardenId;
            this.gardenReq = gardenReq;
        }
    }
}
