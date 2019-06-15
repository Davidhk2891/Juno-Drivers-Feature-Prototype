package davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.model.objects;

/**
 * Created by David on 6/1/2019 for Juno Driver Features Prototype.
 */
public class Document {

    private String docName;
    private String docExp;

    public Document(String docname, String docexp){
        this.docName = docname;
        this.docExp = docexp;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocExp() {
        return docExp;
    }

    public void setDocExp(String docExp) {
        this.docExp = docExp;
    }
}
