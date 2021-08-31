package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailNeighbourActivity extends AppCompatActivity {

    private Neighbour detailNeighbour;
    private NeighbourApiService mApiService;

    @BindView(R.id.detail_avatar)
    ImageView avatarView;
    @BindView(R.id.detail_name)
    TextView avatarName;
    @BindView(R.id.detail_address)
    TextView address;
    @BindView(R.id.detail_phoneNumber)
    TextView phoneNumber;
    @BindView(R.id.detail_facebook)
    TextView facebook;
    @BindView(R.id.about_me)
    TextView aboutMe;
    @BindView(R.id.detail_addFavoriteButton)
    ImageButton addFavoriteButton;
    @BindView(R.id.detail_backButton)
    ImageButton backButton;
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        mApiService = DI.getNeighbourApiService();
        ButterKnife.bind(this);

        detailNeighbour = getIntent().getParcelableExtra("DETAIL_NEIGHBOUR");
        Glide.with(this)
                .load(detailNeighbour.getAvatarUrl())
                .into(avatarView);

        avatarName.setText(detailNeighbour.getName());
        address.setText(detailNeighbour.getAddress());
        phoneNumber.setText(detailNeighbour.getPhoneNumber());
        facebook.setText("www.facebook.fr/" + detailNeighbour.getName().toLowerCase());
        aboutMe.setText(detailNeighbour.getAboutMe());
        collapsingToolbarLayout.setTitle(detailNeighbour.getName());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if(mApiService.getFavorites().contains(detailNeighbour)){
            addFavoriteButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_yellow_24dp));
        }
        else{
            addFavoriteButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_yellow_24dp));
        }

       addFavoriteButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(mApiService.getFavorites().contains(detailNeighbour)){
                   mApiService.deleteFavorite(detailNeighbour);
                   addFavoriteButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_yellow_24dp));
               }
               else{
                   mApiService.addFavorite(detailNeighbour);
                   addFavoriteButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_yellow_24dp));
               }
           }
       });
    }
}
