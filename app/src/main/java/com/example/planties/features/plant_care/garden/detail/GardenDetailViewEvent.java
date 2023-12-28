package com.example.planties.features.plant_care.garden.detail;

public class GardenDetailViewEvent {
    public static class OnLoadPlants extends GardenDetailViewEvent {
        private final String gardenId;

        public OnLoadPlants(String gardenId) {
            this.gardenId = gardenId;
        }

        public String getGardenId() {
            return gardenId;
        }
    }
    public static class OnLoadGarden extends GardenDetailViewEvent {
        private final String gardenId;

        public OnLoadGarden(String gardenId) {
            this.gardenId = gardenId;
        }

        public String getGardenId() {
            return gardenId;
        }
    }
}
