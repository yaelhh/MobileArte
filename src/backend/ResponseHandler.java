/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package backend;

public interface ResponseHandler { 
    
    /**
     * Handle the result of an HTTP request
     * 
     * @param response Contains the status code, headers, and body of the response.  Body may be null.  The special 
     *  status code -1 means a non-HTTP failure; for example, authorization failure or network connection problems.
     */
    public void handle(Response response);
}