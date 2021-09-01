package com.example.hustmatching.ui.main;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hustmatching.R;
import com.example.hustmatching.databinding.FragmentMyInfoBinding;

import static android.app.Activity.RESULT_OK;

public class MyInfoFragment extends Fragment {

    private static final String TAG = "MyInfoFragment";

    FragmentMyInfoBinding binding;
    View view;
    SharedPreferences sharedPreferences;

    ActivityResultLauncher<Intent> pickPhoto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pickPhoto = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if( result.getResultCode() == RESULT_OK) {
                            if(Build.VERSION.SDK_INT >= 19){
                                //4.4及以上系统使用这个方法处理图片
                                handleImageOnKitKat(result.getData());
                            }else{
                                //4.4以下系统使用这个方法处理图片
                                handeleImageBeforeKitKat(result.getData());
                            }
                        }
                    }
                });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_info, container, false);
        view = binding.getRoot();

        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name","未设置");
        binding.userName.setText(name);
        String id = sharedPreferences.getString("ID","未实名认证");
        binding.userId.setText(id);
        String imagePath = sharedPreferences.getString("path","");
        if( imagePath != "") Glide.with(getContext()).load(imagePath).into(binding.userImage);
        String nickName = sharedPreferences.getString("nickName","未设置");
        binding.userNickname.setText(nickName);
        int sex = sharedPreferences.getInt("sex",2);
        binding.sexSpinner.setSelection(sex);
        String contact = sharedPreferences.getString("contact","未设置");
        binding.userContact.setText(contact);
        String profile = sharedPreferences.getString("profile","未设置");
        binding.userProfile.setText(profile);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        binding.editNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View alertView = getActivity().getLayoutInflater().inflate(R.layout.dialog_edit_info, null, false);
                EditText editText = alertView.findViewById(R.id.input);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(alertView);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        binding.userNickname.setText(editText.getText());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("nickName", editText.getText().toString());
                        editor.apply();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        binding.editContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View alertView = getActivity().getLayoutInflater().inflate(R.layout.dialog_edit_info, null, false);
                EditText editText = alertView.findViewById(R.id.input);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(alertView);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        binding.userContact.setText(editText.getText());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("contact", editText.getText().toString());
                        editor.apply();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        binding.editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View alertView = getActivity().getLayoutInflater().inflate(R.layout.dialog_edit_info, null, false);
                EditText editText = alertView.findViewById(R.id.input);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(alertView);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        binding.userProfile.setText(editText.getText());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("profile", editText.getText().toString());
                        editor.apply();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        binding.sexSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("sex",position);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 点击");
                openAlbum();
            }
        });

        Log.d(TAG, "onCreateView: 设置");

        return view;
    }

    private void openAlbum() {
        Log.d(TAG, "openAlbum: 调用");
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        pickPhoto.launch(intent);
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
//        Toast.makeText(this,"到了handleImageOnKitKat(Intent data)方法了", Toast.LENGTH_LONG).show();
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(getActivity(), uri)){
            //如果是 document 类型的 Uri，则通过 document id 处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];//解析出数字格式的 id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            //如果是 content 类型的 uri ， 则使用普通方式处理
            imagePath = getImagePath(uri, null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            //如果是 file 类型的 Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath);//显示选中的图片
    }

    private void handeleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过 Uri 和 selection 来获取真实的图片路径
        Cursor cursor = getActivity().getContentResolver().query(uri, null, selection, null, null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if(imagePath != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("path",imagePath);
            editor.apply();
            Glide.with(getContext()).load(imagePath).into(binding.userImage);
        }else{
            Toast.makeText(getActivity(),"failed to get image", Toast.LENGTH_LONG).show();
        }
    }

}