package com.example.planties.features.plant_care.plant_detail;

import com.example.planties.data.plant.remote.dto.PlantReq;
import com.example.planties.data.plant.remote.dto.PlantReqPut;

public class PlantDetailViewEvent {
    public static class OnSaveEdit extends PlantDetailViewEvent {
        private String gardenId;
        private String plantId;
        private PlantReqPut plantReq;

        public OnSaveEdit(String gardenId, String plantId, PlantReqPut plantReq) {
            this.gardenId = gardenId;
            this.plantId = plantId;
            this.plantReq = plantReq;
        }

        public String getGardenId() {
            return gardenId;
        }

        public String getPlantId() {
            return plantId;
        }

        public PlantReqPut getPlantReq() {
            return plantReq;
        }

        public void setGardenId(String gardenId) {
            this.gardenId = gardenId;
        }

        public void setPlantId(String plantId) {
            this.plantId = plantId;
        }

        public void setPlantReq(PlantReqPut plantReq) {
            this.plantReq = plantReq;
        }
    }

    public static class OnAddImage extends PlantDetailViewEvent {
        private String gardenId;
        private String plantId;
        private PlantReqPut plantReq;

        public OnAddImage(String gardenId, String plantId, PlantReqPut plantReq) {
            this.gardenId = gardenId;
            this.plantId = plantId;
            this.plantReq = plantReq;
        }

        public String getGardenId() {
            return gardenId;
        }

        public String getPlantId() {
            return plantId;
        }

        public PlantReqPut getPlantReq() {
            return plantReq;
        }

        public void setGardenId(String gardenId) {
            this.gardenId = gardenId;
        }

        public void setPlantId(String plantId) {
            this.plantId = plantId;
        }

        public void setPlantReq(PlantReqPut plantReq) {
            this.plantReq = plantReq;
        }
    }

    public static class OnAddPlant extends PlantDetailViewEvent {
        private final String gardenId;
        private final PlantReq plantReq;

        public OnAddPlant(String gardenId, PlantReq plantReq) {
            this.gardenId = gardenId;
            this.plantReq = plantReq;
        }

        public String getGardenId() {
            return gardenId;
        }

        public PlantReq getPlantReq() {
            return plantReq;
        }
    }

    public static class OnLoadPlant extends PlantDetailViewEvent {
        private final String gardenId;
        private final String plantId;

        public OnLoadPlant(String gardenId, String plantId) {
            this.gardenId = gardenId;
            this.plantId = plantId;
        }

        public String getGardenId() {
            return gardenId;
        }

        public String getPlantId() {
            return plantId;
        }
    }

    public static class OnClickEdit extends PlantDetailViewEvent { }

    public static class OnLoadGarden extends PlantDetailViewEvent {
        private final String gardenId;

        public OnLoadGarden(String gardenId) {
            this.gardenId = gardenId;
        }

        public String getGardenId() {
            return gardenId;
        }
    }

    public static class OnAddPostImage extends PlantDetailViewEvent {
        private final String image;

        public OnAddPostImage(String image) {
            this.image = image;
        }

        public String getImage() {
            return image;
        }

    }
}
