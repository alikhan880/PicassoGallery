package kz.kbtu.picassogallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapCircleThumbnail;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private String[] images = {"https://cdn.pixabay.com/photo/2017/01/06/23/21/soap-bubble-1959327_960_720.jpg",
                                "https://static.pexels.com/photos/34950/pexels-photo.jpg",
    "https://ak.picdn.net/assets/cms/97e1dd3f8a3ecb81356fe754a1a113f31b6dbfd4-stock-photo-photo-of-a-common-kingfisher-alcedo-atthis-adult-male-perched-on-a-lichen-covered-branch-107647640.jpg"};
    private String[] names = {"Image1", "Image2", "Image3"};
    @BindView(R.id.tv_url)
    EditText tvUrl;
    @BindView(R.id.spinner_url)
    Spinner spinnerUrl;
    @BindView(R.id.btn_load)
    BootstrapButton btnLoad;
    @BindView(R.id.thumbnail_circle)
    BootstrapCircleThumbnail thumbnailCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, names);
        spinnerUrl.setAdapter(adapter);
        spinnerUrl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadUrl(images[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @OnClick(R.id.btn_load)
    public void onViewClicked() {
        String url = tvUrl.getText().toString();
        Picasso.with(this).load(url).fit().centerCrop().into(thumbnailCircle);
        YoYo.with(Techniques.Shake).duration(500).repeat(3).playOn(btnLoad);
    }


    private void loadUrl(String url){
        tvUrl.setText(url);
    }
}
