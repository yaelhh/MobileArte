package backend;

import com.google.api.client.util.Key;
import com.kinvey.java.LinkedResources.LinkedGenericJson;

public class UpdateEntity extends LinkedGenericJson {
	@Key("ID")
	private String id;
	private String text;
    public UpdateEntity() {
        //"attachment" is the JSON element used to maintain a Linked File.
        putFile("attachment");
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    
    
}
