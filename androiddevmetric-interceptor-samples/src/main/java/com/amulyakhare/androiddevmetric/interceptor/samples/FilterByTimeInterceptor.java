package com.amulyakhare.androiddevmetric.interceptor.samples;

import com.frogermcs.androiddevmetrics.internal.metrics.model.InitMetric;
import com.frogermcs.androiddevmetrics.internal.ui.interceptor.UIInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amulya
 * @since 13 Jan 2017, 8:28 PM.
 *
 * This interceptor allows the user to filter the list to display
 * dependencies which took more than `timeMS` millis. This allows
 * for faster debugging as lot of clutter is removed.
 */
public class FilterByTimeInterceptor implements UIInterceptor {

    private final int timeMs;

    public FilterByTimeInterceptor(int timeMs) {
        this.timeMs = timeMs;
    }

    @Override
    public List<InitMetric> intercept(List<InitMetric> metrics) {
        List<InitMetric> filteredList = new ArrayList<>();
        for (InitMetric metric : metrics) {
            if (metric.getTotalInitTime() >= timeMs) {
                filteredList.add(metric);
            }
        }
        return filteredList;
    }

    @Override
    public String getName() {
        return "Filter By Time (" + this.timeMs + "ms)";
    }
}
