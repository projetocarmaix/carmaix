package br.com.carmaix.services;

import android.content.Context;

import java.util.ArrayList;

public interface VersionRelease {

    TokenReturn login(Context context, String user, String password) throws Exception;

    ArrayList<AvaliationReturn> listAvaliations(Context context, MethodType methodType, int limit, int offset, String status, String sortBy, String sortOrder) throws Exception;

    ArrayList<AvaliationReturn> searchAvaliations(Context context, MethodType methodType, String pattern, int limit, int offset, String status, String sortBy, String sortOrder) throws Exception;

    String getAvaliationsDetail(Context context, MethodType methodType, int id) throws Exception;

}
