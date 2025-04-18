package aicore.utils;

import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.HttpResponse;

import java.io.IOException;

public class RestCaller {

    public static void createNewUser(String userId) throws IOException {
        String endpoint = UrlUtils.getApi("api/auth/createUser");
        Request rq = Request.post(endpoint)
                .bodyForm(Form.form()
                        .add("name", userId)
                        .add("username", userId)
                        .add("email", userId + "@deloitte.com")
                        .add("password", "Test123!")
                        .build());

        HttpResponse response = rq.execute().returnResponse();
        int code = response.getCode();
        System.out.println(code);
        assert code == 200;
    }

    public static void main(String[] args) throws IOException {
        RestCaller.createNewUser("test4");
    }
}
