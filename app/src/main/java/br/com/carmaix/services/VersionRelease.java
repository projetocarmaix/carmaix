package br.com.carmaix.services;

import android.content.Context;

public interface VersionRelease {

    TokenReturn login(Context context, String user, String password) throws Exception;

    String listAvaliations(Context context, MethodType methodType, int limit, int offset, String status, String sortBy, String sortOrder) throws Exception;

    String searchAvaliations(Context context, MethodType methodType, String pattern, int limit, int offset, String status, String sortBy, String sortOrder) throws Exception;

    String getAvaliationsDetail(Context context, MethodType methodType, int id) throws Exception;

}
