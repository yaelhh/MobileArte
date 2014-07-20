package backend;

import com.google.api.client.util.Key;
import com.kinvey.java.LinkedResources.LinkedGenericJson;

public class ImagenesComunidadBackend extends LinkedGenericJson {
	@Key("ID")
	private String ID;
	private String Filename;
	
    public ImagenesComunidadBackend() {
        //"attachment" is the JSON element used to maintain a Linked File.
        putFile("attachment");
    }
    public String getText() {
        return Filename;
    }
    public void setText(String text) {
        this.Filename = text;
    }
	public String getId() {
		return ID;
	}
	public void setId(String id) {
		this.ID = id;
	}
    
    
}

