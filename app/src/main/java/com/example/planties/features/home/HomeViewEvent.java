package com.example.planties.features.home;

import com.example.planties.features.utils.adapter.filter.FilterModel;

public abstract class HomeViewEvent {

    public static class OnRefresh extends HomeViewEvent {}
    public static class OnChangedFilter extends HomeViewEvent {
        private FilterModel filter;

        public OnChangedFilter(FilterModel filter) {
            this.filter = filter;
        }

        public FilterModel getFilter() {
            return filter;
        }
        public void setFilter(FilterModel filter) {
            this.filter = filter;
        }
    }

}
