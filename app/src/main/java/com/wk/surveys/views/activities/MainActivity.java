package com.wk.surveys.views.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wk.data.entities.Survey;
import com.wk.surveys.R;
import com.wk.surveys.adapters.SurveyListAdapter;
import com.wk.surveys.databinding.ActivityMainBinding;
import com.wk.surveys.models.Response;
import com.wk.surveys.uicomponents.EndDrawerToggle;
import com.wk.surveys.viewmodels.SurveyViewModel;
import com.wk.surveys.viewmodels.factories.SurveyViewModelFactory;
import com.wk.surveys.views.fragments.SurveyListFragment;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements SurveyListAdapter.SurveyListListener {

    public static final String SELECTED_SURVEY = "selected_survey";

    @Inject
    SurveyViewModelFactory surveyViewModelFactory;
    SurveyViewModel surveyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);

        this.getSupportActionBar().setTitle("");

        EndDrawerToggle endDrawerToggle = new EndDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        endDrawerToggle.syncState();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_layout, new SurveyListFragment());
        fragmentTransaction.commit();


        surveyViewModel = ViewModelProviders.of(this, surveyViewModelFactory).get(SurveyViewModel.class);
        surveyViewModel.getLoadingStatus().observe(this, status->{
            if(status){
                binding.loadingIndicator.setVisibility(View.VISIBLE);
            }else{
                binding.loadingIndicator.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public void OnSurveyListSelected(Survey survey) {
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra(SELECTED_SURVEY, Parcels.wrap(survey));
        startActivity(intent);
    }
}
