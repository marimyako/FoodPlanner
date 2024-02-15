package com.example.foodplanner;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Profile extends Fragment {

Button logoutbtn;
FirebaseAuth mAuth;
FirebaseUser user;
LoginScreen loginScreen;
    public Profile() {
        // Required empty public constructor
    }

    private void finishFragment() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.popBackStack();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_profile, container, false);
        loginScreen=(LoginScreen)getActivity();
        logoutbtn=view.findViewById(R.id.logoutbtn);
        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        if (user==null){
            Intent intent=new Intent(getActivity(), LoginScreen.class);
            startActivity(intent);
            finishFragment();
        }
         logoutbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 FirebaseAuth.getInstance().signOut();
                 Intent intent=new Intent(getActivity(), LoginScreen.class);
                 startActivity(intent);
                 finishFragment();
             }
         });


        return view;
    }
}