package com.jdkgroup.jkshopping.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jdkgroup.jkshopping.R;
import com.jdkgroup.jkshopping.adapter.AdapterCompany;
import com.jdkgroup.jkshopping.adapter.AdapterProduct;
import com.jdkgroup.jkshopping.base.BaseFragment;
import com.jdkgroup.jkshopping.general.General;
import com.jdkgroup.jkshopping.general.GeneralImplement;
import com.jdkgroup.jkshopping.model.ResponseData;
import com.jdkgroup.jkshopping.model.shopping_201.ResponseJKShopping_201;
import com.jdkgroup.jkshopping.utils.AppKeyword;
import com.jdkgroup.jkshopping.utils.SaveSharedPrefernces;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class FragmentFashionProduct extends BaseFragment {
    private Activity activity;
    private Context context;
    private View view;

    private General general;
    private SaveSharedPrefernces ssp;

    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private AdapterProduct adapterProduct;
    //private List<ResponseProductData> alProductData;

    private Gson gson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fashion_product, null);

        activity = getActivity();
        context = getActivity();

        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorLayout);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        general = new GeneralImplement();
        ssp = new SaveSharedPrefernces();
        gson = new Gson();

        general.RecyclerViewListGrid(activity, recyclerView, 1, AppKeyword.RECYCLERVIEW_GRID);

        new AsyncTask_Shopping_200().execute();

        // Set a Listener on the SwipeLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new AsyncTask_Shopping_200().execute();
                    }
                }, 5000);
            }
        });

        return view;
    }

    public class AsyncTask_Shopping_200 extends AsyncTask<Void, Void, Void> {

        private String responsevalue;
        private Socket socket;
        private OutputStream outputStream;
        private DataOutputStream dataOutputStream;
        private InputStream inputStream;
        private DataInputStream dataInputStream;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog(activity);
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {

                InetAddress inetAddress = InetAddress.getByName(AppKeyword.HOSTNAME);
                socket = new Socket(inetAddress, AppKeyword.PORT);
                outputStream = socket.getOutputStream();

                //TODO SENT THE CLIENT REQUEST TO SERVER
                dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeInt(AppKeyword.CODE_COMPANY_DISPLAY_ALL);
                dataOutputStream.writeUTF("");

                //TODO SERVER RESPONSE
                inputStream = socket.getInputStream();
                dataInputStream = new DataInputStream(inputStream);

                responsevalue = dataInputStream.readUTF();
            } catch (IOException ex) {

            } finally {
                //TODO socket CLOSE MUST BE
                try {
                    outputStream.flush();
                    dataOutputStream.flush();

                    outputStream.close();
                    dataOutputStream.close();
                    inputStream.close();
                    dataInputStream.close();

                    socket.close();

                } catch (IOException e) {
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            hideProgressDialog();
            swipeRefreshLayout.setEnabled(false);

            ResponseJKShopping_201 responseJKShopping_201 = gson.fromJson(responsevalue, ResponseJKShopping_201.class);
            ResponseData responseData = responseJKShopping_201.getResponsedata();


            if (responseData.getCode() == AppKeyword.WS_CODE_0) {
                general.DisplaySnackbar(coordinatorLayout, responseData.getMessage(), null, null, 0, 1, AppKeyword.STATUS);
            } else {
                adapterProduct = new AdapterProduct(activity, responseJKShopping_201.getAlcompany());
                recyclerView.setAdapter(adapterProduct);
            }
        }
    }
}