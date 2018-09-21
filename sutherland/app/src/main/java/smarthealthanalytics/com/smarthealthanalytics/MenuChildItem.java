package smarthealthanalytics.com.smarthealthanalytics;

public class MenuChildItem {

    String title;
    Object object;
    public MenuChildItem(String title, Object object){
        this.title = title;
        this.object = object;
    }
    public String gettitle(){
        return  title;
    }
    public Object getobject(){
        return  object;
    }
}
