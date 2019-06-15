package davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.R;
import davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.model.DocsList;
import davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.model.adapters.DocumentAdapter;
import davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.utils.ImageFileStorage;

public class Documents extends AppCompatActivity {

    private DocsList docsList = new DocsList(Documents.this);
    private ListView mDocs_list_view;
    private DocumentAdapter documentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);

        ImageFileStorage.createImageGallery();

        //Navigate back to parent activity
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //--------------------------------

        mDocs_list_view = findViewById(R.id.docs_list_view);
        documentAdapter = new DocumentAdapter(Documents.this, docsList.list()
                , Documents.this);
        mDocs_list_view.setAdapter(documentAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        documentAdapter.onActivityResult(requestCode,resultCode,data);
    }
}
