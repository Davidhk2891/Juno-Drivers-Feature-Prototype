package davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.model.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.R;
import davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.model.objects.Document;
import davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.utils.Camera;
import davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.utils.Gallery;
import davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.view.ImageLoader;

import static android.app.Activity.RESULT_OK;

/**
 * Created by David on 6/1/2019 for Juno Driver Features Prototype.
 */
public class DocumentAdapter extends BaseAdapter implements ActivityCompat.OnRequestPermissionsResultCallback{

    private static final int REQUEST_EXTERNAL_STORAGE_RESULT = 1;
    private LayoutInflater inflater;
    private ArrayList<Object> mList;
    private static final int DOC_ITEM = 0;
    private static final int STATUS_HEADER = 1;
    private Context context;
    private Activity activity;
    private Camera camera;
    private Gallery gallery;

    public DocumentAdapter(Context ctx, ArrayList<Object> list, Activity act){
        this.context = ctx;
        this.activity = act;
        this.mList = list;
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position) instanceof Document){
            return DOC_ITEM;
        }else{
            return STATUS_HEADER;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            switch(getItemViewType(i)){
                case DOC_ITEM:
                    view = inflater.inflate(R.layout.doc_title_view, null);
                    break;
                case STATUS_HEADER:
                    view = inflater.inflate(R.layout.status_header_view, null);
                    break;
            }
        }
        switch (getItemViewType(i)) {
            case DOC_ITEM:
                final TextView textView = view.findViewById(R.id.doc_title);
                final TextView textView2 = view.findViewById(R.id.doc_exp_date);
                textView.setText(((Document) mList.get(i)).getDocName());
                textView2.setText(((Document) mList.get(i)).getDocExp());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("doc pressed", "This doc is: " + textView.getText()
                                .toString());
                        open_dialog(textView.getText().toString());
                    }
                });
                break;
            case STATUS_HEADER:
                TextView headerText = view.findViewById(R.id.doc_status_header_title);
                headerText.setText(((String)mList.get(i)));
                if (headerText.getText().toString().equals(context.getResources().getString
                        (R.string.documents_upToDateDocs))){
                    headerText.setBackgroundColor(context.getResources().getColor
                            (R.color.light_green));
                }
                break;
        }
        return view;
    }

    private void open_dialog(String actionSelected){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.document_action_items, null);
        ListView lv = row.findViewById(R.id.docs_actions_list);
        lv.setAdapter(new ActionAdapter(context));
        builder.setView(row);
        AlertDialog dialog = builder.create();
        dialog.show();
        runListItemActions(lv, actionSelected);
    }

    private void runListItemActions(ListView lv, final String docSelected){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getAdapter().getItem(i).toString().equals(context.getResources
                        ().getString(R.string.document_actions_seeExample))){
                    //see example
                    Log.i("run", "see example for " + docSelected);
                    seeExample(docSelected);
                }else if(adapterView.getAdapter().getItem(i).toString().equals(context.getResources
                        ().getString(R.string.document_actions_takePicture))){
                    //take picture
                    Log.i("run", "take picture for " + docSelected);
                    camera = new Camera(context, activity);
                    camera.accessCamera(docSelected);
                }else if (adapterView.getAdapter().getItem(i).toString().equals(context.getResources
                        ().getString(R.string.document_actions_chooseFrom))){
                    //Choose from gallery
                    Log.i("run", "choose from Gallery for " + docSelected);
                    gallery = new Gallery(context, activity);
                    gallery.ChooseDocsFromGallery();
                }
            }
        });
    }

    private void seeExample(String docSelected){
        Intent intent = new Intent(context, ImageLoader.class);
        intent.putExtra("doc", docSelected);
        context.startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int i, @NonNull String[] strings, @NonNull int[] ints) {
        if(i == REQUEST_EXTERNAL_STORAGE_RESULT) {
            if(ints.length > 0 && ints[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(context, "permission granted"
                        , Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Please grant permission to access the camera"
                        , Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        switch(requestCode){
            case 1:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(context, "File Send Successfully"
                            , Toast.LENGTH_LONG).show();
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(context, "File Send Successfully"
                            , Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
