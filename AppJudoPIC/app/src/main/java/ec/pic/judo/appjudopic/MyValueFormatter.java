package ec.pic.judo.appjudopic;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

public class MyValueFormatter extends IndexAxisValueFormatter {

    private String[] mvalues;

    public MyValueFormatter(String[] values) {

        this.mvalues = values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mvalues[(int)value];
    }

}
