package com.kyrostechnologies.template.chatting.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.kyrostechnologies.template.chatting.ActivityFriendDetails;
import com.kyrostechnologies.template.chatting.ActivityMain;
import com.kyrostechnologies.template.chatting.R;
import com.kyrostechnologies.template.chatting.adapter.FriendsListAdapter;
import com.kyrostechnologies.template.chatting.data.Constant;
import com.kyrostechnologies.template.chatting.model.Friend;
import com.kyrostechnologies.template.chatting.widget.DividerItemDecoration;

public class FriendsFragment extends Fragment {

    private RecyclerView recyclerView;
    public FriendsListAdapter mAdapter;
    private ProgressBar progressBar;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_friends, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        progressBar  = (ProgressBar) view.findViewById(R.id.progressBar);
		
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
		
        // specify an adapter (see also next example)
        mAdapter = new FriendsListAdapter(getActivity(), Constant.getFriendsData(getActivity()));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new FriendsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, Friend obj, int position) {
                ActivityFriendDetails.navigate((ActivityMain) getActivity(), v.findViewById(R.id.image), obj);
            }
        });

        return view;
    }

    public void onRefreshLoading(){
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }
}
