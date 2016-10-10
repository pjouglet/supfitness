package supfitness.luciole.com.supfitness.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;
import java.util.ArrayList;
import java.util.List;

import supfitness.luciole.com.supfitness.GraphXLabels;
import supfitness.luciole.com.supfitness.R;

public class TabFragment2 extends Fragment{

    private XYPlot plot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_2, container, false);

        plot = (XYPlot)view.findViewById(R.id.plot);
        Number[] series1Numbers = {1, 4, 2, 8, 4, 16, 8, 32, 16, 64};

        List<Integer> weightList = new ArrayList<>();
        for(int i = 0; i < TabFragment1.adapter.getCount(); i++){
            weightList.add(TabFragment1.adapter.getItem(i).getWeigth());
        }
        XYSeries series = new SimpleXYSeries(weightList, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, getResources().getString(R.string.weight));

        LineAndPointFormatter pointFormatter = new LineAndPointFormatter();
        pointFormatter.setPointLabelFormatter(new PointLabelFormatter());
        pointFormatter.configure(getContext(), R.xml.point_formatter);
        pointFormatter.setInterpolationParams(new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

        plot.addSeries(series, pointFormatter);
        plot.setTicksPerRangeLabel(3);
        plot.getGraphWidget();
        plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);
        //plot.setDomainValueFormat(new DecimalFormat("#"));
        plot.setDomainValueFormat(new GraphXLabels());
        return view;
    }
}