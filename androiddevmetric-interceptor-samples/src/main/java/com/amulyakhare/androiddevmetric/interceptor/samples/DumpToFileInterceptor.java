package com.amulyakhare.androiddevmetric.interceptor.samples;

import android.util.Log;

import com.frogermcs.androiddevmetrics.internal.MetricDescription;
import com.frogermcs.androiddevmetrics.internal.MetricDescriptionTreeItem;
import com.frogermcs.androiddevmetrics.internal.metrics.model.InitMetric;
import com.frogermcs.androiddevmetrics.internal.ui.interceptor.UIInterceptor;

import java.util.List;

/**
 * @author amulya
 * @since 13 Jan 2017, 8:04 PM.
 *
 * This interceptor will dump the entire tree to logcat. Could similarly dump
 * to HTML file for even better analysis on the browser.
 */
public class DumpToFileInterceptor implements UIInterceptor {

    @Override
    public List<InitMetric> intercept(List<InitMetric> metrics) {
        StringBuilder builder = new StringBuilder();
        for (InitMetric metric : metrics) {
            builder.append("---------------------------------------------\n");
            MetricDescription description = MetricDescription.InitFromMetric(metric);
            builder.append(description.className);
            builder.append("\n");
            builder.append(description.formattedInitTime.replaceAll("<[^>]*>", ""));
            builder.append("\n");
            for (MetricDescriptionTreeItem child : description.descriptionTreeItems) {
                String dumpText = child.description.replaceAll("(?i)<br[^>]*>", "\n");
                builder.append(dumpText.replaceAll("<[^>]*>", ""));
                builder.append("\n");
            }
        }
        Log.d("DAGGER", builder.toString());
        return metrics;
    }

    @Override
    public String getName() {
        return "Dump To Logcat";
    }
}
