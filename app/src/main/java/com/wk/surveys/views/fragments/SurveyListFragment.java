package com.wk.surveys.views.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
    private static final Integer PER_PAGE = 5;

    SurveyListAdapter.SurveyListListener surveyListListener;
    private Boolean isClear = false;
    private Boolean allowLoadMore = true;
    private Integer page = 1;

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

        binding.surveyViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(allowLoadMore){
                    if(position == adapter.getCount()-3){
                        page = page+1;
                        surveyViewModel.loadRemoteData(page,PER_PAGE);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        surveyViewModel.getRefreshNotify().observe(this, status->{
            if(status){
                isClear = true;
                page = 1;
                allowLoadMore = true;
                surveyViewModel.loadRemoteData(page, PER_PAGE);
            }
        });

        surveyViewModel.getResponse().observe(this,response->{
            if(response.status){
                if((response.data!=null) && (response.data.size()>0)){
                    adapter.setSurveys(response.data,isClear);
                    binding.pageIndicator.setViewPager(binding.surveyViewpager);

                    if(isClear){
                        binding.surveyViewpager.setCurrentItem(0);
                    }

                    isClear = false;
                }else{
                    allowLoadMore = false;
                }

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
