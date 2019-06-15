package davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.model;

import android.content.Context;

import java.util.ArrayList;

import davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.R;
import davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.model.objects.Document;

/**
 * Created by David on 6/1/2019 for Juno Driver Features Prototype.
 */
public class DocsList {

    private Context context;

    public DocsList(Context ctx){
        this.context = ctx;
    }

    public ArrayList<Object> list(){
        String doc1 = context.getResources().getString(R.string.document_certificateOfLia);
        String doc2 = context.getResources().getString(R.string.document_tlcLicense);
        String doc3 = context.getResources().getString(R.string.document_dmvLicense);
        String doc4 = context.getResources().getString(R.string.document_fhvLicense);
        String doc5 = context.getResources().getString(R.string.document_vehicleRegis);
        String exp1 = context.getResources().getString(R.string.date1);
        String exp2 = context.getResources().getString(R.string.date2);
        String exp3 = context.getResources().getString(R.string.date3);
        String exp4 = context.getResources().getString(R.string.date4);
        String exp5 = context.getResources().getString(R.string.date5);

        ArrayList<Object> docsList = new ArrayList<>();
        docsList.add(context.getResources().getString(R.string.documents_expiredDocs));
        docsList.add(new Document(doc1, exp1));
        docsList.add(new Document(doc2, exp2));
        docsList.add(context.getResources().getString(R.string.documents_upToDateDocs));
        docsList.add(new Document(doc3, exp3));
        docsList.add(new Document(doc4, exp4));
        docsList.add(new Document(doc5, exp5));
        return docsList;
    }

}
