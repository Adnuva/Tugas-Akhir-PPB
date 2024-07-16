package com.example.bluest.bottombar;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bluest.R;
import com.example.bluest.oauth.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link account#newInstance} factory method to
 * create an instance of this fragment.
 */
public class account extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public account() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment account.
     */
    // TODO: Rename and change types and number of parameters
    public static account newInstance(String param1, String param2) {
        account fragment = new account();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        // Inflate the layout for this fragment
        String loggedInUsername = "username_pengguna";

        // Mengambil data pengguna dari database
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        Cursor cursor = dbHelper.getUserDataByUsername(loggedInUsername);

        if (cursor.moveToFirst()) {
            // Mendapatkan data dari Cursor
//            String username = cursor.getString(cursor.getColumnIndex("username"));
//            String password = cursor.getString(cursor.getColumnIndex("password"));
            int usernameColumnIndex = cursor.getColumnIndex(DatabaseHelper.COL_USERNAME);
            int passwordColumnIndex = cursor.getColumnIndex(DatabaseHelper.COL_PASS);

            // Mendapatkan data dari Cursor
            String username = cursor.getString(usernameColumnIndex);
            String password = cursor.getString(passwordColumnIndex);

            // Menampilkan data pengguna di UI (gunakan elemen UI yang sesuai)
//            TextView usernameTextView = view.findViewById(R.id.username);
//            usernameTextView.setText("Username: ");
//            TextView pass = view.findViewById(R.id.password);
//            pass.setText("Password: " + password);

            // Anda dapat menambahkan elemen UI lainnya sesuai kebutuhan
        }

        cursor.close();
        dbHelper.close();
        return view;
    }
}