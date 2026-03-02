package aicore.framework;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RestCaller {

    public static void createNewUser(String userId) throws IOException {
        String endpoint = UrlUtils.getApi("api/auth/createUser");
        Request rq = Request.post(endpoint)
                .bodyForm(Form.form()
                        .add("name", userId)
                        .add("username", userId)
                        .add("email", userId + "@test.com")
                        .add("password", "Test123!")
                        .build());

        HttpResponse response = rq.execute().returnResponse();
        int code = response.getCode();
        System.out.println(code);
        assert code == 200;
    }
    
    public static List<Map<String, Object>> getModelEngines(String cookie) throws IOException {
    	List<Map<String, Object>> engines = new ArrayList<>();
    	String endpoint = UrlUtils.getApi("api/auth/admin/engine/getEngines?engineTypes=MODEL");
		Request rq = Request.get(endpoint);
//                .bodyForm(Form.form()
//                        .add("engineTypes", "MODEL")
//                        .add("limit", limit+"")
//                        .add("offset", offset+"")
//                        .build());

		rq.addHeader("cookie", cookie);
		Response r = rq.execute();
		String s = r.returnContent().toString();
		Gson gson = new Gson();

		// Define the type of the map
		Type type = new TypeToken<List<Map<String, Object>>>() {
		}.getType();

		// Convert the JSON string to a map
		engines = gson.fromJson(s, type);

		return engines;
       
    }

    public static void main(String[] args) throws IOException {
        RestCaller.createNewUser("test4");
    }
}
