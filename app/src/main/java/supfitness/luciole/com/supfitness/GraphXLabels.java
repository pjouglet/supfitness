package supfitness.luciole.com.supfitness;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;

/**
 * Created by Luciole on 03/04/2016.
 */
public class GraphXLabels extends Format {

    public static String[] labels ={"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aou", "Sep", "Oct", "Nov", "Dec"};

    @Override
    public StringBuffer format(Object object, StringBuffer buffer, FieldPosition position){
        int parsedInt = Math.round(Float.parseFloat(object.toString()));
        String label = labels[parsedInt];
        buffer.append(label);
        return  buffer;
    }

    @Override
    public Object parseObject(String string, ParsePosition position) {
        return Arrays.asList(labels).indexOf(string);
    }
}
