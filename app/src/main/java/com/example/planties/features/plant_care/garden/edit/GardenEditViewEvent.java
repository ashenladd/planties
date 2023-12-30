package com.example.planties.features.plant_care.garden.edit;

import com.example.planties.data.garden.remote.dto.GardenReq;
import com.example.planties.data.reminder.remote.dto.ReminderReq;
import com.example.planties.features.plant_care.plant_detail.PlantDetailViewEvent;

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

    public static class OnAddReminder extends GardenEditViewEvent {
        private final String gardenId;
        private final ReminderReq reminderReq;

        public OnAddReminder(String gardenId,ReminderReq reminderReq){
            this.gardenId = gardenId;
            this.reminderReq = reminderReq;
        }

        public ReminderReq getReminderReq(){
            return reminderReq;
        }

        public String getGardenId(){
            return gardenId;
        }
    }

    public static class OnLoadReminder extends GardenEditViewEvent {
        private final String gardenId;

        public OnLoadReminder(String gardenId){
            this.gardenId = gardenId;
        }

        public String getGardenId(){
            return gardenId;
        }
    }
}
