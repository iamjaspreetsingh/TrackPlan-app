package com.jskgmail.attendance;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class addstudpicActivity extends AppCompatActivity {
    String TAG="cameraa";
    String mCurrentPhotoPath;
    String facetoken="tt";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    File image = null;
    File photoFile = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudpic);
dispatchTakePictureIntent();
        Button b=(Button)findViewById(R.id.button14);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    private File createImageFile(){
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        try {
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
Log.e("paath",mCurrentPhotoPath);


        return image;

    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go

            photoFile = createImageFile();
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.jskgmail.attendance.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    goapii();
                }
            }

        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
    private class FriendsProcessor extends AsyncTask<String, Void, Integer> {



        public FriendsProcessor() {

            super();

        }


        // The onPreExecute is executed on the main UI thread before background processing is

        // started. In this method, we start the progressdialog.

        @Override

        protected void onPreExecute() {

            super.onPreExecute();


            // Show the progress dialog on the screen


        }


        // This method is executed in the background and will return a result to onPostExecute

        // method. It receives the file name as input parameter.

        @Override

        protected Integer doInBackground(String... urls) {


            InputStream inputStream = null;

            HttpURLConnection urlConnection = null;

            Integer result = 0;


            // TODO connect to server, download and process the JSON string


            // Now we read the file, line by line and construct the

            // Json string from the information read in.

            try {

                /* forming th java.net.URL object */

                URL url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();



                 /* optional request header */

                urlConnection.setRequestProperty("Content-Type", "application/json");



                /* optional request header */

                urlConnection.setRequestProperty("Accept", "application/json");



                /* for Get request */

                urlConnection.setRequestMethod("POST");

                int statusCode = urlConnection.getResponseCode();



                /* 200 represents HTTP OK */

                if (statusCode == 200) {

                    inputStream = new BufferedInputStream(urlConnection.getInputStream());


                    // Convert the read in information to a Json string

                    String response = convertInputStreamToString(inputStream);


                    // now process the string using the method that we implemented in the previous exercise

                    Log.e("responseeee",response.replace(" ",""));


TextView t=(TextView)findViewById(R.id.textView82);
t.setText(response);

























                    result = 1; // Successful

                } else {

                    result = 0; //"Failed to fetch data!";

                }

            } catch (Exception e) {

                Log.d(TAG, e.getLocalizedMessage());

            }

            return result; //"Failed to fetch data!";

        }

    }
    private class FriendsProcessor1 extends AsyncTask<String, Void, Integer> {



        public FriendsProcessor1() {

            super();

        }


        // The onPreExecute is executed on the main UI thread before background processing is

        // started. In this method, we start the progressdialog.

        @Override

        protected void onPreExecute() {

            super.onPreExecute();


            // Show the progress dialog on the screen


        }


        // This method is executed in the background and will return a result to onPostExecute

        // method. It receives the file name as input parameter.

        @Override

        protected Integer doInBackground(String... urls) {


            InputStream inputStream = null;

            HttpURLConnection urlConnection = null;

            Integer result = 0;


            // TODO connect to server, download and process the JSON string


            // Now we read the file, line by line and construct the

            // Json string from the information read in.

            try {

                /* forming th java.net.URL object */

                URL url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();



                 /* optional request header */

                urlConnection.setRequestProperty("Content-Type", "application/json");



                /* optional request header */

                urlConnection.setRequestProperty("Accept", "application/json");



                /* for Get request */

                urlConnection.setRequestMethod("POST");

                int statusCode = urlConnection.getResponseCode();



                /* 200 represents HTTP OK */

                if (statusCode == 200) {

                    inputStream = new BufferedInputStream(urlConnection.getInputStream());


                    // Convert the read in information to a Json string

                    String response = convertInputStreamToString(inputStream);


                    // now process the string using the method that we implemented in the previous exercise

                    Log.e("responseeee",response.replace(" ",""));

String re[]=response.replace(" ","").split(":");
facetoken=re[re.length-1].replace("}]}","").replace("\"","");

// Now calling faceadd api to faceset adding facetoken from above

                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//TODO

                    retrofit2.Call call = apiService.getall("8eXIfwPbVhLUXV4xt9eW2xRSxWt74Fki","9xyBX7iWUUWu4msZbaAm6_XTRN9OiT5b","537c2b49a9a160655b9a3c707555af4b",facetoken);
                    call.enqueue(new retrofit2.Callback() {
                        @Override
                        public void onResponse(retrofit2.Call call, Response response) {
                            Log.e(TAG, "success");
                            Log.e(TAG, response.raw().request().url().toString());
                            String url = response.raw().request().url().toString();
                            FriendsProcessor mytask = new FriendsProcessor();
                            mytask.execute(url);


                        }

                        @Override
                        public void onFailure(retrofit2.Call call, Throwable t) {
                            Log.e(TAG, "failureee");
                        }


                    });






























                    result = 1; // Successful

                } else {

                    result = 0; //"Failed to fetch data!";

                }

            } catch (Exception e) {

                Log.d(TAG, e.getLocalizedMessage());

            }

            return result; //"Failed to fetch data!";

        }

    }







    private String convertInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));

        String line = "";

        String result = "";

        while((line = bufferedReader.readLine()) != null){

            result += line;

        }



            /* Close Stream */

        if(null!=inputStream){

            inputStream.close();

        }

        return result;

    }









    @RequiresApi(api = Build.VERSION_CODES.M)
    void goapii() {


        ApiInterface1 apiService1 = ApiClient.getClient().create(ApiInterface1.class);
//TODO
        Log.e("filee", String.valueOf(image));
        retrofit2.Call call1 = apiService1.getall("8eXIfwPbVhLUXV4xt9eW2xRSxWt74Fki",
                "9xyBX7iWUUWu4msZbaAm6_XTRN9OiT5b",
                photoFile
        );




        call1.enqueue(new retrofit2.Callback() {
            @Override
            public void onResponse(retrofit2.Call call, Response response) {
                Log.e(TAG, "success");
                Log.e(TAG, response.raw().request().url().toString());
                String url = response.raw().request().url().toString();
                FriendsProcessor1 mytask = new FriendsProcessor1();
                mytask.execute(url);


            }

            @Override
            public void onFailure(retrofit2.Call call, Throwable t) {
                Log.e(TAG, "failureee");
            }


        });














    }
    public static class ApiClient {

        public static final String BASE_URL ="https://api-us.faceplusplus.com/facepp/v3/";

        private static Retrofit retrofit = null;

        public static Retrofit getClient() {
            if (retrofit==null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }


    }



    public interface ApiInterface {


        @POST("faceset/addface")
        retrofit2.Call<ResponseBody> getall(@Query("api_key") String code,
                                            @Query("api_secret") String monthact,
                                            @Query("faceset_token") String facesettoken,
                                            @Query("face_tokens") String facetoken
        );

    }


    public interface ApiInterface1 {


        @POST("detect")
        retrofit2.Call<ResponseBody> getall(@Query("api_key") String code,
                                            @Query("api_secret") String monthact,
                                            @Query("image_file") File imagefile
                                         //   @Query("image_url") String url
                                            // https://firebasestorage.googleapis.com/v0/b/attendance-4e350.appspot.com/o/06112011292-001.jpg?alt=media&token=a087ec2b-891c-4182-8df7-1917685aeb11

        );

    }














}
