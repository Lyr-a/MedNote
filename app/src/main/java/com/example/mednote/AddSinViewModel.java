package com.example.mednote;

import androidx.lifecycle.ViewModel;

public class AddSinViewModel extends ViewModel {

    String currentPhotoPath = "";

    public String getCurrentPhotoPath(){
        return currentPhotoPath;
    }

    public void setCurrentPhotoPath(String currentPhotoPath){
        this.currentPhotoPath = currentPhotoPath;
    }

}
