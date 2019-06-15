package davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.view;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.R;

public class ImageLoader extends AppCompatActivity {

    private ImageView mExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);

        //Navigate back to parent activity
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //--------------------------------

        mExample = findViewById(R.id.example);
        imageLoader();
    }

    private void imageLoader(){
        Resources res = getResources();
        Intent intent = getIntent();
        String docType = intent.getStringExtra("doc");
        switch(docType){
            case "FHV License":
                setActTitle("FHV License");
                load(res.getDrawable(R.drawable.diamond));
                break;
            case "Vehicle Registration":
                setActTitle("Vehicle Registration");
                load(res.getDrawable(R.drawable.vehiclereg));
                break;
            case "Cert. of liability insurance":
                setActTitle("Cert. of liability insurance");
                load(res.getDrawable(R.drawable.certlowres));
                break;
            case "DMV License":
                setActTitle("DMV License");
                load(res.getDrawable(R.drawable.dmv));
                break;
            case "TLC License":
                setActTitle("TLC License");
                load(res.getDrawable(R.drawable.tlc));
                break;
        }
    }

    private void setActTitle(String docTitle){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(docTitle);
        }
    }

    private void load(Drawable drawable){
        Glide.with(ImageLoader.this)
                .load(drawable)
                .fitCenter()
                .into(mExample);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

