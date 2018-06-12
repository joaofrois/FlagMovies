package pt.flag.flagmovies.http.requests;

import android.content.Context;
import android.os.AsyncTask;
import java.net.MalformedURLException;
import java.net.URL;

import pt.flag.flagmovies.R;
import pt.flag.flagmovies.helpers.RequestsHelper;
import pt.flag.flagmovies.utils.DLog;
import pt.flag.flagmovies.utils.ResponseErrors;
import pt.flag.flagmovies.utils.ServerResponse;

public abstract class ExecuteRequestAsyncTask<ResponseEntity> extends AsyncTask<Void, Void, ServerResponse<ResponseEntity>> {

    private static final String API_KEY = "api_key";
    private static final String TAG = ExecuteRequestAsyncTask.class.getSimpleName();



    private Context context;

    public ExecuteRequestAsyncTask(Context context){
        this.context = context;
    }

    protected abstract String getPath();

    /**
     * Implementations must override this method and call
     * addQueryParam for each query arg
     * */
    protected abstract void addQueryParams(StringBuilder sb);

    /**
     * In Java we cannot get the class type from a generic type,
     * so the implementations must provide it
     * */
    protected abstract Class<ResponseEntity> getResponseEntityClass();

    /**
     * Called when server response finish with success
     * */
    protected abstract void onResponseSuccess(ResponseEntity responseEntity);

    /**
     * Called when there is an error calling server (possible internet connection error)
     * */
    protected abstract void onNetworkError();

    @Override
    protected final void onPostExecute(ServerResponse<ResponseEntity> serverResponse) {
        super.onPostExecute(serverResponse);
        switch(serverResponse.getErrorType()){
            case ResponseErrors.NO_ERROR:
                onResponseSuccess(serverResponse.getResponseEntity());
                break;
            case ResponseErrors.PARSE_ERROR:
                throw new RuntimeException("Parse error! Verify your response entity!");
            case ResponseErrors.NETWORK_ERROR:
                onNetworkError();
                break;
            case ResponseErrors.URL_MALFORMED:
                throw new RuntimeException("Url called is malformed! Verify it!");
        }
    }

    /**
     * Documentation: https://developer.android.com/reference/java/net/HttpURLConnection.html
     * */
    @Override
    protected ServerResponse<ResponseEntity> doInBackground(Void... voids) {
        URL url;

        try {
            String urlString = buildUrl();
            DLog.d(TAG, "execute request for url: " + urlString);

            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return new ServerResponse<>(null, ResponseErrors.URL_MALFORMED);
        }

        try {
            String response = RequestsHelper.executeRequest(url);

            DLog.d(TAG, "Response: " + response);

            if(response == null){
                return new ServerResponse<>(null, ResponseErrors.NETWORK_ERROR);
            }

            ResponseEntity responseEntity = RequestsHelper.fromJson(response, getResponseEntityClass());

            return new ServerResponse<>(responseEntity, ResponseErrors.NO_ERROR);
        }
        catch(Exception e){
            DLog.e(TAG, "doInBackground() exception: " + e);
            return new ServerResponse<>(null, ResponseErrors.PARSE_ERROR);
        }

    }



    private String buildUrl() {
        String apiKey = context.getString(R.string.server_api_key);
        StringBuilder sb = new StringBuilder(context.getString(R.string.server_endpoint));
        sb.append(getPath());
        addQueryParam(sb, API_KEY, apiKey);
        addQueryParams(sb);

        return sb.toString();
    }

    protected StringBuilder addQueryParam(StringBuilder sb, String key, String value) {
        return RequestsHelper.addQueryParam(sb, key, value);
    }

}