package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowInsetsController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.news.utils.DbHelper;

import java.util.List;

public class NewsDetailActivity extends AppCompatActivity {
    ImageView goBackFromDetails, imgOfNews1;
    TextView newsSource1, newsTitle1, newsTimeAgo1, newsContent1;
    Button learnMore;
    ImageView likeNews, saveNews, shareNews;
    DbHelper db = new DbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        getWindow().setStatusBarColor(getResources().getColor(R.color.white, this.getTheme()));

        // Ensure the status bar icons are black if the background is light
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().getDecorView().getWindowInsetsController().setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


        Intent intent = getIntent();
        String imgUrl = intent.getStringExtra("newsImgUrl");
        String source = intent.getStringExtra("newsSource");
        String title = intent.getStringExtra("newsTitle");
        String timeAgo = intent.getStringExtra("newsTimeAgo");
        String urlToWeb = intent.getStringExtra("newsUrlToWeb");
        String content = intent.getStringExtra("newsContent");


        goBackFromDetails = findViewById(R.id.goBackFromDetails);
        imgOfNews1 = findViewById(R.id.imgOfNews1);
        newsSource1 = findViewById(R.id.newsSource1);
        newsTitle1 = findViewById(R.id.newsTitle1);
        newsTimeAgo1 = findViewById(R.id.newsTimeAgo1);
        newsContent1 = findViewById(R.id.newsContent1);
        learnMore = findViewById(R.id.learnMore);
        likeNews = findViewById(R.id.likeNews);
        saveNews = findViewById(R.id.saveNews);
        shareNews = findViewById(R.id.shareNews);

        Glide.with(this).load(imgUrl).into(imgOfNews1);
        newsSource1.setText(source);
        newsTitle1.setText(title);
        newsSource1.setText(source);
        newsTimeAgo1.setText(timeAgo);
        newsContent1.setText(content);

        learnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse(urlToWeb);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });

        goBackFromDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsDetailActivity.this, MainActivity.class));
                finish();
            }
        });

        likeNews.setOnClickListener(v -> {
            Glide.with(getApplicationContext()).load(R.drawable.liked_clicked).into(likeNews);
            addNewsToLikeSection(imgUrl, source, title, timeAgo, urlToWeb, content);
        });
        saveNews.setOnClickListener(v -> {
            Glide.with(getApplicationContext()).load(R.drawable.save_clicked).into(saveNews);
//            List<NewsModel.Articles> articles =  db.getLikedNews();
//            Toast.makeText(this, articles.get(0).getTitle(), Toast.LENGTH_SHORT).show();
        });
        shareNews.setOnClickListener(v -> {
            Glide.with(getApplicationContext()).load(R.drawable.send_clicked).into(shareNews);
            shareNews(urlToWeb);
        });

    }

    private void addNewsToLikeSection(String imgUrl, String source, String title, String timeAgo, String urlToWeb, String content) {
        db.insertLikedNews(imgUrl, source, title, timeAgo, urlToWeb, content);
        Toast.makeText(this, "Added to favourites section", Toast.LENGTH_SHORT).show();
    }

    private void shareNews(String newsUrl){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this news!");
        shareIntent.putExtra(Intent.EXTRA_TEXT, newsUrl);
        startActivity(Intent.createChooser(shareIntent, "Share on"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(NewsDetailActivity.this, MainActivity.class));
        finish();
    }
}