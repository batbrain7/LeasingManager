package tech.mohitkumar.internappdesign.Activities;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import is.arontibo.library.ElasticDownloadView;
import me.anwarshahriar.calligrapher.Calligrapher;
import tech.mohitkumar.internappdesign.R;

public class UploadActivity extends AppCompatActivity {

    public static final int VIDEO_CAPTURE_REQUEST = 1;
    ElasticDownloadView elasticDownloadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        elasticDownloadView = (ElasticDownloadView) findViewById(R.id.elastic_download_view);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Fonts/OpenSans-Regular.ttf", true);
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent,VIDEO_CAPTURE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == VIDEO_CAPTURE_REQUEST && resultCode == RESULT_OK && data!=null) {
            elasticDownloadView.setVisibility(View.VISIBLE);
            elasticDownloadView.startIntro();
            elasticDownloadView.setProgress(10);
            elasticDownloadView.success();
            //elasticDownloadView.fail();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
