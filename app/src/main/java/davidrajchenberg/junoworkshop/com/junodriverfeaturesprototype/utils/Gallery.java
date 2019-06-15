package davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by David on 6/3/2019 for Juno Driver Features Prototype.
 */
public class Gallery {
    private Context context;
    private Activity activity;

    public Gallery(Context ctx, Activity act){
        this.context = ctx;
        this.activity = act;
    }

    public void ChooseDocsFromGallery(){
        StoragePermission.isStoragePermissionGranted(context,activity);
        if(StoragePermission.isStoragePermissionGranted(context,activity)){
            Intent intent2 = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activity.startActivityForResult(intent2, 2);
        }
    }
}
