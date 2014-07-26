/** 
 * Copyright (c) 2014 Kinvey Inc.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * 
 */
package loginBackend;

import com.kinvey.android.Client;

import android.app.Application;

/**
 * Global application class.  Instantiates the KCS Client and sets global constants.
 *
 */
public class UserLogin extends Application {
    private Client service;

    // Application Constants
    public static final String AUTHTOKEN_TYPE = "com.kinvey.myapplogin";
    public static final String ACCOUNT_TYPE = "com.kinvey.myapplogin";
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
        return service;
    }
}