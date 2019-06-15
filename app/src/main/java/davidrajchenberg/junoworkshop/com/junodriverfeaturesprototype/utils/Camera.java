package davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;

/**
 * Created by David on 6/3/2019 for Juno Driver Features Prototype.
 */
public class Camera {

    private Context context;
    private Activity activity;

    public Camera(Context ctx, Activity act){
        this.context = ctx;
        this.activity = act;
    }

    public void accessCamera(String docTitle){
        StoragePermission.isWrittingToStoragePermissionGranted(context,activity);
        if (StoragePermission.isWrittingToStoragePermissionGranted(context,activity)){
            try{
                File docFile = ImageFileStorage.createImageFile(docTitle);
                Uri targetUriIDString = ImageFileStorage.contentCamFile(context,docFile);
                //String rawImageString1 = docFile.getAbsolutePath();
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                i.putExtra(MediaStore.EXTRA_OUTPUT, targetUriIDString);
                activity.startActivityForResult(i, 1);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
