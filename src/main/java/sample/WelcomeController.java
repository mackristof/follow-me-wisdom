/*
 * #%L
 * Wisdom-Framework
 * %%
 * Copyright (C) 2013 - 2014 Wisdom Framework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package sample;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.annotations.Validate;
import org.json.JSONArray;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.PathParameter;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.annotations.View;
import org.wisdom.api.annotations.scheduler.Async;
import org.wisdom.api.content.Json;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Your first Wisdom Controller.
 */
@Controller
public class WelcomeController extends DefaultController {


    /**
     * Injects a template named 'welcome'.
     */
    @View("welcome")
    Template welcome;

    @Requires
    Json json;


    @Validate
    public void init() throws Exception {

    }

    /**
     * The action method returning the welcome page. It handles
     * HTTP GET request on the "/" URL.
     *
     * @return the welcome page
     */
    @Route(method = HttpMethod.GET, uri = "/")
    public Result welcome() {
        return ok(render(welcome, "welcome", "Welcome to Wisdom Framework!"));
    }


    @Route(method = HttpMethod.GET, uri = "/db")
    @Async
    public Result listDb() throws UnirestException {
        final HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.get("http://127.0.0.1:5984/_all_dbs").asJson();
        final JSONArray array = jsonNodeHttpResponse.getBody().getArray();
        final com.fasterxml.jackson.databind.JsonNode jsonNode = json.parse(array.toString());
        return ok(jsonNode);

    }

    @Route(method = HttpMethod.PUT, uri = "/db/{database}")
    @Async
    public Result createDb(@PathParameter("database") String database){
        Result wisdomResult = new Result();
        //"http://localhost:5984/"+database)
        return wisdomResult;

    }


}
