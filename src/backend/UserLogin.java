package backend;

import android.app.Application;

import com.kinvey.android.Client;

/**
 * Global application class.  Instantiates the KCS Client and sets global constants.
 *
 */
public class UserLogin extends Application {
    private Client service;

    // Application Constants
    public static final String AUTHTOKEN_TYPE = ".clientarte";
    public static final String ACCOUNT_TYPE = ".clientarte";
    public static final String LOGIN_TYPE_KEY = "loginType";
    public static final String TAG = "ArteBackend";
	private String appKey="kid_VT8_It3ePE";
	private String appSecret="1b0fa51481984d2da5910f78a9d26ccc";
    
    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }
   

    private void initialize() {
		// Enter your app credentials here
		//service = new Client.Builder(this).build();
    	service = new Client.Builder(appKey,appSecret,this).build();
    }

    public Client getKinveyService() {
    	service = new Client.Builder(appKey,appSecret,this).build();
        return service;
    }
}
