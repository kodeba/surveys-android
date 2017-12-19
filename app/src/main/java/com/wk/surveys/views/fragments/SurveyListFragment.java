package com.wk.surveys.views.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wk.data.entities.Survey;
import com.wk.surveys.R;
import com.wk.surveys.adapters.SurveyListAdapter;
import com.wk.surveys.databinding.FragmentSurveyListBinding;
import com.wk.surveys.models.Response;
import com.wk.surveys.viewmodels.SurveyViewModel;

import java.util.List;

import me.kaelaela.verticalviewpager.VerticalViewPager;
import me.kaelaela.verticalviewpager.transforms.DefaultTransformer;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class SurveyListFragment extends Fragment implements SurveyListAdapter.SurveyListListener {

    SurveyListAdapter.SurveyListListener surveyListListener;

    public SurveyListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof SurveyListAdapter.SurveyListListener){
            surveyListListener = (SurveyListAdapter.SurveyListListener) context;
        }else{
            throw new RuntimeException("Activity must implement "+ SurveyListAdapter.SurveyListListener.class.getSimpleName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentSurveyListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_survey_list, container, false);
        SurveyViewModel surveyViewModel = ViewModelProviders.of(getActivity()).get(SurveyViewModel.class);

        SurveyListAdapter adapter = new SurveyListAdapter(getContext(),this);
        binding.surveyViewpager.setAdapter(adapter);
        binding.surveyViewpager.setPageTransformer(false, new DefaultTransformer());



        surveyViewModel.getResponse().observe(this,response->{
            if(response.status){
                adapter.setSurveys(response.data,false);
                binding.pageIndicator.setViewPager(binding.surveyViewpager);
            }else{
                Timber.i("error : %s",response.error.getMessage());
            }
        });

        surveyViewModel.loadRemoteData(1,5);


        return binding.getRoot();
    }

    @Override
    public void OnSurveyListSelected(Survey survey) {
        surveyListListener.OnSurveyListSelected(survey);
    }
}
