package com.nightonke.wowoviewpagerexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.nightonke.wowoviewpagerexample.gif.GIFActivity;
import com.nightonke.wowoviewpagerexample.svg.SVGActivity;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.wowo_position_animation).setOnClickListener(this);
        findViewById(R.id.wowo_translation_animation).setOnClickListener(this);
        findViewById(R.id.wowo_scale_animation).setOnClickListener(this);
        findViewById(R.id.wowo_alpha_animation).setOnClickListener(this);
        findViewById(R.id.wowo_rotation_animation).setOnClickListener(this);

        findViewById(R.id.wowo_text_view_text_size_animation).setOnClickListener(this);
        findViewById(R.id.wowo_text_view_color_animation).setOnClickListener(this);
        findViewById(R.id.wowo_text_view_text_animation).setOnClickListener(this);

        findViewById(R.id.wowo_background_color_animation).setOnClickListener(this);
        findViewById(R.id.wowo_drawable_color_animation).setOnClickListener(this);
        findViewById(R.id.wowo_layer_list_color_animation).setOnClickListener(this);
        findViewById(R.id.wowo_state_list_color_animation).setOnClickListener(this);

        findViewById(R.id.wowo_path_animation).setOnClickListener(this);

        findViewById(R.id.gearbox).setOnClickListener(this);
        findViewById(R.id.static_view_pager).setOnClickListener(this);

        findViewById(R.id.svg_expansibility).setOnClickListener(this);
        findViewById(R.id.gif_expansibility).setOnClickListener(this);

        findViewById(R.id.cv_example).setOnClickListener(this);
        findViewById(R.id.guide_page_1).setOnClickListener(this);
        findViewById(R.id.guide_page_2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SetEaseTypeActivity.class);
        switch (v.getId()) {
            case R.id.wowo_position_animation:
                intent.putExtra("AnimationType", "WoWoPositionAnimation");
                break;
            case R.id.wowo_translation_animation:
                intent.putExtra("AnimationType", "WoWoTranslationAnimation");
                break;
            case R.id.wowo_scale_animation:
                intent.putExtra("AnimationType", "WoWoScaleAnimation");
                break;
            case R.id.wowo_alpha_animation:
                intent.putExtra("AnimationType", "WoWoAlphaAnimation");
                break;
            case R.id.wowo_rotation_animation:
                intent.putExtra("AnimationType", "WoWoRotationAnimation");
                break;
            case R.id.wowo_text_view_text_size_animation:
                intent.putExtra("AnimationType", "WoWoTextViewTextSizeAnimation");
                break;
            case R.id.wowo_text_view_color_animation:
                intent.putExtra("AnimationType", "WoWoTextViewColorAnimation");
                break;
            case R.id.wowo_text_view_text_animation:
                intent.putExtra("AnimationType", "WoWoTextViewTextAnimation");
                break;
            case R.id.wowo_background_color_animation:
                intent.putExtra("AnimationType", "WoWoBackgroundColorAnimation");
                break;
            case R.id.wowo_drawable_color_animation:
                intent.putExtra("AnimationType", "WoWoShapeColorAnimation");
                break;
            case R.id.wowo_layer_list_color_animation:
                intent.putExtra("AnimationType", "WoWoLayerListColorAnimation");
                break;
            case R.id.wowo_state_list_color_animation:
                intent.putExtra("AnimationType", "WoWoStateListColorAnimation");
                break;
            case R.id.wowo_path_animation:
                intent.putExtra("AnimationType", "WoWoPathAnimation");
                break;
            case R.id.gearbox:
                startActivity(new Intent(this, GearboxActivity.class));
                return;
            case R.id.static_view_pager:
                startActivity(new Intent(this, StaticActivity.class));
                return;
            case R.id.svg_expansibility:
                startActivity(new Intent(this, SVGActivity.class));
                return;
            case R.id.gif_expansibility:
                startActivity(new Intent(this, GIFActivity.class));
                return;
            case R.id.guide_page_1:
                startActivity(new Intent(this, GuidePageActivity1.class));
                return;
            case R.id.guide_page_2:

                return;
        }
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_github:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "https://github.com/Nightonke/WoWoViewPager")));
                return true;
            case R.id.action_developer:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "https://github.com/Nightonke")));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
