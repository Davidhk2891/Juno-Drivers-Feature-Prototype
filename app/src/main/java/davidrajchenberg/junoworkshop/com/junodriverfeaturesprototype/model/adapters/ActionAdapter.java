package davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.model.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.R;

/**
 * Created by David on 6/2/2019 for Juno Driver Features Prototype.
 */
public class ActionAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> arrayList;

    public ActionAdapter(Context ctx){
        this.context = ctx;
        arrayList = new ArrayList<>();
        Resources res = context.getResources();
        String[] actionTitles = res.getStringArray(R.array.document_actions);

        arrayList.addAll(Arrays.asList(actionTitles));
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_action_item, viewGroup, false);
        TextView tv = row.findViewById(R.id.action_title);
        tv.setText(arrayList.get(i));

        return row;
    }
}
